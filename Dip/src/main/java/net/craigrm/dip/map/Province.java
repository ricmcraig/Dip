package net.craigrm.dip.map;

import java.util.Set;

import net.craigrm.dip.map.properties.Owner;
//import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.map.properties.Supply;
import net.craigrm.dip.map.properties.Terrains;

/**
 * Represents an area on the game board in which a unit may be played.
 * <P>
 * Areas which have separate coastlines are represented several times, once as 
 * the whole province, for use with land units and once for each coastline,
 * for use with sea units. This is because neighbours are dependent on coastline.
 * <p>
 * For example, on the standard map, Spain, which has two coastlines, is 
 * represented as:
 * <br>
 * Spain, with neighbours: Gulf of Lyon, Gascony, Marseilles, 
 * Mid Atlantic Ocean, Portugal and Western Mediterranean<br>
 * Spain North Coast, with neighbours: Mid Atlantic Ocean, 
 * Portugal and Western Mediterranean<br>
 * Spain South Coast, with neighbours: Gulf of Lyon, Marseilles, 
 * Mid Atlantic Ocean, Portugal and Western Mediterranean<br>
 * 
 * @author Ric Craig
 */
public class Province implements Comparable<Province>{

	private final ProvinceIdentifier provinceIdentifier;
	private final Terrains type;
	private final Supply supply;
	private final Owner owner;
	private final String fullName;
	private final Set<ProvinceIdentifier> aliases;
	private final Set<ProvinceIdentifier> neighbours;
	
	/**
	 * Constructs an immutable Province instance
	 * 
	 * @param id the canonical unique Identifier of this Province 
	 * @param terrain the terrain type of the Province 
	 * @param supply the supply value of the Province
	 * @param owner the Power for whom the Province is a home province (may be none) 
	 * @param fullName the name of the Province
	 * @param aliases a collection of alternative Identifiers by which this Province is known
	 * @param neighbours a collection Identifiers of Provinces that border this one
	 */
	public Province(ProvinceIdentifier id, Terrains terrain, Supply supply, Owner owner, 
			String fullName, Set<ProvinceIdentifier> aliases, Set<ProvinceIdentifier> neighbours) {
		this.provinceIdentifier = id;
        this.type = terrain;
        this.supply = supply;
        this.owner = owner;
        this.fullName = fullName;
        this.aliases = aliases;
        this.neighbours = neighbours;
	}

	/**
	 * 
	 * @return the canonical unique Identifier of this Province 
	 */
	public ProvinceIdentifier getIdentifier() {
		return provinceIdentifier;
	}

	/**
	 * 
	 * @return the terrain type of this Province 
	 */
	public Terrains getType() {
		return type;
	}

	/**
	 * 
	 * @return the supply value of this Province 
	 */
	public Supply getSupply() {
		return supply;
	}

	/**
	 * 
	 * @return the owner, if any, of this Province (note the owner may
	 *  differ from the current controller, which is an attribute of 
	 *  {@link net.craigrm.dip.state.TurnState}  
	 */
	public Owner getOwner() {
		return owner;
	}

	/**
	 * 
	 * @return the natural language name of this Province 
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 
	 * @return a collection of alternative commonly used unique Identifiers of this Province 
	 */
	public Set<ProvinceIdentifier> getAliases() {
		return aliases;
	}

	/**
	 * 
	 * @return a collection of Identifiers of Provinces that border this one 
	 */
	public Set<ProvinceIdentifier> getNeighbours() {
		return neighbours;
	}
	
	@Override
	public int hashCode() {
		return ((provinceIdentifier == null) ? 1 : provinceIdentifier.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Province other = (Province) obj;
		if (provinceIdentifier == null) {
			if (other.provinceIdentifier != null) {
				return false;
			}
		} else if (!provinceIdentifier.equals(other.provinceIdentifier)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(Province i) {
		return provinceIdentifier.compareTo(i.getIdentifier());
	}
	
	@Override
	public String toString() {
		final String SEPARATOR = ", ";
		StringBuilder ps = new StringBuilder("Province:(");
		ps.append(provinceIdentifier).append(SEPARATOR);
		ps.append(type).append(SEPARATOR);
		ps.append(supply).append(SEPARATOR);
		ps.append(owner).append(SEPARATOR);
		ps.append(fullName).append(SEPARATOR);
		ps.append(aliases).append(SEPARATOR);
		ps.append(neighbours).append(")");
		return ps.toString();
	}
}
