package net.craigrm.dip.orders;

import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.state.Unit;

public class SupportingOrder extends Order {

	Unit supportedUnit;
	OrderType supportedOrderType;
	
	public SupportingOrder(Unit unit, OrderType orderType, Unit supportedUnit, OrderType supportedOrderType, Identifier destination){
		super(unit, orderType, destination);
		this.supportedUnit = supportedUnit;
		this.supportedOrderType = supportedOrderType;
		
	}
}
