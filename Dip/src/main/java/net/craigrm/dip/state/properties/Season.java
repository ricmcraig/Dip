package net.craigrm.dip.state.properties;


public enum Season {
	SPRING("S", false), 
	FALL("F", true);
	
	private static final String EXPECTED_FORMAT = "\"S\" or \"F\"";
	
	private String seasonID;
	private boolean isAdjustmentSeason;
	
	public static Season getSeason (String season) {
		String trimmedSeason = season.trim();
		
		for (Season s: Season.values()) {
			if (s.getSeasonID().equalsIgnoreCase(trimmedSeason)) {
				return s;
			}
		}
		throw new SeasonFormatException(EXPECTED_FORMAT , trimmedSeason);
	}

	/**
	 * @return the next {@code Season} in the enumeration, or the first 
	 * {@code Season} if this is the last.
	 * 
	 */
	public Season next() {
		int nextOrdinalValue = (this.ordinal()+1) % Season.values().length;
		return Season.values()[nextOrdinalValue];
	}
	
	public String getSeasonID() {
		return this.seasonID;
	}

	public boolean isAdjustmentSeason() {
		return isAdjustmentSeason;
	}

	public void setAdjustmentSeason(boolean isAdjustmentSeason) {
		this.isAdjustmentSeason = isAdjustmentSeason;
	}

	private Season (String seasonID, boolean isAdjustmentSeason) {
		this.seasonID = seasonID;
		this.isAdjustmentSeason = isAdjustmentSeason;
	}
	

}
