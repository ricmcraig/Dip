package net.craigrm.dip.scanners;

import java.util.Arrays;

import net.craigrm.dip.map.IBracketedCSVScanner;

public final class BracketedCSVScanner implements IBracketedCSVScanner {

	private static final String EMPTY_LIST_REGEX = "\\(\\s*\\)";
	private static final String CSV_SEPARATOR_REGEX = ",";
	
	private final String[] elements;

	public BracketedCSVScanner(String scanString){
		if (scanString == null) {
			throw new IllegalArgumentException("Wrong format scan string: null");
		}
		
		String trimmedScanString = scanString.trim();
		
		if (trimmedScanString.matches(EMPTY_LIST_REGEX)) {
			elements = new String[0];
			return; 
		}
		
		// Expecting string format: (a, b, c)
		if (scanString.length() < 2) {
			throw new IllegalArgumentException("Wrong format scan string: >" + trimmedScanString + "<");
		}
		
		if (trimmedScanString.charAt(0) != '(' || trimmedScanString.charAt(trimmedScanString.length()-1) != ')') {
			throw new IllegalArgumentException("Wrong format scan string: >" + trimmedScanString + "<");
		}
		
		elements = trimmedScanString.substring(1, trimmedScanString.length()-1).split(CSV_SEPARATOR_REGEX);
	}
	
	public String[] getElements(){
		return Arrays.copyOf(elements, elements.length);
	}

}
