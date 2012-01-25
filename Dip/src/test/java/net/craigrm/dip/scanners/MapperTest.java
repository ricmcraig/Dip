package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.scanners.StandardMapper;

import org.junit.Test;

public class MapperTest {

	private static final String STANDARD_MAP_FILENAME = "./src/test/resources/maps/standardtest.dip";
	private static final int STANDARD_MAP_SEA_PROVINCES_NUMBER = 19;
	private static final int STANDARD_MAP_INLAND_PROVINCES_NUMBER = 14;
	private static final int STANDARD_MAP_COASTAL_PROVINCES_NUMBER = 48;
	
	
	private static final String SPAIN_COASTAL_MAP_FILENAME = "./src/test/resources/maps/spaintest.dip";
	

	
	@Test
	public void createStandardMap() {
		StandardMapper standardMapper = new StandardMapper(STANDARD_MAP_FILENAME);
		DipMap dipMap = new DipMap(standardMapper);
		assertEquals("Check sea provinces",STANDARD_MAP_SEA_PROVINCES_NUMBER,dipMap.getSeaProvinces().size());
		assertEquals("Check inland provinces",STANDARD_MAP_INLAND_PROVINCES_NUMBER,dipMap.getInlandProvinces().size());
		assertEquals("Check coastal provinces",STANDARD_MAP_COASTAL_PROVINCES_NUMBER, dipMap.getCoastalProvinces().size());
	}

	@Test
	// Regression test following problem corrected in /Dip/src/test/resources/maps/standardtest.dip
	public void testSpainCoastal() {
		StandardMapper standardMapper = new StandardMapper(SPAIN_COASTAL_MAP_FILENAME);
		DipMap dipMap = new DipMap(standardMapper);
		assertEquals("Check sea provinces", STANDARD_MAP_SEA_PROVINCES_NUMBER, dipMap.getSeaProvinces().size());
		assertEquals("Check inland provinces", 0, dipMap.getInlandProvinces().size());
		assertEquals("Check coastal provinces", 1, dipMap.getCoastalProvinces().size());
	}

}
