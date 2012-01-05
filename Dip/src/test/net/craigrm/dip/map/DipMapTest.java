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
import net.craigrm.dip.state.Unit;

import org.junit.Test;


public class DipMapTest {
	
	private static final String SINGLE_SEA_PROVINCE_FILENAME = "./mapstest/singleSeaProvince.dip";
	private static final String SINGLE_COASTAL_PROVINCE_FILENAME = "./mapstest/singleCoastalProvince.dip";
	private static final String SINGLE_INLAND_PROVINCE_FILENAME = "./mapstest/singleInlandProvince.dip";

	@Test
	public void constructorInitialisesProvinceSets() {
		DipMap dm = new DipMap(new stubMapper());
		assertTrue("Should initialise sea provinces set",dm.getSeaProvincesCopy() !=null && dm.getSeaProvincesCopy().isEmpty());
		assertTrue("Should initialise inland provinces set",dm.getInlandProvincesCopy() !=null && dm.getInlandProvincesCopy().isEmpty());
		assertTrue("Should initialise coastal provinces set",dm.getCoastalProvincesCopy() !=null && dm.getCoastalProvincesCopy().isEmpty());
	}

	@Test
	public void addSingleSeaProvince() {
		final String PROVINCE_NAME = "ABC";
		Province sp = makeProvince(Terrains.SEA, PROVINCE_NAME);
		DipMap dm = new DipMap(new stubMapper(sp));
		assertTrue("Should be one sea province",dm.getSeaProvincesCopy() != null && dm.getSeaProvincesCopy().size() == 1);
		assertTrue("Should be no inland provinces",dm.getInlandProvincesCopy() != null && dm.getInlandProvincesCopy().isEmpty());
		assertTrue("Should be no coastal provinces",dm.getCoastalProvincesCopy() != null && dm.getCoastalProvincesCopy().isEmpty());
		assertTrue("Sea province should be the one added", dm.getSeaProvincesCopy().contains(sp.getIdentifier()));
	}

	@Test
	public void addSingleInlandProvince() {
		final String PROVINCE_NAME = "ABC";
		Province sp = makeProvince(Terrains.INLAND, PROVINCE_NAME);
		DipMap dm = new DipMap(new stubMapper(sp));
		assertTrue("Should be no sea province",dm.getSeaProvincesCopy() != null && dm.getSeaProvincesCopy().isEmpty());
		assertTrue("Should be no inland provinces",dm.getInlandProvincesCopy() != null && dm.getInlandProvincesCopy().size() == 1);
		assertTrue("Should be one coastal provinces",dm.getCoastalProvincesCopy() != null && dm.getCoastalProvincesCopy().isEmpty());
		assertTrue("Inland province should be the one added", dm.getInlandProvincesCopy().contains(sp.getIdentifier()));
	}

	@Test
	public void addSingleCoastalProvince() {
		final String PROVINCE_NAME = "ABC";
		Province sp = makeProvince(Terrains.COAST, PROVINCE_NAME);
		DipMap dm = new DipMap(new stubMapper(sp));
		assertTrue("Should be no sea province",dm.getSeaProvincesCopy() != null && dm.getSeaProvincesCopy().isEmpty());
		assertTrue("Should be no inland provinces",dm.getInlandProvincesCopy() != null && dm.getInlandProvincesCopy().isEmpty());
		assertTrue("Should be one coastal provinces",dm.getCoastalProvincesCopy() != null && dm.getCoastalProvincesCopy().size() == 1);
		assertTrue("Coastal province should be the one added", dm.getCoastalProvincesCopy().contains(sp.getIdentifier()));
	}

	private Province makeProvince(Terrains type, String id){
		Province sp = new Province(new Identifier(id), type, Supply.NONE, Powers.AUSTRIAHUNGARY, "Any Name", new Aliases("()"), new Neighbours("()"));
		return sp;
	}
	
	final class stubMapper implements IMapper {

		Set<Province> stubProvinces = new HashSet<Province>();
		
		public stubMapper(){
		}
		
		public stubMapper(Province p){
			stubProvinces.add(p);
		}
		
		public Set<Province> getProvinces() {
			return stubProvinces;
		}
		
		public Set<Unit> getStartPosition() {
			return null;
		}
		
	}

}
