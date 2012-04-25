package net.craigrm.dip.map;

import static org.junit.Assert.*;

import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.map.Province;
import net.craigrm.dip.map.properties.Terrains;

import org.junit.Test;


public class DipMapTest {
	
	@Test
	public void constructorInitialisesProvinceSets() {
		DipMap.reloadMap(new MapperStub());
		DipMap dm = DipMap.getMap();
		assertTrue("Should initialise sea provinces set",dm.getSeaProvinces() !=null && dm.getSeaProvinces().isEmpty());
		assertTrue("Should initialise inland provinces set",dm.getInlandProvinces() !=null && dm.getInlandProvinces().isEmpty());
		assertTrue("Should initialise coastal provinces set",dm.getCoastalProvinces() !=null && dm.getCoastalProvinces().isEmpty());
	}

	@Test
	public void addSingleSeaProvince() {
		final String PROVINCE_NAME = "ABC";
		Province sp = ProvinceMaker.makeProvince(Terrains.SEA, PROVINCE_NAME);
		DipMap.reloadMap(new MapperStub(sp));
		DipMap dm = DipMap.getMap();
		assertTrue("Should be one sea province",dm.getSeaProvinces() != null && dm.getSeaProvinces().size() == 1);
		assertTrue("Should be no inland provinces",dm.getInlandProvinces() != null && dm.getInlandProvinces().isEmpty());
		assertTrue("Should be no coastal provinces",dm.getCoastalProvinces() != null && dm.getCoastalProvinces().isEmpty());
		assertTrue("Sea province should be the one added", dm.getSeaProvinces().contains(sp.getIdentifier()));
	}

	@Test
	public void addSingleInlandProvince() {
		final String PROVINCE_NAME = "ABC";
		Province ip = ProvinceMaker.makeProvince(Terrains.INLAND, PROVINCE_NAME);
		DipMap.reloadMap(new MapperStub(ip));
		DipMap dm = DipMap.getMap();
		assertTrue("Should be no sea province",dm.getSeaProvinces() != null && dm.getSeaProvinces().isEmpty());
		assertTrue("Should be no inland provinces",dm.getInlandProvinces() != null && dm.getInlandProvinces().size() == 1);
		assertTrue("Should be one coastal provinces",dm.getCoastalProvinces() != null && dm.getCoastalProvinces().isEmpty());
		assertTrue("Inland province should be the one added", dm.getInlandProvinces().contains(ip.getIdentifier()));
	}

	@Test
	public void addSingleCoastalProvince() {
		final String PROVINCE_NAME = "ABC";
		Province cp = ProvinceMaker.makeProvince(Terrains.COAST, PROVINCE_NAME);
		DipMap.reloadMap(new MapperStub(cp));
		DipMap dm = DipMap.getMap();
		assertTrue("Should be no sea province",dm.getSeaProvinces() != null && dm.getSeaProvinces().isEmpty());
		assertTrue("Should be no inland provinces",dm.getInlandProvinces() != null && dm.getInlandProvinces().isEmpty());
		assertTrue("Should be one coastal provinces",dm.getCoastalProvinces() != null && dm.getCoastalProvinces().size() == 1);
		assertTrue("Coastal province should be the one added", dm.getCoastalProvinces().contains(cp.getIdentifier()));
	}

	@Test
	public void testValidProvinceIdentifiersCorrect() {
		final String PROVINCE_NAME = "ABC";
		final String ALIASES = "DEF, GHI";
		Province cp = ProvinceMaker.makeProvinceWithAliases(Terrains.COAST, PROVINCE_NAME, ALIASES);
		DipMap.reloadMap(new MapperStub(cp));
		DipMap dm = DipMap.getMap();
		assertTrue("Should be valid province",dm.isValidProvinceIdentifier(new ProvinceIdentifier(PROVINCE_NAME)));
		assertTrue("Should be valid province",dm.isValidProvinceIdentifier(new ProvinceIdentifier("DEF")));
		assertTrue("Should be valid province",dm.isValidProvinceIdentifier(new ProvinceIdentifier("GHI")));
	}

	@Test
	public void testInvalidProvinceIdentifiersCorrect() {
		final String PROVINCE_NAME = "ABC";
		final String ALIASES = "DEF, GHI";
		Province cp = ProvinceMaker.makeProvinceWithAliases(Terrains.COAST, PROVINCE_NAME, ALIASES);
		DipMap.reloadMap(new MapperStub(cp));
		DipMap dm = DipMap.getMap();
		assertFalse("Should be invalid province",dm.isValidProvinceIdentifier(new ProvinceIdentifier("XYZ")));
	}

	@Test
	public void testNeighbour() {
		final String PROVINCE_NAME = "ABC";
		final String NEIGHBOURS = "DEF, GHI";
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, PROVINCE_NAME, NEIGHBOURS);
		Province p2 = ProvinceMaker.makeProvince(Terrains.COAST, "DEF");
		Province p3 = ProvinceMaker.makeProvince(Terrains.COAST, "GHI");
		DipMap.reloadMap(new MapperStub(p1, p2, p3));
		DipMap dm = DipMap.getMap();
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier(PROVINCE_NAME), new ProvinceIdentifier("DEF")));
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier(PROVINCE_NAME), new ProvinceIdentifier("GHI")));
	}

	@Test
	public void testAliasWithNeighbours() {
		final String PROVINCE_NAME = "ABC";
		final String ALIASES = "BCA, CAB";
		final String NEIGHBOURS = "DEF";
		Province p1 = ProvinceMaker.makeProvinceWithAliasesAndNeighbours(Terrains.COAST, PROVINCE_NAME, ALIASES, NEIGHBOURS);
		Province p2 = ProvinceMaker.makeProvince(Terrains.COAST, "DEF");
		DipMap.reloadMap(new MapperStub(p1, p2));
		DipMap dm = DipMap.getMap();
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier(PROVINCE_NAME), new ProvinceIdentifier("DEF")));
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier("BCA"), new ProvinceIdentifier("DEF")));
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier("CAB"), new ProvinceIdentifier("DEF")));
	}

	@Test
	public void testAliasedNeighbours() {
		final String PROVINCE_NAME = "ABC";
		final String NEIGHBOURS = "DEF";
		final String NEIGHBOUR_ALIASES = "EFD, FDE";
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, PROVINCE_NAME, NEIGHBOURS);
		Province p2 = ProvinceMaker.makeProvinceWithAliases(Terrains.COAST, NEIGHBOURS, NEIGHBOUR_ALIASES);
		DipMap.reloadMap(new MapperStub(p1, p2));
		DipMap dm = DipMap.getMap();
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier(PROVINCE_NAME), new ProvinceIdentifier("DEF")));
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier(PROVINCE_NAME), new ProvinceIdentifier("EFD")));
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier(PROVINCE_NAME), new ProvinceIdentifier("FDE")));
	}

	@Test
	public void testAliasWithAliasedNeighbours() {
		final String PROVINCE_NAME = "ABC";
		final String ALIASES = "BCA, CAB";
		final String NEIGHBOURS = "DEF";
		final String NEIGHBOUR_ALIASES = "EFD, FDE";
		Province p1 = ProvinceMaker.makeProvinceWithAliasesAndNeighbours(Terrains.COAST, PROVINCE_NAME, ALIASES, NEIGHBOURS);
		Province p2 = ProvinceMaker.makeProvinceWithAliases(Terrains.COAST, NEIGHBOURS, NEIGHBOUR_ALIASES);
		DipMap.reloadMap(new MapperStub(p1, p2));
		DipMap dm = DipMap.getMap();
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier("BCA"), new ProvinceIdentifier("EFD")));
		assertTrue("Should be neighbouring province",dm.isNeighbour(new ProvinceIdentifier("CAB"), new ProvinceIdentifier("FDE")));
	}

	@Test
	public void testNonNeighbour() {
		final String PROVINCE_NAME = "ABC";
		final String NEIGHBOURS = "DEF, GHI";
		final String NON_NEIGHBOUR = "XYZ";
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, PROVINCE_NAME, NEIGHBOURS);
		Province p2 = ProvinceMaker.makeProvince(Terrains.COAST, NON_NEIGHBOUR);
		DipMap.reloadMap(new MapperStub(p1, p2));
		DipMap dm = DipMap.getMap();
		assertFalse("Should not be neighbouring province",dm.isNeighbour(new ProvinceIdentifier(PROVINCE_NAME), new ProvinceIdentifier(NON_NEIGHBOUR)));
	}

}
