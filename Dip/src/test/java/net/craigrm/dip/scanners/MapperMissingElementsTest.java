package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import net.craigrm.dip.map.MapDefinitionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MapperMissingElementsTest {

	private static final String MISSING_IDENTIFIER_MAP_FILENAME = "./src/test/resources/maps/missingIdentifier.dip";
	private static final int MISSING_IDENTIFIER_LINE_NUMBER = 1;
	private static final String MISSING_ELEMENT_IDENTIFIER = "Identifier";

	private static final String MISSING_TERRAIN_MAP_FILENAME = "./src/test/resources/maps/missingTerrain.dip";
	private static final int MISSING_TERRAIN_LINE_NUMBER = 1;
	private static final String MISSING_ELEMENT_TERRAIN = "Terrain";
	
	private static final String MISSING_SUPPLY_MAP_FILENAME = "./src/test/resources/maps/missingSupply.dip";
	private static final int MISSING_SUPPLY_LINE_NUMBER = 1;
	private static final String MISSING_ELEMENT_SUPPLY = "Suppply";
	
	private static final String MISSING_POWER_MAP_FILENAME = "./src/test/resources/maps/missingPower.dip";
	private static final int MISSING_POWER_LINE_NUMBER = 1;
	private static final String MISSING_ELEMENT_POWER = "Power";
	
	private static final String MISSING_FULLNAME_MAP_FILENAME = "./src/test/resources/maps/missingFullName.dip";
	private static final int MISSING_FULLNAME_LINE_NUMBER = 1;
	private static final String MISSING_ELEMENT_FULLNAME = "Full Name";
	
	private static final String MISSING_ALIASES_MAP_FILENAME = "./src/test/resources/maps/missingAliases.dip";
	private static final int MISSING_ALIASES_LINE_NUMBER = 1;
	private static final String MISSING_ELEMENT_ALIASES = "Aliases";
	
	private static final String MISSING_NEIGHBOURS_MAP_FILENAME = "./src/test/resources/maps/missingNeighbours.dip";
	private static final int MISSING_NEIGHBOURS_LINE_NUMBER = 1;
	private static final String MISSING_ELEMENT_NEIGHBOURS = "Neighbours";
	
	private final File inputTestMapFile;
	private final int expectedRecordNumber;
	private final String expectedMissingElementName;
	
	public MapperMissingElementsTest(File inputTestMapFile, int expectedRecordNumber, String expectedMissingElementName) {
		this.inputTestMapFile = inputTestMapFile;
		this.expectedRecordNumber = expectedRecordNumber;
		this.expectedMissingElementName = expectedMissingElementName;
	}

	@Parameters
	public static Collection<Object[]> getTestParams(){
		return Arrays.asList(new Object[][]{
			{new File(MISSING_IDENTIFIER_MAP_FILENAME), MISSING_IDENTIFIER_LINE_NUMBER, MISSING_ELEMENT_IDENTIFIER},
			{new File(MISSING_TERRAIN_MAP_FILENAME), MISSING_TERRAIN_LINE_NUMBER, MISSING_ELEMENT_TERRAIN},
			{new File(MISSING_SUPPLY_MAP_FILENAME), MISSING_SUPPLY_LINE_NUMBER, MISSING_ELEMENT_SUPPLY},
			{new File(MISSING_POWER_MAP_FILENAME), MISSING_POWER_LINE_NUMBER, MISSING_ELEMENT_POWER},
			{new File(MISSING_FULLNAME_MAP_FILENAME), MISSING_FULLNAME_LINE_NUMBER, MISSING_ELEMENT_FULLNAME},
			{new File(MISSING_ALIASES_MAP_FILENAME), MISSING_ALIASES_LINE_NUMBER, MISSING_ELEMENT_ALIASES},
			{new File(MISSING_NEIGHBOURS_MAP_FILENAME), MISSING_NEIGHBOURS_LINE_NUMBER, MISSING_ELEMENT_NEIGHBOURS}			
		});
	}
	
	@Test
	public void testMissingElement(){
		boolean mdeCaught = false;
		File mapFile = inputTestMapFile;
		try {
			new StandardMapper(mapFile);
		}
		catch (MapDefinitionException mde){
			mdeCaught = true;
			assertEquals("Definition Identifier", mapFile.getAbsolutePath(), mde.getMapDefinitionIdentifier());
			assertEquals("Record Number", expectedRecordNumber, mde.getRecordNumber());
			assertEquals("Underlying cause", NoSuchMapElementException.class, mde.getCause().getClass());
			NoSuchMapElementException cause = (NoSuchMapElementException)mde.getCause();
			assertEquals("Duplicate Alias", expectedMissingElementName, cause.getMissingElementName());
		}
		if (!mdeCaught)
			fail("Expected to catch MapDefinitionException");
	}
}
