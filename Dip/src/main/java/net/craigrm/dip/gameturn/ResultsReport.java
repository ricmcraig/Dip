package net.craigrm.dip.gameturn;

import java.util.Collections;
import java.util.Map;

import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.state.TurnIdentifier;

class ResultsReport {

	enum State {
		PROCESSING,
		RESOLVED,
		FAILED;
	}
	
	private State resultState = State.PROCESSING;
	private TurnIdentifier turnID;
	private Map<Powers,Map<String,Result>> results = Collections.emptyMap();
	
	ResultsReport (IOrdersDataSource ods, IPositionDataSource pds) {
		// Generate a set of result holders from the union of the orders and the position data.
		// We want to report a result for every order (even for orders which cannot be parsed) 
		// and for units that had no explicit orders.
		
		//TODO This may not work as duplicate orders should appear in the results, but may be removed when added to the orders set.
		ods.getOrders();
	}
	
	public State getResultState() {
		return resultState;
	}
	
	public TurnIdentifier getTurnID() {
		return turnID;
	}

	public boolean isReportReady() {
		return resultState != State.PROCESSING;
	}
	
	public Map<Powers, Map<String, Result>> getResults() {
		if (resultState == State.PROCESSING) {
			throw new IllegalStateException("Cannot get results while state is " + State.PROCESSING + ".");
		}
			
	//TODO Return unmodifiable copy
		return results;
	}
	
}
