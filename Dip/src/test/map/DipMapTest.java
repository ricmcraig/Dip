package map;

import static org.junit.Assert.*;

import map.properties.Terrains;

import org.junit.Test;

public class DipMapTest {
	
	private static final String SINGLE_SEA_PROVINCE_FILENAME = "./mapstest/singleSeaProvince.dip";
	private static final String SINGLE_COASTAL_PROVINCE_FILENAME = "./mapstest/singleCoastalProvince.dip";
	private static final String SINGLE_INLAND_PROVINCE_FILENAME = "./mapstest/singleInlandProvince.dip";

	@Test
	public void constructorInitialisesProvinceSets() {
		DipMap dm = new DipMap();
		assertTrue("Should initialise sea provinces set",dm.getSeaProvincesCopy() !=null && dm.getSeaProvincesCopy().isEmpty());
		assertTrue("Should initialise inland provinces set",dm.getInlandProvincesCopy() !=null && dm.getInlandProvincesCopy().isEmpty());
		assertTrue("Should initialise coastal provinces set",dm.getCoastalProvincesCopy() !=null && dm.getCoastalProvincesCopy().isEmpty());
	}

	@Test
	public void addSingleSeaProvince() {
		final String PROVINCE_NAME = "ABC";
		DipMap dm = new DipMap();
		Province sp = createProvince(Terrains.SEA, PROVINCE_NAME);
		dm.addProvince(sp);
		assertTrue("Should be one sea province",dm.getSeaProvincesCopy() != null && dm.getSeaProvincesCopy().size() == 1);
		assertTrue("Should be no inland provinces",dm.getInlandProvincesCopy() != null && dm.getInlandProvincesCopy().isEmpty());
		assertTrue("Should be no coastal provinces",dm.getCoastalProvincesCopy() != null && dm.getCoastalProvincesCopy().isEmpty());
		assertTrue("Sea province should be the one added", dm.getSeaProvincesCopy().contains(sp.getIdentifier()));
	}

	@Test
	public void addSingleInlandProvince() {
		final String PROVINCE_NAME = "ABC";
		DipMap dm = new DipMap();
		Province sp = createProvince(Terrains.INLAND, PROVINCE_NAME);
		dm.addProvince(sp);
		assertTrue("Should be no sea province",dm.getSeaProvincesCopy() != null && dm.getSeaProvincesCopy().isEmpty());
		assertTrue("Should be no inland provinces",dm.getInlandProvincesCopy() != null && dm.getInlandProvincesCopy().size() == 1);
		assertTrue("Should be one coastal provinces",dm.getCoastalProvincesCopy() != null && dm.getCoastalProvincesCopy().isEmpty());
		assertTrue("Inland province should be the one added", dm.getInlandProvincesCopy().contains(sp.getIdentifier()));
	}

	@Test
	public void addSingleCoastalProvince() {
		final String PROVINCE_NAME = "ABC";
		DipMap dm = new DipMap();
		Province sp = createProvince(Terrains.COAST, PROVINCE_NAME);
		dm.addProvince(sp);
		assertTrue("Should be no sea province",dm.getSeaProvincesCopy() != null && dm.getSeaProvincesCopy().isEmpty());
		assertTrue("Should be no inland provinces",dm.getInlandProvincesCopy() != null && dm.getInlandProvincesCopy().isEmpty());
		assertTrue("Should be one coastal provinces",dm.getCoastalProvincesCopy() != null && dm.getCoastalProvincesCopy().size() == 1);
		assertTrue("Coastal province should be the one added", dm.getCoastalProvincesCopy().contains(sp.getIdentifier()));
	}

	private Province createProvince(Terrains type, String id){
		Province sp = new Province();
		sp.setIdentifier(id);
		sp.setType(type);
		return sp;
	}
}
