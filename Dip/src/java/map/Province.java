package map;

import map.properties.Powers;
import map.properties.Supply;
import map.properties.Terrains;

public class Province implements Comparable<Province>{

	private Identifier identifier;
	private Supply supply;
	private Terrains type;
	private Powers owner;
	private String fullName;
	private Aliases aliases;
	private Neighbours neighbours;
	
	public Province() {
	}
	public Province(String identifier) {
		setIdentifier(identifier);
	}
	public Province(Identifier identifier) {
		setIdentifier(identifier);
	}
		
	public Identifier getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = new Identifier(identifier);
	}
	public Supply getSupply() {
		return supply;
	}
	public void setSupply(Supply supply) {
		this.supply = supply;
	}
	public void setSupply(String supply) {
		this.supply = null;
		String trimmedSupply = supply.trim();
		
		for(Supply s: Supply.values()){
			if (s.getSupplyID().equalsIgnoreCase(trimmedSupply)){
				this.supply = s;
				break;
			}
		}
		if (this.supply == null){
			throw new IllegalArgumentException(Supply.expectedMessage + "Got: " + trimmedSupply);
		}
	}
	public Terrains getType() {
		return type;
	}
	public void setType(Terrains type) {
		this.type = type;
	}
	public void setType(String type) {
		this.type = null;
		String trimmedType = type.trim();
		
		for(Terrains t: Terrains.values()){
			if (t.getBroadType().equalsIgnoreCase(trimmedType)){
				this.type = t;
				break;
			}
		}
		if (this.type == null){
			throw new IllegalArgumentException(Terrains.expectedMessage + "Got: " + trimmedType);
		}
	}
	public Powers getOwner() {
		return owner;
	}
	public void setOwner(Powers owner) {
		this.owner = owner;
	}
	public void setOwner(String owner) {
		this.owner = null;
		String trimmedOwner = owner.trim();
		
		for (Powers p: Powers.values()){
			if (p.getPowerID().equalsIgnoreCase(trimmedOwner)){
				this.owner = p;
				break;
			}
		}
		if (this.owner == null ){
			throw new IllegalArgumentException(Powers.expectedMessage + "Got: " + trimmedOwner);
		}
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Aliases getAliases() {
		return aliases;
	}
	public void setAliases(Aliases aliases) {
		this.aliases = aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = new Aliases(aliases);
	}
	public Neighbours getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(Neighbours neighbours) {
		this.neighbours = neighbours;
	}
	public void setNeighbours(String neighbours) {
		this.neighbours = new Neighbours(neighbours);
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
