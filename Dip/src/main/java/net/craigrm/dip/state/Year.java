package net.craigrm.dip.state;

import net.craigrm.dip.exceptions.YearFormatException;

public class Year {
	
	private final int yearNumber;

	public Year(String yearString){
		try{
			this.yearNumber = Integer.parseInt(yearString);
		}
		catch (NumberFormatException nfe){
			throw new YearFormatException();
		}
	}

	private Year(int yearNumber){
		this.yearNumber = yearNumber;
	}

	public Year next(){
		return new Year(yearNumber + 1);
	}

	int getYearNumber(){
		return yearNumber;
	}
	 
	@Override
	public String toString(){
		return String.valueOf(yearNumber);
	}

	@Override
	public int hashCode() {
		return new Integer(yearNumber).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Year other = (Year) obj;
		return yearNumber == other.yearNumber;
	}
	
	
}
