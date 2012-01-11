package net.craigrm.dip.gameturn;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.scanners.StandardMapper;
import net.craigrm.dip.scanners.StandardPositionParser;
import net.craigrm.dip.state.TurnState;

public class GameTurn {

	private DipMap dm;
	private TurnState ts;
// TODO	private Orders orders;
	
	public GameTurn(String mapFile, String positionFile, String ordersFile, String resultsFile){
		StandardMapper standardMapper = new StandardMapper(mapFile);
		StandardPositionParser standardPositionParser = new StandardPositionParser(positionFile);
// TODO		StandardOrdersParser standardOrdersParser = new StandardOrdersParser(ordersFile);
		standardMapper.parseGameDefinition();
		dm = new DipMap(standardMapper);
		ts = new TurnState(standardPositionParser);
	}
	
	void generateNewPosition(){
		
	}
}
