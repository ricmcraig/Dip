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
