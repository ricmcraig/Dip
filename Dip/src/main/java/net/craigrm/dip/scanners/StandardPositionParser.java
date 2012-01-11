package net.craigrm.dip.scanners;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import net.craigrm.dip.exceptions.*;
import net.craigrm.dip.gameturn.IPositionParser;
import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.state.Season;
import net.craigrm.dip.state.Unit;
import net.craigrm.dip.state.UnitType;
import net.craigrm.dip.state.Year;

public class StandardPositionParser implements IPositionParser{

	private static final String POSITION_FILE_COMMENT_INDICATOR = "#";
	private static final int TURN_YEAR_START = 0;
	private static final int TURN_YEAR_END = 4;
	private static final int TURN_SEASON_START = 4;
	private static final int TURN_SEASON_END = 5;

	private File posFile;
	private Set<Unit> units = new HashSet<Unit>();
	private Year year = null;
	private Season season = null;
	
	public StandardPositionParser(File posFile){
		this.posFile = posFile;
		checkFile();
	}

	public StandardPositionParser(String posFileName){
		if (posFileName == null)
		{
			throw new IllegalArgumentException("Position file name not specified.");
		}
		File mapFile = new File(posFileName);
		this.posFile = mapFile;
		checkFile();
	}
	
	public Set<Unit> getUnits(){
		return Collections.unmodifiableSet(units);
	}

	public Year getYear(){
		return year;
	}

	public Season getSeason(){
		return season;
	}

	public void parsePositionDefinition(){
		Scanner lineScanner = null;
		String line = null; 
		int lineNo = 0;
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(posFile));
			// Get the turn identifier on the first non-comment line
			while ((line = br.readLine()) != null){
				lineNo++;
				if (line.startsWith(POSITION_FILE_COMMENT_INDICATOR)){
					continue;
				}
				year = new Year(line.substring(TURN_YEAR_START, TURN_YEAR_END));
				season = Season.getSeason(line.substring(TURN_SEASON_START, TURN_SEASON_END));
				break;
			}
			if (year == null || season == null){
				throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + ". Could not find turn identifier");
			}
			
			// Get the unit positions from subsequent non-comment lines
			while ((line = br.readLine()) != null){
				lineNo++;
				if (line.startsWith(POSITION_FILE_COMMENT_INDICATOR)){
					continue;
				}
				lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				Identifier id = new Identifier(lineScanner.next());
				Powers unitOwner = Powers.getPower(lineScanner.next());
				UnitType unitType = UnitType.getType(lineScanner.next());
				units.add(new Unit(id, unitOwner, unitType));
			}
			if (units.isEmpty()){
				throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + ". Could not find unit positions");
			}
			
		}
		catch (FileNotFoundException fnfe){
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " cannot be found.");
		}
		catch (IOException ioe){
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " cannot be read.");
		}
		catch (NoSuchElementException nsee){
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " has problem at line: " + lineNo);
		}
		catch (YearFormatException nfe){
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " has problem at line: " + lineNo + ". Expected a turn year.");
		}
		catch (SeasonFormatException iae){
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " has problem at line: " + lineNo + ". Expected a turn season.");
		}

	}

	private void checkFile() {
		if (posFile == null) {
			throw new IllegalArgumentException("Position file not specified.");
		}
		
		if (!posFile.isFile()){
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " cannot be accessed.");
		}
	}
}

