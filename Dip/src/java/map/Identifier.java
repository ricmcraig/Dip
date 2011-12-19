package map;


public class Identifier implements Comparable<Identifier> {
	
	private static final String ID_REGEX = "\\w\\w\\w(\\(\\wC\\))?";
	private final String id;
	
	public Identifier (String id){
		
		if (id == null || !id.trim().toUpperCase().matches(ID_REGEX)){
			throw new IllegalArgumentException("Province identifier has bad format: " + id);
		}
		
		this.id = id.trim().toUpperCase();
	}
	
	public String getID(){
		return id;
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null || !(o instanceof Identifier)) {
			return false;
		}
		return (((Identifier)o).getID().equalsIgnoreCase(this.id));
	}
	
	@Override
	public int hashCode(){
		return id.hashCode();
	}

	@Override
	public int compareTo(Identifier i) {
		return id.compareTo(i.getID());
	}
	
	@Override
	public String toString(){
		return id;
	}
}
