package net.craigrm.dip.gameturn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.orders.Order;
import net.craigrm.dip.orders.SupportingOrder;
import net.craigrm.dip.orders.TurnOrders;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.state.TurnState;
import net.craigrm.dip.state.Unit;

public class Outcome {
	private OrderStatus orderState;
	private String detail;

	public Outcome(Order o) {
		//Check for badly formed order
		if (!o.isWellFormed()) {
			 orderState = OrderStatus.BADLYFORMED;
			return;
		}
		
		if (!o.hasValidStartingPosition()) {
			orderState = OrderStatus.BADLYFORMED;
			detail = "Invalid starting position.";
			return;
		}
		
		if (!o.hasValidTarget()) {
			orderState = OrderStatus.BADLYFORMED;
			detail = "Invalid destination.";
			return;
		}
		
		//Order is well formed. Check if it is legal.
		if (!TurnState.getState().isOwnedUnitPresent(o.getUnit())) {
			orderState = OrderStatus.ILLEGAL;
			detail = "The unit is not at the specified position.";
			return;
		}
		
		switch (o.getOrderType()) {
		case HOLD:
			break;
		case MOVE:
			if (!o.getUnit().canMove(o.getTarget())) {
				orderState = OrderStatus.ILLEGAL;
				detail = "Cannot move from current position to destination.";
				return;
			}
			break;
		case SUPPORT:
			OrderType supportedOrderType = ((SupportingOrder)o).getSupportedOrderType(); 
			Unit supportedUnit = ((SupportingOrder)o).getSupportedUnit();
			
			if (!TurnState.getState().isAnyUnitPresent(supportedUnit)) {
				orderState = OrderStatus.ILLEGAL;
				detail = "The supported unit is not at the specified position.";
				return;
			}
			
			switch (supportedOrderType) {
			case HOLD:
				if (!o.getUnit().canSupport(supportedUnit.getCurrentPosition())) {
					orderState = OrderStatus.ILLEGAL;
					detail = "Cannot support other unit from this position.";
					return;
				}
				break;
			case MOVE:
				if (!o.getUnit().canSupport(o.getTarget())) {
					orderState = OrderStatus.ILLEGAL;
					detail = "Cannot support other unit from this position.";
					return;
				} 
				if (!supportedUnit.canMove(o.getTarget())) {
					orderState = OrderStatus.ILLEGAL;
					detail = "Supported unit cannot move from current position to destination.";
					return;
				}
				break;
			default:
				assert false: "Supported order type should be HOLD or MOVE";
				break;
			}
			break;
		case CONVOY:
			//TODO
			throw new UnsupportedOperationException("Not yet implemented");
		default:
			assert false: "Order type should be HOLD, MOVE, SUPPORT or CONVOY";
			break;
		}
		
		// If we've got to here then the order is neither badly formed nor illegal.
		// We cannot tell in isolation if the order will succeed or fail,
		// so outcome is unknown for now.
		orderState = OrderStatus.UNKNOWN;

	}

	public Outcome (Order order, Set<Order> goodOrders) {
		switch (order.getOrderType()) {
		case HOLD:
			//TODO
			throw new UnsupportedOperationException("Not yet implemented");
		case MOVE:
			//TODO
			throw new UnsupportedOperationException("Not yet implemented");
		case SUPPORT:
			//Support fails if it is cut. Support can be successful even if
			//the supported order fails.
			if (isSupportCut((SupportingOrder)order, goodOrders)) {
				orderState = OrderStatus.FAILED;
			} else {
				orderState = OrderStatus.SUCCESSFUL;
			}
			break;
		case CONVOY:
			//TODO
			throw new UnsupportedOperationException("Not yet implemented");
		default:
			assert false: "Order type should be HOLD, MOVE, SUPPORT or CONVOY";
			break;
		}
		
	}
	
	
	public OrderStatus getOrderState() {
		return orderState;
	}

	public String getDetail() {
		return detail;
	}

	private boolean isSupportCut(SupportingOrder order, Set<Order> orders) {
		// Support is cut if:
		// 1) The supporting unit is dislodged
		// 2) The supporting unit is attacked and the supporting unit is not:
		//   a) supporting an attack on the attacking unit
		//   b) supporting an attack on a single point of failure on the convoy 
		//      of the attacking unit
		
		//TODO This method is not accurate in some edge cases where the attacker is convoyed 
		//and the supporting unit is supporting an action against one of the convoying units 
		
		//Get a collection of attack orders targeting this supporting unit 
		ProvinceIdentifier currentPosition = order.getUnitPosition();
		Set<Order> moveOrders = TurnOrders.getOrdersByType(OrderType.MOVE, orders);
		Set<Order> attackedByOrders = new HashSet<Order>();
		for(Order moveOrder: moveOrders) {
			if (moveOrder.getTarget().equals(currentPosition)) {
				attackedByOrders.add(moveOrder);
			}
		}

		// Check for no attacks.
		if (attackedByOrders.isEmpty()) {
			return false;
		}

		//Check for more than one attack: guaranteed to cut support.
		if (attackedByOrders.size() > 1) {
			return true;
		}

		//Attacked by one unit. 
		//Check if the supporting unit is supporting an attack: if not, support is cut
		if (!order.getSupportedOrderType().equals(OrderType.MOVE)) {
			return true;
		}

		//Get attack details
		ProvinceIdentifier supportOrderDestination = order.getTarget();
		Order moveOrder = null;
		for (Order o: moveOrders) { //Expecting exactly one member
			moveOrder = o;
		}

		//Check if the supported attack is against the attacker: if it is, support is NOT cut
		if (!supportOrderDestination.equals(moveOrder.getUnitPosition())) {
			return false;
		}
			

		//Check if attacker is being convoyed: if not, support is cut
		Set<List<ProvinceIdentifier>> convoyRoutes = TurnOrders.getConvoyRoutesForOrder(moveOrder, orders);
		if (convoyRoutes.isEmpty()) {
			return true;
		}

		
		//Check if the supported attack is against a single point of failure in the convoy routes of the attacker: 
		// if not, support is cut
		for(List<ProvinceIdentifier> convoyRoute: convoyRoutes) {
			if (!convoyRoute.contains(order.getTarget())) {
				//There is at least one convoy route that is not attacked 
				return true;
			}
		}
		
		// The supported attack is against a single point of failure in the attacking unit's convoy. The support is NOT cut.
		return false;
		
	}

}
