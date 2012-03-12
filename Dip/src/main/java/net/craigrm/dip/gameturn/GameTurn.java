package net.craigrm.dip.gameturn;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.map.IMapDataSource;
import net.craigrm.dip.orders.Order;
import net.craigrm.dip.scanners.StandardMapper;
import net.craigrm.dip.scanners.StandardOrdersParser;
import net.craigrm.dip.scanners.StandardPositionParser;
import net.craigrm.dip.state.TurnState;

public class GameTurn {

	private Set<Order> orders;
	private Map<Order, Outcome> actions;
	
	public GameTurn(String mapFile, String positionFile, String ordersFile, String resultsFile) {
		IMapDataSource standardMapper = new StandardMapper(mapFile);
		IPositionDataSource standardPositionParser = new StandardPositionParser(positionFile);
		IOrdersDataSource standardOrdersParser = new StandardOrdersParser(ordersFile);
		DipMap.makeMap(standardMapper);
		TurnState.setTurnState(standardPositionParser);
		orders = standardOrdersParser.getOrders();
		actions = new LinkedHashMap<Order, Outcome>();
		for(Order o: orders) {
				actions.put(o, new Outcome(o));
		}
	}
	
	void generateNewPosition() {
		
	}
}
