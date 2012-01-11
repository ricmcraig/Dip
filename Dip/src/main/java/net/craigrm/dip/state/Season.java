package net.craigrm.dip.state;

import net.craigrm.dip.exceptions.SeasonFormatException;

public enum Season {
	SPRING("S"), FALL("F");
	
	private static String expectedMessage = "Expected value of \"S\" or \"F\". ";
	
	private String seasonID;
	
	public static Season getSeason (String season){
		String trimmedSeason = season.trim();
		
		for (Season s: Season.values()){
			if (s.getSeasonID().equalsIgnoreCase(trimmedSeason)){
				return s;
			}
		}
		throw new SeasonFormatException(Season.expectedMessage + "Got: " + trimmedSeason);
	}

	/**
	 * @return the next {@code Season} in the enumeration, or the first 
	 * {@code Season} if this is the last.
	 * 
	 */
	public Season next(){
		int nextOrdinalValue = (this.ordinal()+1) % Season.values().length;
		return Season.values()[nextOrdinalValue];
	}
	
	public String getSeasonID(){
		return this.seasonID;
	}

	private Season (String seasonID){
		this.seasonID = seasonID;
	}
	

}
