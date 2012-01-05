package net.craigrm.dip.state;

enum Season {
	SPRING, FALL;
	
	public static final Season INITIAL_SEASON = SPRING;

	/**
	 * @return the next {@code Season} in the enumeration, or the first 
	 * {@code Season} if this is the last.
	 * 
	 */
	public Season next(){
		int nextOrdinalValue = (this.ordinal()+1) % Season.values().length;
		return Season.values()[nextOrdinalValue];
	}
}
