package map;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import map.properties.Terrains;
public class DipMap {

	private HashMap<Identifier, Province> dipMap;
	private HashSet<Identifier> seaProvinces;
	private HashSet<Identifier> inlandProvinces;
	private HashSet<Identifier> coastalProvinces;

	public DipMap(IMapper mapper){
		dipMap = new HashMap<Identifier, Province>();
		seaProvinces = new HashSet<Identifier>();
		inlandProvinces = new HashSet<Identifier>();
		coastalProvinces = new HashSet<Identifier>();
		for(Province p: mapper.getProvinces()) {
			addProvince(p);
		}
	}
	
	public Set<Identifier> getSeaProvincesCopy(){
		return Collections.unmodifiableSet(seaProvinces);
	}
	public Set<Identifier> getInlandProvincesCopy(){
		return Collections.unmodifiableSet(inlandProvinces);
	}
	public Set<Identifier> getCoastalProvincesCopy(){
		return Collections.unmodifiableSet(coastalProvinces);
	}

	private void addProvince(Province province){
		dipMap.put(province.getIdentifier(), province);
		switch(province.getType()) { 
			case SEA: {
				seaProvinces.add(province.getIdentifier());
				break;
			}
			case INLAND: {
				inlandProvinces.add(province.getIdentifier());
				break;
			}
			case COAST: {
				coastalProvinces.add(province.getIdentifier());
				break;
			}
		}
	}
	
}
