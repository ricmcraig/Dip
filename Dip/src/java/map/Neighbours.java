package map;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import scanners.BracketedCSVScanner;

public class Neighbours {

	private Set<Identifier> neighbours;
	
	public Neighbours(String neighbourString){
		BracketedCSVScanner neighbourScanner = new BracketedCSVScanner();
		neighbours = neighbourScanner.getUniqueIdentifiers(neighbourString);
	}

	public boolean contains(Identifier id){
		return neighbours.contains(id);
	}
	
	public boolean containsAny(Set<Identifier> s){
		Set<Identifier> intersection = new HashSet<Identifier>(neighbours);
		intersection.retainAll(s);
		return !intersection.isEmpty();
	}

	@Override
	public String toString(){
		//Order of neighbours not guaranteed. For consistency, make to_string order alphabetical.
		StringBuilder sb = new StringBuilder("");
		for(Identifier i:new TreeSet<Identifier>(neighbours)){
			sb.append(i.toString()).append(", ");
		}
		return sb.delete(sb.length()-2, sb.length()).toString();
	}
}
