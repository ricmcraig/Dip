package net.craigrm.dip.map;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Diplomacy map as a collection of 
 * {@link Provincesnet.craigrm.dip.map.Province Province}. 
 * Provides convenience methods to return collections of Province 
 * {@link net.ProvinceIdentifier.dip.map.Identifier Identifier}s by terrain type.
 * <p>
 * Holds the non-stateful elements of the game. Can be likened 
 * to the empty game board, showing the playing map but no unit pieces.
 * 
 * @author Ric Craig
 * 
 */
public class DipMap {
	
	private static DipMap instance = null;

	private final HashMap<ProvinceIdentifier, Province> map;
	private final HashSet<ProvinceIdentifier> seaProvinces;
	private final HashSet<ProvinceIdentifier> inlandProvinces;
	private final HashSet<ProvinceIdentifier> coastalProvinces;
	private final HashMap<ProvinceIdentifier, ProvinceIdentifier> aliases;
	
	/**
	 * Constructs a new DipMap instance from a mapper if there is no 
	 * current DipMap instance. The mapper is responsible for extracting 
	 * the Province data from a map definition of whatever form.  
	 * 
	 * @param mapDataSource an {@link net.craigrm.dip.map.IMapDataSource IMapper} implementation that 
	 * extracts Province data from a map definition.
	 */
	public static void makeMap(IMapDataSource mapDataSource) {
		if (instance == null) {
			instance = new DipMap(mapDataSource);
		}
	}

	/**
	 * Constructs a new DipMap instance from a mapper if there is no 
	 * current DipMap instance. The mapper is responsible for extracting 
	 * the Province data from a map definition of whatever form.  
	 * 
	 * @param mapDataSource an {@link net.craigrm.dip.map.IMapDataSource IMapper} implementation that 
	 * extracts Province data from a map definition.
	 */
	public static void reloadMap(IMapDataSource mapDataSource) {
		instance = new DipMap(mapDataSource);
	}

	/**
	 * Provides the sole instance of DipMap created by {@link net.craigrm.dip.map.DipMap#makeMap(IMapDataSource) makeMap(IMapper)}
	 * @return the DipMap instance
	 * @throws IllegalStateException if DipMap has not been instantiated
	 */
	public static DipMap getMap() {
		if (instance == null) {
			throw new IllegalStateException();
		}
		return instance;
	}
	
	private DipMap(IMapDataSource mapDataSource) {
		map = new HashMap<ProvinceIdentifier, Province>();
		seaProvinces = new HashSet<ProvinceIdentifier>();
		inlandProvinces = new HashSet<ProvinceIdentifier>();
		coastalProvinces = new HashSet<ProvinceIdentifier>();
		aliases = new HashMap<ProvinceIdentifier, ProvinceIdentifier>();
		for(Province p: mapDataSource.getProvinces()) {
			addProvince(p);
		}
	}

	/**
	 * 	
	 * @return an unmodifiable view of the set of Province Identifiers that 
	 * make up the sea areas of the map. 
	 */
	public Set<ProvinceIdentifier> getSeaProvinces() {
		return Collections.unmodifiableSet(seaProvinces);
	}

	/**
	 * 	
	 * @return an unmodifiable view of the set of Province Identifiers that 
	 * make up the land areas of the map that do not border sea areas. 
	 */
	public Set<ProvinceIdentifier> getInlandProvinces() {
		return Collections.unmodifiableSet(inlandProvinces);
	}
	
	/**
	 * 	
	 * @return an unmodifiable view of the set of Province Identifiers that 
	 * make up the land areas of the map that border sea areas. 
	 */
	public Set<ProvinceIdentifier> getCoastalProvinces() {
		return Collections.unmodifiableSet(coastalProvinces);
	}

	/**
	 * 	
	 * @param an Identifier of the Province, either the canonical Identifier or an alias 
	 * @return the Province with the given Identifier, or null if it does not exist. 
	 */
	public Province getProvince(ProvinceIdentifier id) {
		final ProvinceIdentifier canonicalId = aliases.get(id);
		return map.get(canonicalId);
	}

	/**
	 * 	
	 * @param an Identifier of a Province, either the canonical Identifier or an alias 
	 * @return true if the Identifier identifies a Province of the current map. 
	 */
		public boolean isValidIdentifier(ProvinceIdentifier id) {
			
			return aliases.containsKey(id);
		}

	/**
	 * 	
	 * @param an Identifier of a Province, either the canonical Identifier or an alias 
	 * @param an Identifier of a Province, either the canonical Identifier or an alias 
	 * @return true id the two Provinces are neighbours.
	 */
	public boolean isNeighbour(ProvinceIdentifier id1, ProvinceIdentifier id2) {
		final ProvinceIdentifier canonicalId1 = aliases.get(id1);
		final ProvinceIdentifier canonicalId2 = aliases.get(id2);
		return map.get(canonicalId1).getNeighbours().contains(canonicalId2);
	}

	private void addProvince(Province province) {
		final ProvinceIdentifier id = province.getIdentifier();
		map.put(id, province);
		switch(province.getType()) { 
			case SEA: {
				seaProvinces.add(id);
				break;
			}
			case INLAND: {
				inlandProvinces.add(id);
				break;
			}
			case COAST: {
				coastalProvinces.add(id);
				break;
			}
		}
		for(ProvinceIdentifier aliasId:province.getAliases()) {
			aliases.put(aliasId, id);
		}
		aliases.put(id, id);
	}
	
}
