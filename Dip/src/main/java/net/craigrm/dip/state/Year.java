package net.craigrm.dip.state;

class Year {
	
	private static final int INITIAL_YEAR_NUMBER = 1901; 
	public static final Year INITIAL_YEAR = new Year(INITIAL_YEAR_NUMBER);
	
	private final int yearNumber;

	private Year(int yearNumber){
		this.yearNumber = yearNumber;
	}

	public Year next(){
		if (yearNumber < INITIAL_YEAR_NUMBER){
			throw new IllegalStateException("No initial year used.");
		}
		return new Year(yearNumber + 1);
	}

	int getYearNumber(){
		return yearNumber;
	}
	 
	@Override
	public String toString(){
		return String.valueOf(yearNumber);
	}
}
