package net.craigrm.dip.orders;

import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.state.Unit;

public class Order {
	Unit unit;
	OrderType orderType;
	Identifier destination;

	public Order(Unit unit, OrderType orderType, Identifier destination){
		this.unit = unit;
		this.orderType = orderType;
		this.destination = destination;
	}
	
}
