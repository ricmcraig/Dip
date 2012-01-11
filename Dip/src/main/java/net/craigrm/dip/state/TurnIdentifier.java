package net.craigrm.dip.state;

class TurnIdentifier implements Comparable<TurnIdentifier>{
	
	private final Year year;
	private final Season season;
	private final int seasonNumber; //Convenience field used to determine natural order
	
	TurnIdentifier(Year year, Season season){
		this.year = year;
		this.season = season;
		this.seasonNumber = year.getYearNumber() * Season.values().length + season.ordinal();
	}
	
	TurnIdentifier(String year, String season){
		this.year = new Year(year);
		this.season = Season.getSeason(season);
		this.seasonNumber = this.year.getYearNumber() * Season.values().length + this.season.ordinal();
	}
	
	TurnIdentifier next(){
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
	
}
