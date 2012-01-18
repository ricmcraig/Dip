package net.craigrm.dip.map;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Diplomacy map as a collection of 
 * {@link Provincesnet.craigrm.dip.map.Province}. 
 * Provides convenience methods to return collections of Province 
 * {@link net.cragirm.dip.map.Identifier} by terrain type.
 * <p>
 * Holds the non-stateful elements of the game. Can be likened 
 * to the empty game board, showing the playing map but no unit pieces.
 * 
 * @author Ric Craig
 * 
 */
public class DipMap {
	private final HashMap<Identifier, Province> dipMap;
	private final HashSet<Identifier> seaProvinces;
	private final HashSet<Identifier> inlandProvinces;
	private final HashSet<Identifier> coastalProvinces;

/**
 * Constructs a DipMap instance from a mapper. The mapper is responsible 
 * for extracting the Province data from a map definition of whatever form.  
 * 
 * @param mapper an {@link net.craigrm.dip.map.IMapper} implementation that 
 * extracts Province data from a map definition.
 */
	public DipMap(IMapper mapper){
		dipMap = new HashMap<Identifier, Province>();
		seaProvinces = new HashSet<Identifier>();
		inlandProvinces = new HashSet<Identifier>();
		coastalProvinces = new HashSet<Identifier>();
		for(Province p: mapper.getProvinces()) {
			addProvince(p);
		}
	}

/**
 * 	
 * @return an unmodifiable view of the set of Province Identifiers that 
 * make up the sea areas of the map. 
 */
	public Set<Identifier> getSeaProvinces(){
		return Collections.unmodifiableSet(seaProvinces);
	}

/**
 * 	
 * @return an unmodifiable view of the set of Province Identifiers that 
 * make up the land areas of the map that do not border sea areas. 
 */
	public Set<Identifier> getInlandProvinces(){
		return Collections.unmodifiableSet(inlandProvinces);
	}
	
/**
 * 	
 * @return an unmodifiable view of the set of Province Identifiers that 
 * make up the land areas of the map that border sea areas. 
 */
	public Set<Identifier> getCoastalProvinces(){
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
