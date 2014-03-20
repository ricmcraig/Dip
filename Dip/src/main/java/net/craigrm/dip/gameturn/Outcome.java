package net.craigrm.dip.gameturn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			detail = "Invalid Target.";
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
				detail = "Cannot move from current position to Target.";
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
					detail = "Supported unit cannot move from current position to Target.";
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
			orderState = getMoveOrderStatus((SupportingOrder)order, goodOrders);
		case SUPPORT:
			//Support fails if it is cut. Support can be successful even if
			//the supported order fails.
			orderState = getSupportOrderStatus((SupportingOrder)order, goodOrders);
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

	private OrderStatus getSupportOrderStatus(SupportingOrder order, Set<Order> orders) {
		// Support is cut if:
		// 1) The supporting unit is dislodged
		// 2) The supporting unit is attacked and the supporting unit is not:
		//   a) supporting an attack on the attacking unit
		//   b) supporting an attack on a single point of failure on the convoy 
		//      of the attacking unit
		
		// Get a collection of attack orders targeting this supporting unit 
		ProvinceIdentifier currentPosition = order.getUnitPosition();
		Set<Order> attackedByOrders = TurnOrders.getMovesTo(currentPosition);

		// Check for no attacks.
		if (attackedByOrders.isEmpty()) {
			return OrderStatus.SUCCESSFUL;
		}

		// Check for more than one attack: guaranteed to cut support.
		if (attackedByOrders.size() > 1) {
			return OrderStatus.FAILED;
		}

		// Attacked by one unit. 
		// Check if the supporting unit is supporting an attack: if not, support is cut
		if (!order.getSupportedOrderType().equals(OrderType.MOVE)) {
			return OrderStatus.FAILED;
		}

		// Get supported attack details
		ProvinceIdentifier supportOrderTarget = order.getTarget();
		Order supportedMoveOrder = TurnOrders.getOrderForSupportingOrder(order);
		
		// Check that the supported order exists: if not, support is cut
		if (supportedMoveOrder == null) {
			return OrderStatus.FAILED;
		}

		// Check if the supported attack is against the attacker: if it is, support is NOT cut
		if (!supportOrderTarget.equals(supportedMoveOrder.getUnitPosition())) {
			return OrderStatus.SUCCESSFUL;
		}

		// Check if attacker is being convoyed: if not, support is cut
		Set<List<ProvinceIdentifier>> convoyRoutes = TurnOrders.getConvoyRoutesForOrder(supportedMoveOrder, orders);
		if (convoyRoutes.isEmpty()) {
			return OrderStatus.FAILED;
		}
		
		// Check if the supported attack is against a single point of failure in the convoy routes of the attacker: 
		// if not, support is cut
		for(List<ProvinceIdentifier> convoyRoute: convoyRoutes) {
			if (!convoyRoute.contains(order.getTarget())) {
				// There is at least one convoy route that is not attacked 
				return OrderStatus.FAILED;
			}
		}
		
		// The supported attack is against a single point of failure in the attacking unit's convoy. The support is NOT cut.
		return OrderStatus.SUCCESSFUL;
		
	}

	private OrderStatus getMoveOrderStatus(Order moveOrder, Set<Order> orders) {
		// Move succeeds if:
		// 1) a. Target is empty and 
		//    b. no other unit is attempting to move there
		// 2) a. Target is empty and 
		//    b. support for this moving unit is greater than support 
		//       for each of the other unit attempting to move to this Target
		// 3) a. Target is occupied by foreign unit and 
		//    b. no other unit is attempting to move there and 
		//    c. support for this moving unit is greater than for the occupying unit
		
		// Move fails if:
		// 1) a. Another unit is attempting to move there with greater or equal support
		
		// Move status unknown if:
		// 1) a. Target is occupied by unit that is attempting to move

		
		// Get a collection of move orders targeting the province. 
		ProvinceIdentifier targetProvince = moveOrder.getUnitPosition();
		Set<Order> movingOrders = TurnOrders.getMovesTo(targetProvince);

		// Get order for occupying unit (if any).
		Order occupyingOrder = TurnOrders.getOrderFor(targetProvince);
		
		// Target is empty and no competing unit.
		if (occupyingOrder == null && movingOrders.size() == 1) {
			return OrderStatus.SUCCESSFUL;
		}
		
		// Calculate highest support for units moving to the target province.
		Map<OrderStatus, Set<Order>> ordersByStatus = new HashMap<OrderStatus, Set<Order>>();
		Map<Order, Integer> supportStrengths = new HashMap<Order, Integer>();
		Set<Order> successfulSupportOrders = TurnOrders.getOrdersByType(OrderType.SUPPORT, ordersByStatus.get(OrderStatus.SUCCESSFUL));
		for(Order supportingOrder: successfulSupportOrders){
			Order supportedOrder = TurnOrders.getOrderForSupportingOrder((SupportingOrder)supportingOrder);
			if (movingOrders.contains(supportedOrder)){
				if (supportStrengths.get(supportedOrder) == null) {
					supportStrengths.put(supportedOrder, 1);
				}
				else {
					supportStrengths.put(supportedOrder, supportStrengths.get(supportedOrder) + 1);
				}
			}
		}
		
		
		return OrderStatus.UNKNOWN;
		
	}
}
