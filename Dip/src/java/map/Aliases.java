package map;
import java.util.Set;

import scanners.BracketedCSVScanner;

public class Aliases {

	private Set<Identifier> aliases;
	
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
	
}
