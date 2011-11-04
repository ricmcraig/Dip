package map;
import java.util.HashMap;
import java.util.HashSet;

import map.properties.Terrains;
public class DipMap {

	private HashMap<Identifier, Province> dipMap;
	private HashSet<Identifier> seaProvinces;
	private HashSet<Identifier> inlandProvinces;
	private HashSet<Identifier> coastalProvinces;

	public DipMap(){
		dipMap = new HashMap<Identifier, Province>();
		seaProvinces = new HashSet<Identifier>();
		inlandProvinces = new HashSet<Identifier>();
		coastalProvinces = new HashSet<Identifier>();
	}
	
	public void addProvince(Province province){
		dipMap.put(province.getIdentifier(), province);
		if (province.getType() == Terrains.SEA){
			seaProvinces.add(province.getIdentifier());
		} else {
			inlandProvinces.add(province.getIdentifier());
		}
	}
	
	public void resolveLandTypes(){
		for(Identifier i:inlandProvinces){
			if (dipMap.get(i).getNeighbours().containsAny(seaProvinces)){
				dipMap.get(i).setType(Terrains.COAST);
				coastalProvinces.add(i);
			}
		}
		inlandProvinces.removeAll(coastalProvinces);
		System.out.println("Sea Provinces: " +seaProvinces);
		System.out.println("Inland Provinces: " +inlandProvinces);
		System.out.println("Coastal Provinces: " +coastalProvinces);
	
	}
	
	public HashSet<Identifier> getSeaProvinces(){
		return new HashSet<Identifier>(seaProvinces);
	}
	public HashSet<Identifier> getInlandProvinces(){
		return new HashSet<Identifier>(inlandProvinces);
	}
	public HashSet<Identifier> getCoastalProvinces(){
		return new HashSet<Identifier>(coastalProvinces);
	}
}
