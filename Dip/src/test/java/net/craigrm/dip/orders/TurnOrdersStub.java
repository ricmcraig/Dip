package net.craigrm.dip.orders;

import java.util.HashSet;
import java.util.Set;

import net.craigrm.dip.gameturn.IOrdersDataSource;
import net.craigrm.dip.state.TurnIdentifier;

public class TurnOrdersStub implements IOrdersDataSource {

	TurnIdentifier stubTurnID;
	Set<Order> stubOrders;
	Set<Adjustment> stubAdjustments;
	
	public TurnOrdersStub(TurnIdentifier t, Set<Order> o, Set<Adjustment> a) {
		stubTurnID = t;
		stubOrders = o;
		stubAdjustments = a;
	}
	
	public TurnOrdersStub() {
		this(new TurnIdentifier("1901S"), new HashSet<Order>(), new HashSet<Adjustment>());
	}
	
	public TurnOrdersStub(Set<Order> o, Set<Adjustment> a) {
		stubTurnID = new TurnIdentifier("1901S");
		stubOrders = o;
		stubAdjustments = a;
	}
	
	@Override
	public TurnIdentifier getTurnID() {
		return stubTurnID;
	}

	@Override
	public Set<Order> getOrders() {
		return stubOrders;
	}
	
	@Override
	public Set<Adjustment> getAdjustments() {
		return stubAdjustments;
	}

}
