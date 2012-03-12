package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import net.craigrm.dip.map.DuplicateProvinceIdentifierException;
import net.craigrm.dip.map.MapDefinitionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MapperDuplicateIdentifiersTest {

	private static final String DUPLICATE_PROVINCE_MAP_FILENAME = "./src/test/resources/maps/duplicateProvince.dip";
	private static final int DUPLICATE_PROVINCE_LINE_NUMBER = 83;
	private static final String DUPLICATE_IDENTIFIER_NAME = "ABC";

	private static final String DUPLICATE_ALIAS_MAP_FILENAME = "./src/test/resources/maps/duplicateAlias.dip";
	private static final int DUPLICATE_ALIAS_LINE_NUMBER = 1;
	
	private static final String DUPLICATE_NEIGHBOUR_MAP_FILENAME = "./src/test/resources/maps/duplicateNeighbour.dip";
	private static final int DUPLICATE_NEIGHBOUR_LINE_NUMBER = 1;
	
	private final File inputTestMapFile;
	private final int expectedRecordNumber;
	private final String expectedDuplicateIdentifierName;
	
	public MapperDuplicateIdentifiersTest(File inputTestMapFile, int expectedRecordNumber, String expectedDuplicateIdentifierName) {
		this.inputTestMapFile = inputTestMapFile;
		this.expectedRecordNumber = expectedRecordNumber;
		this.expectedDuplicateIdentifierName = expectedDuplicateIdentifierName;
	}

	@Parameters
	public static Collection<Object[]> getTestParams() {
		return Arrays.asList(new Object[][]{
			{new File(DUPLICATE_PROVINCE_MAP_FILENAME), DUPLICATE_PROVINCE_LINE_NUMBER, DUPLICATE_IDENTIFIER_NAME},
			{new File(DUPLICATE_ALIAS_MAP_FILENAME), DUPLICATE_ALIAS_LINE_NUMBER, DUPLICATE_IDENTIFIER_NAME},
			{new File(DUPLICATE_NEIGHBOUR_MAP_FILENAME), DUPLICATE_NEIGHBOUR_LINE_NUMBER, DUPLICATE_IDENTIFIER_NAME}			
		});
	}
	
	@Test
	public void duplicateIdentifierMap() {
		boolean mdeCaught = false;
		try {
			new StandardMapper(inputTestMapFile);
		}
		catch (MapDefinitionException mde) {
			mdeCaught = true;
			assertEquals("Definition Identifier", inputTestMapFile.getAbsolutePath(), mde.getMapDefinitionIdentifier());
			assertEquals("Record Number", expectedRecordNumber, mde.getRecordNumber());
			assertEquals("Underlying cause", DuplicateProvinceIdentifierException.class, mde.getCause().getClass());
			DuplicateProvinceIdentifierException cause = (DuplicateProvinceIdentifierException)mde.getCause();
			assertEquals("Duplicate Identifier", expectedDuplicateIdentifierName, cause.getDuplicateIdentifier());
		}
		if (!mdeCaught) {
			fail("Expected to catch MapDefinitionException");
		}
	}

}
