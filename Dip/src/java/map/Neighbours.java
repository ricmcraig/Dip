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
		//Order of neighbours not guaranteed. For ease of reading, ensure to_string returns contents in natural order.
		StringBuilder sb = new StringBuilder("");
		TreeSet<Identifier> ts = new TreeSet<Identifier>(neighbours); 
		for(Identifier i:ts.headSet(ts.last())){
			sb.append(i.toString()).append(", ");
		}
		sb.append(ts.last());
		return sb.toString();
	}
}
