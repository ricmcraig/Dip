package net.craigrm.dip.gameturn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.map.IMapDataSource;
import net.craigrm.dip.orders.Order;
import net.craigrm.dip.orders.TurnOrders;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.scanners.StandardMapper;
import net.craigrm.dip.scanners.StandardOrdersParser;
import net.craigrm.dip.scanners.StandardPositionParser;
import net.craigrm.dip.state.TurnState;

public class GameTurn {

	private Map<Order, Outcome> results;
	
	public GameTurn(String mapFile, String positionFile, String ordersFile, String resultsFile) {
		IMapDataSource mds = new StandardMapper(mapFile);
		IPositionDataSource pds = new StandardPositionParser(positionFile);
		IOrdersDataSource ods = new StandardOrdersParser(ordersFile);
		
		DipMap.makeMap(mds);
		TurnState.setTurnState(pds);
		TurnOrders.setTurnOrders(ods);
		
		Map<OrderStatus, Set<Order>> ordersByStatus = new HashMap<OrderStatus, Set<Order>>();
		ordersByStatus.put(OrderStatus.BADLYFORMED, new HashSet<Order>());
		ordersByStatus.put(OrderStatus.ILLEGAL, new HashSet<Order>());
		ordersByStatus.put(OrderStatus.FAILED, new HashSet<Order>());
		ordersByStatus.put(OrderStatus.SUCCESSFUL, new HashSet<Order>());
		ordersByStatus.put(OrderStatus.UNKNOWN, new HashSet<Order>());
		
		
		results = new LinkedHashMap<Order, Outcome>();
		// First pass of orders: assign outcomes to mark badly formed or illegal orders.
		// These can be ignored in subsequent processing.
		for(Order order: TurnOrders.getOrders()) {
			Outcome outcome =new Outcome(order); 
			results.put(order, outcome);
			ordersByStatus.get(outcome.getOrderState()).add(order);
		}
		
		// Second pass: detect and mark failed support orders.
		Set<Order> unresolvedSupportOrders = TurnOrders.getOrdersByType(OrderType.SUPPORT, ordersByStatus.get(OrderStatus.UNKNOWN));
		for(Order order: unresolvedSupportOrders) {
			Outcome outcome =new Outcome(order, ordersByStatus.get(OrderStatus.UNKNOWN)); 
			
			
			
			
		}
		
	}
	
	void generateNewPosition() {
		
	}
}
