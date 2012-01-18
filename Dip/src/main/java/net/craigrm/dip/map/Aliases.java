package net.craigrm.dip.map;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import net.craigrm.dip.scanners.BracketedCSVScanner;

/**
 * Represents an immutable collection of alternative Province Identifiers 
 * that uniquely identify a particular Province. <p>
 * The canonical identifier for a Province is given by 
 * {@link net.craigrm.dip.Province#getIdentifier()}. This is a collection 
 * of alternative identifiers. Some provinces on the standard Diplomacy
 * map are referred to by a number of different identifiers.  
 * 
 * @author Ric Craig
 *
 */
		
public final class Aliases {

	private final Set<Identifier> aliases = new HashSet<Identifier>();

	/**
	 * Constructs the collection of alternative Province Identifiers
	 * 
	 * @param aliasScanner .
	 */
	public Aliases(IBracketedCSVScanner aliasScanner){
		for(String alias:aliasScanner.getElements()) {
			if (!aliases.add(new Identifier(alias))) {
				throw new AliasesStateException("Duplicate alias found.", alias);
			}
		}
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
