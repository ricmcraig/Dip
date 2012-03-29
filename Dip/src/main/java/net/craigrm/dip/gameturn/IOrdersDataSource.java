package net.craigrm.dip.gameturn;

import java.util.Set;

import net.craigrm.dip.orders.Adjustment;
import net.craigrm.dip.orders.Order;
import net.craigrm.dip.state.TurnIdentifier;

public interface IOrdersDataSource {
	public TurnIdentifier getTurnID();
	public Set<Order> getOrders();
	public Set<Adjustment> getAdjustments();

}
