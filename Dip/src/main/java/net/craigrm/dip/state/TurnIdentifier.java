package net.craigrm.dip.state;

import net.craigrm.dip.state.properties.Season;
import net.craigrm.dip.state.properties.Year;

public class TurnIdentifier implements Comparable<TurnIdentifier>{

	private static final int YEAR_SEASON_STRING_EXPECTED_LENGTH = 5; // Expects format e.g. 1901S

	private final Year year;
	private final Season season;
	private final int seasonNumber; //Convenience field used to determine natural order
	
	TurnIdentifier(Year year, Season season) {
		this.year = year;
		this.season = season;
		this.seasonNumber = year.getYearNumber() * Season.values().length + season.ordinal();
	}
	
	TurnIdentifier(String year, String season) {
		this(new Year(year), Season.getSeason(season));
	}
	
	public TurnIdentifier(String turnID) {
		if (turnID.length() != YEAR_SEASON_STRING_EXPECTED_LENGTH) {
			throw new TurnIdentifierFormatException(turnID);
		}
		
		this.year = new Year(turnID.substring(0,4));
		this.season = Season.getSeason(turnID.substring(4, 5));
		this.seasonNumber = year.getYearNumber() * Season.values().length + season.ordinal();
	}

	public boolean isAdjustmentTurn() {
		return  season.isAdjustmentSeason();
	}
	
	TurnIdentifier next() {
		Season newSeason = season.next();
		Year newYear = year;
		if (newSeason.equals(Season.SPRING)) {
			newYear = year.next();
		} 
		return  new TurnIdentifier(newYear, newSeason);
	}
	
	@Override
	public int compareTo(TurnIdentifier other) {
		//Returns the number of seasons between the two turns
		return this.seasonNumber - other.seasonNumber;
	}

	@Override
	public int hashCode() {
		return seasonNumber;
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
		TurnIdentifier other = (TurnIdentifier) obj;
		if (seasonNumber != other.seasonNumber) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TurnIdentifier [year=" + year + ", season=" + season
				+ ", seasonNumber=" + seasonNumber + "]";
	}
	
}
