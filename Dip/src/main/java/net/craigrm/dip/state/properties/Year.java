package net.craigrm.dip.state.properties;


public final class Year {
	
	private final int yearNumber;

	public Year(String yearString) {
		try {
			this.yearNumber = Integer.parseInt(yearString);
		}
		catch (NumberFormatException nfe) {
			throw new YearFormatException(yearString);
		}
	}

	private Year(int yearNumber) {
		this.yearNumber = yearNumber;
	}

	public Year next() {
		return new Year(yearNumber + 1);
	}

	public int getYearNumber() {
		return yearNumber;
	}
	 
	@Override
	public String toString() {
		return String.valueOf(yearNumber);
	}

	@Override
	public int hashCode() {
		// Use yearNumber for hash code (modelled on Integer which uses underlying primitive int)
		return yearNumber;
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
		Year other = (Year) obj;
		return yearNumber == other.yearNumber;
	}
	
	
}
