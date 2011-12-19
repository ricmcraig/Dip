package map;
import java.util.Set;
import java.util.TreeSet;

import scanners.BracketedCSVScanner;

public class Aliases {

	private final Set<Identifier> aliases;
	
	public Aliases(String aliasString){
		BracketedCSVScanner aliasScanner = new BracketedCSVScanner();
		aliases = aliasScanner.getUniqueIdentifiers(aliasString);
	}

	public boolean contains(Identifier id){
		return aliases.contains(id);
	}
	
	public boolean isEmpty(){
		return aliases.isEmpty();
	}
	
	@Override
	public String toString(){
		//Order of aliases not guaranteed. For ease of reading, ensure to_string returns contents in natural order.
		StringBuilder sb = new StringBuilder("");
		TreeSet<Identifier> ts = new TreeSet<Identifier>(aliases); 
		for(Identifier i:ts.headSet(ts.last())){
			sb.append(i.toString()).append(", ");
		}
		sb.append(ts.last());
		return sb.toString();
	}
}
