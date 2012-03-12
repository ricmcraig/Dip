package net.craigrm.dip.scanners;

/**
 * A unit that provides an array of Strings from a data source
 * of form (<i>string 1</i>, <i>string 2</i>, ... <i>string n</i>).
 * 
 * @author Ric Craig
 *
 */
interface IBracketedCSVScanner {

	/**
	 * 
	 * @return an array of String where each element represents one of 
	 * the comma separated strings of the data source.
	 */
	public String[] getElements();

}
