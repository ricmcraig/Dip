package map;

import map.properties.Powers;
import map.properties.Terrains;

public class Province {

	private Identifier identifier;
	private boolean hasSupplyCentre;
	private Terrains type;
	private Powers owner;
	private String fullName;
	private Aliases aliases;
	private Neighbours neighbours;
	
	public Identifier getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = new Identifier(identifier);
	}
	public boolean isHasSupplyCentre() {
		return hasSupplyCentre;
	}
	public void setHasSupplyCentre(boolean hasSupplyCentre) {
		this.hasSupplyCentre = hasSupplyCentre;
	}
	public void setHasSupplyCentre(String hasSupplyCentre) {
		this.hasSupplyCentre = hasSupplyCentre != null && hasSupplyCentre.trim().equalsIgnoreCase("SC");
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
	
	public String toString(){
		return "Province:(" + identifier + ")";
	}
}
