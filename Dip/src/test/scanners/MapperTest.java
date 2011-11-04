package scanners;

import static org.junit.Assert.*;

import map.DipMap;

import org.junit.Test;

public class MapperTest {

	private static final String STANDARD_MAP_FILENAME = "./mapstest/standardtest.dip";
	private static final String SPAIN_COASTAL_MAP_FILENAME = "./mapstest/spaintest.dip";
	private static final int STANDARD_MAP_SEA_PROVINCES_NUMBER = 19;
	private static final int STANDARD_MAP_INLAND_PROVINCES_NUMBER = 14;
	private static final int STANDARD_MAP_COASTAL_PROVINCES_NUMBER = 48;

	
	@Test
	public void createStandardMap() {
		Mapper mapper = new Mapper(STANDARD_MAP_FILENAME);
		DipMap dipMap = new DipMap();
		mapper.constructMap(dipMap);
		dipMap.resolveLandTypes();
		assertEquals("Check sea provinces",STANDARD_MAP_SEA_PROVINCES_NUMBER,dipMap.getSeaProvinces().size());
		assertEquals("Check inland provinces",STANDARD_MAP_INLAND_PROVINCES_NUMBER,dipMap.getInlandProvinces().size());
		assertEquals("Check coastal provinces",STANDARD_MAP_COASTAL_PROVINCES_NUMBER, dipMap.getCoastalProvinces().size());
	}

	@Test
	public void testSpainCoastal() {
		Mapper mapper = new Mapper(SPAIN_COASTAL_MAP_FILENAME);
		DipMap dipMap = new DipMap();
		mapper.constructMap(dipMap);
		dipMap.resolveLandTypes();
		assertEquals("Check sea provinces",STANDARD_MAP_SEA_PROVINCES_NUMBER,dipMap.getSeaProvinces().size());
		assertEquals("Check inland provinces", 0, dipMap.getInlandProvinces().size());
		assertEquals("Check coastal provinces", 1, dipMap.getCoastalProvinces().size());
		
	}
}
