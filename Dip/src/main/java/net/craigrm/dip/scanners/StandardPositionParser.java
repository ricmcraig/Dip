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

import net.craigrm.dip.gameturn.IPositionDataSource;
import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.state.Control;
import net.craigrm.dip.state.PositionDefinitionException;
import net.craigrm.dip.state.TurnIdentifier;
import net.craigrm.dip.state.TurnIdentifierFormatException;
import net.craigrm.dip.state.Unit;
import net.craigrm.dip.state.properties.SeasonFormatException;
import net.craigrm.dip.state.properties.UnitType;
import net.craigrm.dip.state.properties.YearFormatException;

public class StandardPositionParser implements IPositionDataSource{

	private static final String COMMENT_PREFIX = "#";
	private static final String TURN_PREFIX = "=";
	private static final String WHITESPACE_REGEX =  "\\s*";
	private static final String CONTROL_LINE_REGEX = ".*,.*";
	private static final String POSITION_LINE_REGEX = ".*,.*,.*";

	private File posFile;
	private Set<Unit> units = new HashSet<Unit>();
	private Set<Control> control = new HashSet<Control>();
	private TurnIdentifier turnID = null;
	
	public StandardPositionParser(File posFile) {
		this.posFile = posFile;
		checkFile();
	}

	public StandardPositionParser(String posFileName) {
		if (posFileName == null) {
			throw new IllegalArgumentException("Position file name not specified.");
		}
		File posFile = new File(posFileName);
		this.posFile = posFile;
		checkFile();
	}
	
	public Set<Unit> getUnits() {
		return Collections.unmodifiableSet(units);
	}

	public Set<Control> getControl() {
		return Collections.unmodifiableSet(control);
	}

	public TurnIdentifier getTurnID() {
		return turnID;
	}

	public void parsePositionDefinition() {
		FileReader fr = null;
		Scanner lineScanner = null;
		String line = null; 
		int lineNo = 0;
		
		try {
			fr = new FileReader(posFile);
		}
		catch (FileNotFoundException fnfe) {
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " cannot be found.");
		}
		
		BufferedReader br = new BufferedReader(fr);
		try {
			
			// Get the turn identifier on the first non-comment line
			while ((line = br.readLine()) != null) {
				lineNo++;
				// Skip comment line
				if (line.startsWith(COMMENT_PREFIX)) {
					continue;
				}
				
				//Skip blank line
				if (line.matches(WHITESPACE_REGEX)) {
					continue;
				}
				
				// Handle turn designation line
				if (line.startsWith(TURN_PREFIX)) {
					if (turnID != null) {
						throw new PositionDefinitionException(posFile.getAbsolutePath(), lineNo, line, "a single Turn designation");
					}
					try {
						turnID = new TurnIdentifier(line.substring(TURN_PREFIX.length()));
					}
					catch (TurnIdentifierFormatException tife) {
						throw new PositionDefinitionException(posFile.getAbsolutePath(), lineNo, line, "a correctly formatted turn ID", tife);
					}
					catch (YearFormatException yfe) {
						throw new PositionDefinitionException(posFile.getAbsolutePath(), lineNo, line, "a valid turn year", yfe );
					}
					catch (SeasonFormatException sfe) {
						throw new PositionDefinitionException(posFile.getAbsolutePath(), lineNo, line, "a valid turn season", sfe );
					}
					continue;
				}
				
				// Should be a position or control line: check we've got a turn
				if (turnID == null) {
					throw new PositionDefinitionException(posFile.getAbsolutePath(), lineNo, line, "a turn designation before this point");
				}
				
				// Handle position and control lines
				lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				try {
					if (line.matches(POSITION_LINE_REGEX)) {
						ProvinceIdentifier id = new ProvinceIdentifier(lineScanner.next());
						Powers unitOwner = Powers.getPowerFromID(lineScanner.next());
						UnitType unitType = UnitType.getType(lineScanner.next());
						units.add(new Unit(id, unitOwner, unitType));
					} else if (line.matches(CONTROL_LINE_REGEX)) {
						ProvinceIdentifier id = new ProvinceIdentifier(lineScanner.next());
						Powers controller = Powers.getPowerFromID(lineScanner.next());
						control.add(new Control(id, controller));
					} else {
						throw new PositionDefinitionException(posFile.getAbsolutePath(), lineNo, line, POSITION_LINE_REGEX + " or " + CONTROL_LINE_REGEX );
					}
				}
				finally{
					lineScanner.close();
				}
			}
			
			// We must have a turnID
			if (turnID == null) {
				throw new PositionDefinitionException(posFile.getAbsolutePath(), 0, null, "a turn ID in the orders defintion file");
			}

			// We must have at least one unit definition
			if (units.isEmpty()) {
				throw new PositionDefinitionException(posFile.getAbsolutePath(), 0, null, "to find unit positon definitions");
			}
			
			// We must have at least one control definition
			if (control.isEmpty()) {
				throw new PositionDefinitionException(posFile.getAbsolutePath(), 0, null, "to find control definitions");
			}
		}
		catch (IOException ioe) {
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " cannot be read.");
		}
		catch (NoSuchElementException nsee) {
			throw new PositionDefinitionException(posFile.getAbsolutePath(), lineNo, line, POSITION_LINE_REGEX + " or " + CONTROL_LINE_REGEX );
		}
		finally{
			try {
				br.close();
			}
			catch (IOException ioe) {
				//TODO Log exception. Continue anyway: we read the file successfully
			}
		}

	}

	private void checkFile() {
		if (posFile == null) {
			throw new IllegalArgumentException("Position file not specified.");
		}
		
		if (!posFile.isFile()) {
			throw new IllegalArgumentException("Position file " + posFile.getAbsolutePath() + " cannot be accessed.");
		}
	}
}

