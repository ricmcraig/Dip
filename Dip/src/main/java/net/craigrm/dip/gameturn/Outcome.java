package net.craigrm.dip.gameturn;

import net.craigrm.dip.orders.Order;
import net.craigrm.dip.orders.SupportingOrder;
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
		
		if (!o.hasValidDestination()) {
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
			if (!o.getUnit().canMove(o.getDestination())) {
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
				if (!o.getUnit().canSupport(o.getDestination())) {
					orderState = OrderStatus.ILLEGAL;
					detail = "Cannot support other unit from this position.";
					return;
				} 
				if (!supportedUnit.canMove(o.getDestination())) {
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
	
	public OrderStatus getOrderState() {
		return orderState;
	}

	public String getDetail() {
		return detail;
	}

}
