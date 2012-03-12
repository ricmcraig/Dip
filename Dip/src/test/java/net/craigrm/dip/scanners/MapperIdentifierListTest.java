package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.map.ProvinceIdentifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MapperIdentifierListTest {

	private static final String NO_ALIAS_MAP_FILENAME = "./src/test/resources/maps/noAliases.dip";
	private static final String IDENTIFIER_NAME = "ABC";

	private static final String NO_NEIGHBOUR_MAP_FILENAME = "./src/test/resources/maps/noNeighbours.dip";

	private static final String SINGLE_COASTAL_ALIAS_MAP_FILENAME = "./src/test/resources/maps/singleCoastalAlias.dip";

	private static final String SINGLE_COASTAL_NEIGHBOUR_MAP_FILENAME = "./src/test/resources/maps/singleCoastalNeighbour.dip";

	private static final String SEVERAL_ALIASES_MAP_FILENAME = "./src/test/resources/maps/severalAliases.dip";

	private static final String SEVERAL_NEIGHBOURS_MAP_FILENAME = "./src/test/resources/maps/severalNeighbours.dip";

	
	private final File inputTestMapFile;
	private final String expectedProvinceIdentifierName;
	
	public MapperIdentifierListTest(File inputTestMapFile, String expectedProvinceIdentifierName) {
		this.inputTestMapFile = inputTestMapFile;
		this.expectedProvinceIdentifierName = expectedProvinceIdentifierName;
	}

	@Parameters
	public static Collection<Object[]> getTestParams() {
		return Arrays.asList(new Object[][]{
			{new File(NO_ALIAS_MAP_FILENAME), IDENTIFIER_NAME},
			{new File(NO_NEIGHBOUR_MAP_FILENAME), IDENTIFIER_NAME},
			{new File(SINGLE_COASTAL_ALIAS_MAP_FILENAME), IDENTIFIER_NAME},
			{new File(SINGLE_COASTAL_NEIGHBOUR_MAP_FILENAME), IDENTIFIER_NAME},
			{new File(SEVERAL_ALIASES_MAP_FILENAME), IDENTIFIER_NAME},
			{new File(SEVERAL_NEIGHBOURS_MAP_FILENAME), IDENTIFIER_NAME}			
		});
	}
	
	@Test
	public void getProvinceWithIdentifierList() {
		StandardMapper standardMapper = new StandardMapper(inputTestMapFile);
		DipMap.makeMap(standardMapper);
		DipMap dm = DipMap.getMap();
		assertNotNull("Check province",dm.getProvince(new ProvinceIdentifier(expectedProvinceIdentifierName)));
	}
}
