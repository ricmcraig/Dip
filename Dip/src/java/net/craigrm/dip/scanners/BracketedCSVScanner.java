package net.craigrm.dip.scanners;

import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

import net.craigrm.dip.map.Identifier;


public class BracketedCSVScanner {

	public Set<Identifier> getUniqueIdentifiers( String scanString){
			Set<Identifier> resultSet = new HashSet<Identifier>();

			if (scanString == null) {
				throw new IllegalArgumentException("Wrong format scan string: null");
			}
			
			String trimmedScanString = scanString.trim();
			
			if (trimmedScanString.equals("()")) {
				return resultSet; 
			}
			
			// Expecting string format: (a, b, c)
			if (scanString.length() < 2) {
				throw new IllegalArgumentException("Wrong format scan string: >" + trimmedScanString + "<");
			}
			
			if (trimmedScanString.charAt(0) != '(' || trimmedScanString.charAt(trimmedScanString.length()-1) != ')') {
				throw new IllegalArgumentException("Wrong format scan string: >" + trimmedScanString + "<");
			}
			
			Scanner bracketedCSVScanner = new Scanner(trimmedScanString.substring(1,trimmedScanString.length()-1));
			bracketedCSVScanner.useDelimiter(",");

			while (bracketedCSVScanner.hasNext()) {
				resultSet.add(new Identifier(bracketedCSVScanner.next().trim()));
			}

			return resultSet;
	}
}
