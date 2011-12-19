package map;

import map.properties.Powers;
import map.properties.Supply;
import map.properties.Terrains;

public class Province implements Comparable<Province>{

	private final Identifier identifier;
	private final Terrains type;
	private final Supply supply;
	private final Powers owner;
	private final String fullName;
	private final Aliases aliases;
	private final Neighbours neighbours;
	
	public Province(Identifier id, Terrains terrain, Supply supply, Powers owner, String fullName, Aliases aliases, Neighbours neighbours) {
		this.identifier = id;
        this.type = terrain;
        this.supply = supply;
        this.owner = owner;
        this.fullName = fullName;
        this.aliases = aliases;
        this.neighbours = neighbours;
	}

	public Identifier getIdentifier() {
		return identifier;
	}
	public Terrains getType() {
		return type;
	}
	public Supply getSupply() {
		return supply;
	}
	public Powers getOwner() {
		return owner;
	}
	public String getFullName() {
		return fullName;
	}
	public Aliases getAliases() {
		return aliases;
	}
	public Neighbours getNeighbours() {
		return neighbours;
	}
	
	@Override
	public boolean equals(Object o){
		// Provinces are equal if their Identifiers are equal. If other properties are different, this is ignored.
		if (o == null || !(o instanceof Province)) {
			return false;
		}
		return (((Province)o).getIdentifier().equals(this.identifier));
	}
	
	@Override
	public int hashCode(){
		return identifier.hashCode();
	}

	@Override
	public int compareTo(Province i) {
		return identifier.compareTo(i.getIdentifier());
	}
	
	@Override
	public String toString(){
		final String SEPARATOR = ", ";
		StringBuilder ps = new StringBuilder("Province:(");
		ps.append(identifier).append(SEPARATOR);
		ps.append(type).append(SEPARATOR);
		ps.append(supply).append(SEPARATOR);
		ps.append(owner).append(SEPARATOR);
		ps.append(fullName).append(SEPARATOR);
		ps.append(aliases).append(SEPARATOR);
		ps.append(neighbours).append(")");
		return ps.toString();
	}
}
