package net.craigrm.dip.map;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import net.craigrm.dip.map.Aliases;
import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.map.IMapper;
import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.map.Neighbours;
import net.craigrm.dip.map.Province;
import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.map.properties.Supply;
import net.craigrm.dip.map.properties.Terrains;
import net.craigrm.dip.scanners.BracketedCSVScanner;
import net.craigrm.dip.state.Unit;

import org.junit.Test;


public class DipMapTest {
	
	private static final String SINGLE_SEA_PROVINCE_FILENAME = "./mapstest/singleSeaProvince.dip";
	private static final String SINGLE_COASTAL_PROVINCE_FILENAME = "./mapstest/singleCoastalProvince.dip";
	private static final String SINGLE_INLAND_PROVINCE_FILENAME = "./mapstest/singleInlandProvince.dip";
	private static final Aliases EMPTY_ALIASES = new Aliases(new BracketedCSVScanner("()"));
	private static final Neighbours EMPTY_NEIGHBOURS = new Neighbours(new BracketedCSVScanner("()"));

	@Test
	public void constructorInitialisesProvinceSets() {
		DipMap dm = new DipMap(new MapperStub());
		assertTrue("Should initialise sea provinces set",dm.getSeaProvinces() !=null && dm.getSeaProvinces().isEmpty());
		assertTrue("Should initialise inland provinces set",dm.getInlandProvinces() !=null && dm.getInlandProvinces().isEmpty());
		assertTrue("Should initialise coastal provinces set",dm.getCoastalProvinces() !=null && dm.getCoastalProvinces().isEmpty());
	}

	@Test
	public void addSingleSeaProvince() {
		final String PROVINCE_NAME = "ABC";
		Province sp = makeProvince(Terrains.SEA, PROVINCE_NAME);
		DipMap dm = new DipMap(new MapperStub(sp));
		assertTrue("Should be one sea province",dm.getSeaProvinces() != null && dm.getSeaProvinces().size() == 1);
		assertTrue("Should be no inland provinces",dm.getInlandProvinces() != null && dm.getInlandProvinces().isEmpty());
		assertTrue("Should be no coastal provinces",dm.getCoastalProvinces() != null && dm.getCoastalProvinces().isEmpty());
		assertTrue("Sea province should be the one added", dm.getSeaProvinces().contains(sp.getIdentifier()));
	}

	@Test
	public void addSingleInlandProvince() {
		final String PROVINCE_NAME = "ABC";
		Province ip = makeProvince(Terrains.INLAND, PROVINCE_NAME);
		DipMap dm = new DipMap(new MapperStub(ip));
		assertTrue("Should be no sea province",dm.getSeaProvinces() != null && dm.getSeaProvinces().isEmpty());
		assertTrue("Should be no inland provinces",dm.getInlandProvinces() != null && dm.getInlandProvinces().size() == 1);
		assertTrue("Should be one coastal provinces",dm.getCoastalProvinces() != null && dm.getCoastalProvinces().isEmpty());
		assertTrue("Inland province should be the one added", dm.getInlandProvinces().contains(ip.getIdentifier()));
	}

	@Test
	public void addSingleCoastalProvince() {
		final String PROVINCE_NAME = "ABC";
		Province cp = makeProvince(Terrains.COAST, PROVINCE_NAME);
		DipMap dm = new DipMap(new MapperStub(cp));
		assertTrue("Should be no sea province",dm.getSeaProvinces() != null && dm.getSeaProvinces().isEmpty());
		assertTrue("Should be no inland provinces",dm.getInlandProvinces() != null && dm.getInlandProvinces().isEmpty());
		assertTrue("Should be one coastal provinces",dm.getCoastalProvinces() != null && dm.getCoastalProvinces().size() == 1);
		assertTrue("Coastal province should be the one added", dm.getCoastalProvinces().contains(cp.getIdentifier()));
	}

	private Province makeProvince(Terrains type, String id){
		Province sp = new Province(new Identifier(id), type, Supply.NONE, Powers.NONE, "Any Name", EMPTY_ALIASES, EMPTY_NEIGHBOURS);
		return sp;
	}
	
	final class MapperStub implements IMapper {

		Set<Province> stubProvinces = new HashSet<Province>();
		
		public MapperStub(){
		}
		
		public MapperStub(Province p){
			stubProvinces.add(p);
		}
		
		public Set<Province> getProvinces() {
			return stubProvinces;
		}
	}

}
