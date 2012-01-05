package net.craigrm.dip.game;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.scanners.StandardMapper;
import net.craigrm.dip.state.History;

public class Game {

	private DipMap dm;
	private History h;
	
	public Game(String gameFile){
		StandardMapper standardMapper = new StandardMapper(gameFile);
		standardMapper.parseGameDefinition();
		dm = new DipMap(standardMapper);
		h = new History(standardMapper);
	}
}
