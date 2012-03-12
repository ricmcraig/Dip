package net.craigrm.dip.orders;

import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.state.Unit;

public class SupportingOrder extends Order {

	private final Unit supportedUnit;
	private final OrderType supportedOrderType;
	
	public SupportingOrder(String orderText, boolean wellFormed, Unit unit, OrderType orderType, Unit supportedUnit, OrderType supportedOrderType, ProvinceIdentifier destination) {
		super(orderText, wellFormed, unit, orderType, destination);
		this.supportedUnit = supportedUnit;
		this.supportedOrderType = supportedOrderType;
	}

	public Unit getSupportedUnit() {
		return supportedUnit;
	}

	public OrderType getSupportedOrderType() {
		return supportedOrderType;
	}
	
}
