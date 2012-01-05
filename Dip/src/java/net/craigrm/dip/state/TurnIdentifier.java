package net.craigrm.dip.state;

class TurnIdentifier implements Comparable<TurnIdentifier>{
	
	public static final TurnIdentifier INITIAL_TURN_ID = new TurnIdentifier(Season.INITIAL_SEASON, Year.INITIAL_YEAR);
	
	private final Season season;
	private final Year year;
	private final int seasonNumber; //Convenience field used to determine natural order
	
	private TurnIdentifier(Season season, Year year){
		this.season = season;
		this.year = year;
		this.seasonNumber = year.getYearNumber() * Season.values().length + season.ordinal();
	}
	
	public TurnIdentifier next(){
		Season newSeason = season.next();
		Year newYear = year;
		if (newSeason.equals(Season.SPRING)) {
			newYear = year.next();
		} 
		
		return  new TurnIdentifier(newSeason, newYear);
	}
	
	@Override
	public int compareTo(TurnIdentifier other) {
		//Returns the number of seasons between the two turns
		return this.seasonNumber - other.seasonNumber;
	}
	
}
