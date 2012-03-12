package net.craigrm.dip.map;

/**
 * An immutable representation of a unique identifier of a Province.
 * 
 * @author Ric Craig
 * 
 */

public class ProvinceIdentifier implements Comparable<ProvinceIdentifier> {

	private static final String ID_REGEX = "\\w\\w\\w(\\(\\wC\\))?";
	private final String id;

	/**
	 * Constructs an Identifier for Province from an input string. 
	 * The Identifier is a normalised representation where leading and 
	 * trailing whitespace is removed and the input string is converted 
	 * to upper case.
	 *     
	 * @param id a string representation of the identifier. 
	 *    
	 * @throws java.lang.IllegalArgumentException If id is not of the 
	 * correct form. id is expected to match regular expression 
	 * "\s*\w\w\w(\(\wC\))?\s*".
	 */
	public ProvinceIdentifier(String id) {

		if (id == null || !id.trim().toUpperCase().matches(ID_REGEX)) {
			throw new IllegalArgumentException(
					"Province identifier has bad format: " + id);
		}

		this.id = id.trim().toUpperCase();
	}
	
	public String getID() {
		return id;
	}
	
	@Override
	public int compareTo(ProvinceIdentifier i) {
		return id.compareTo(i.id);
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return 0;
		}
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProvinceIdentifier other = (ProvinceIdentifier) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return id;
	}
}
