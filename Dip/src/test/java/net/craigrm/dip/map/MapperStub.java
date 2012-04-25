package net.craigrm.dip.map;

import java.util.HashSet;
import java.util.Set;

final class MapperStub implements IMapDataSource {

	Set<Province> stubProvinces = new HashSet<Province>();
	
	public MapperStub() {
	}
	
	public MapperStub(Province p) {
		stubProvinces.add(p);
	}
	
	public MapperStub(Province... ps) {
		for(Province p:ps) {
			stubProvinces.add(p);
		}
	}
	
	public Set<Province> getProvinces() {
		return stubProvinces;
	}
}

