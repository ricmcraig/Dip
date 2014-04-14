package net.craigrm.dip.resolver;

import java.util.Collections;
import java.util.Set;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.state.TurnState;
import net.craigrm.dip.state.Unit;
import net.craigrm.dip.orders.Adjustment;
import net.craigrm.dip.orders.Order;
import net.craigrm.dip.orders.TurnOrders;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.gameturn.Result;

public class Resolver {

	//TODO: In anticipation of map, state and orders becoming non public
	DipMap map;
	TurnState state;
	TurnOrders orders;
	Set<Order> unitOrders;
	Set<Adjustment> adjustments;
	
	Resolver(DipMap map, TurnState state, TurnOrders orders){
		this.map = map;
		this.state = state;
		this.unitOrders = orders.getOrders();
		this.adjustments = orders.getAdjustments();
	}
	
	Set<Result> resolve() {
		
		generateMissingOrders();
		
		
		//TODO: return results
		return null;
	}
	
	void generateMissingOrders() {
		Set<Unit> unitsWithOrders = Collections.emptySet();
		
		for(Order order: unitOrders){
			unitsWithOrders.add(order.getUnit());
		}
		
		for(Unit unit:state.getUnits()){
			if(!unitsWithOrders.contains(unit)){
				unitOrders.add(makeHoldOrder(unit));
			}
		}
	}
	
	Order makeHoldOrder(Unit unit){
		return new Order("System generated HOLD", true, unit, OrderType.HOLD, unit.getCurrentPosition() );
	}
}
