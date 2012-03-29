package net.craigrm.dip.orders;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.craigrm.dip.gameturn.IOrdersDataSource;
import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.state.TurnIdentifier;

/**
 * Represents the collection of orders and adjustments provided by all 
 * players for a given turn. 
 * Provides methods to return subsets of the orders. 
 * 
 * @author Ric Craig
 * 
 */
public class TurnOrders {

	private static TurnOrders turnOrders;
	
	private final TurnIdentifier turnID;
	private final Set<Order> orders;
	private final Set<Adjustment> adjustments;

	//Cached subsets of orders lazily instantiated
	private Map<OrderType, Set<Order>> ordersByType; 

	
	/**
	 * Constructs a new TurnOrders instance from a Orders data source if there 
	 * is no current TurnOrders instance.  
	 * 
	 * @param ods an {@link net.craigrm.dip.state.IOrdersDataSource IOrdersDataSource}
	 *  implementation that extracts unit position data from a position definition.
	 */
	public static void setTurnOrders(IOrdersDataSource ods) {
		if (turnOrders == null) {
			turnOrders = new TurnOrders(ods);
		}
	}

	private TurnOrders(IOrdersDataSource ods) {
		this.turnID = ods.getTurnID();
		this.orders = ods.getOrders();
		this.adjustments = ods.getAdjustments();
		this.ordersByType = Collections.emptyMap(); 
	}


	/**
	 * Returns turnID associated with these orders
	 * @return the unique identifier for this turn
	 */
	public static TurnIdentifier getTurnIdentifier() {
		return turnOrders.turnID;
	}
	
	/**
	 * Returns a view of all the orders for this turn.
	 * @return an unmodifiable set of the player orders for this turn
	 */
	public static Set<Order> getOrders() {
		return Collections.unmodifiableSet(turnOrders.orders);
	}

	/**
	 * Returns a view of all the adjustments for this turn.
	 * @return an unmodifiable set of the player adjustments for this turn
	 */
	public static Set<Adjustment> getAdjustments() {
		return Collections.unmodifiableSet(turnOrders.adjustments);
	}
	
	/**
	 * Returns a view of all the orders of a given {@link net.craigrm.dip.orders.properties.OrderType OrderType} 
	 * for this turn.
	 * @param orderType the type of the order (e.g. HOLD, MOVE)
	 * @return an unmodifiable set of the player orders of the specified type for this turn
	 */
	public static Set<Order> getOrdersByType(OrderType orderType) {
		if (!turnOrders.ordersByType.containsKey(orderType)) {
			Set<Order> ordersOfAType = new HashSet<Order>();
			for(Order o:turnOrders.orders) {
				if (o.getOrderType() == orderType) {
					ordersOfAType.add(o);
				}
			}
			turnOrders.ordersByType.put(orderType, Collections.unmodifiableSet(ordersOfAType));
		}
		return turnOrders.ordersByType.get(orderType);
	}

	/**
	 * Returns a view of all the orders of a given {@link net.craigrm.dip.orders.properties.OrderType OrderType} 
	 * matching a given filter for this turn.
	 * @param orderType the type of the order (e.g. HOLD, MOVE)
	 * @param filter constrains the return values to a subset of this set  
	 * @return an unmodifiable set of the player orders of the specified type for this turn
	 */
	public static Set<Order> getOrdersByType(OrderType orderType, Set<Order> filter) {
		Set<Order> filteredOrders = getOrdersByType(orderType);
		filteredOrders.retainAll(filter);
		return Collections.unmodifiableSet(filteredOrders);
	}

	/**
	 * Returns a set of the shortest distinct convoy routes for the given order drawn from the given set of 
	 * candidate orders.<p>
	 * Convoy routes A, B, C and A, C are not distinct: only the shorter route A, C is returned. <p>
	 * Convoy routes A, B, D and A, C, D are distinct.<p>
	 * The given order is expected to be a move order. If it is not, no convoy routes are returned. 
	 * 
	 * @param order the order that is being convoyed.
	 * @param candidateOrders the set of orders that contains the convoy orders  .
	 * @return a set of lists of province identifiers. Each list represents a distinct convoy route.
	 */
	public static Set<List<ProvinceIdentifier>> getConvoyRoutesForOrder(Order order, Set<Order> candidateOrders) {
		
		Set<List<ProvinceIdentifier>> convoyRoutes = new HashSet<List<ProvinceIdentifier>>();
		
		if(!order.getOrderType().equals(OrderType.MOVE)) {
			return convoyRoutes;
		}
		
		Set<ProvinceIdentifier> convoyingProvinces = new HashSet<ProvinceIdentifier>();

		//Get all the convoy orders associated with the move order
		for(Order candidateOrder: candidateOrders) {
			if(!candidateOrder.getOrderType().equals(OrderType.CONVOY)) {
				continue;
			}
			
			SupportingOrder convoyOrder = (SupportingOrder)candidateOrder;
			
			if(convoyOrder.getSupportedUnit().equals(order.getUnit()) && 
				convoyOrder.getTarget().equals(order.getTarget())) {
				convoyingProvinces.add(convoyOrder.getUnitPosition());
			}
		}

		//Get the set of possible convoy routes
		convoyRoutes = DipMap.getMap().getConvoyRoutes(order.getUnitPosition(), order.getTarget(), convoyingProvinces);
		return convoyRoutes;
		
	}

}
