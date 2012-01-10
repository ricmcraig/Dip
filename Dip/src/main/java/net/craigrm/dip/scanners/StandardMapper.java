package net.craigrm.dip.scanners;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import net.craigrm.dip.map.Aliases;
import net.craigrm.dip.map.IMapper;
import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.map.Neighbours;
import net.craigrm.dip.map.Province;
import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.map.properties.Supply;
import net.craigrm.dip.map.properties.Terrains;
import net.craigrm.dip.state.Unit;
import net.craigrm.dip.state.UnitType;



public class StandardMapper implements IMapper{

	private static final String ALIASES_REGEX = "\\(.*?\\)";
	private static final String NEIGHBOURS_REGEX = "\\(.*?[^\\(]..\\)";

	private File mapFile;
	private Set<Province> provinces = new HashSet<Province>();
	private Set<Unit> startPosition = new HashSet<Unit>();
	
	public StandardMapper(File mapFile){
		this.mapFile = mapFile;
		checkFile();
	}

	public StandardMapper(String mapFileName){
		if (mapFileName == null)
		{
			throw new IllegalArgumentException("Map file name not specified.");
		}
		File mapFile = new File(mapFileName);
		this.mapFile = mapFile;
		checkFile();
	}
	
	public Set<Province> getProvinces(){
		return provinces;
	}

	public Set<Unit> getStartPosition(){
		return startPosition;
	}

	public void parseGameDefinition(){
		Set<Province> provisionalProvinces = new HashSet<Province>();
		Set<Identifier> seaProvincesIds = new HashSet<Identifier>();
		Scanner lineScanner = null;
		String line = null; 
		int lineNo = 0;
		
		// First pass: read the file and create set of provisional provinces and capture list of sea province names
		try{
			BufferedReader br = new BufferedReader(new FileReader(mapFile));
			while ((line = br.readLine()) != null){
				lineNo++;
				lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				Identifier id = new Identifier(lineScanner.next());
				Terrains terrain = Terrains.getTerrain(lineScanner.next());
				Supply supply = Supply.getSupply(lineScanner.next());
				Powers owner = Powers.getPower(lineScanner.next());
				String fullName = lineScanner.next();
				Aliases aliases = new Aliases(lineScanner.findInLine(ALIASES_REGEX));
				Neighbours neighbours = new Neighbours(lineScanner.findInLine(NEIGHBOURS_REGEX));
				Powers unitOwner = Powers.getPower(lineScanner.next());
				UnitType unitType = UnitType.getType(lineScanner.next());
				provisionalProvinces.add(new Province(id, terrain, supply, owner, fullName, aliases, neighbours));
				if (terrain == Terrains.SEA) {
					seaProvincesIds.add(id);
				}
				if (unitType != UnitType.NONE) {
					startPosition.add(new Unit(id, unitOwner, unitType));
				}
			}
		}
		catch (FileNotFoundException fnfe){
			throw new IllegalArgumentException("Map file " + mapFile.getAbsolutePath() + " cannot be found.");
		}
		catch (IOException ioe){
			throw new IllegalArgumentException("Map file " + mapFile.getAbsolutePath() + " cannot be read.");
		}
		catch (NoSuchElementException nsee){
			throw new IllegalArgumentException("Map file " + mapFile.getAbsolutePath() + " has problem at line: " + lineNo);
		}
		
		// Second pass: adjust provisional inland provinces to coastal provinces if they have a neighbouring sea province
		for(Province p: provisionalProvinces){
			Province adjustedProvince = null;
			if(p.getType() == Terrains.INLAND && p.getNeighbours().containsAny(seaProvincesIds)){
				 adjustedProvince = new Province(p.getIdentifier(), Terrains.COAST, p.getSupply(), p.getOwner(), p.getFullName(), p.getAliases(), p.getNeighbours());}
			else {
				adjustedProvince = p;
			}
			provinces.add(adjustedProvince);
		}
		
	}
	

	private void checkFile() {
		if (mapFile == null) {
			throw new IllegalArgumentException("Map file not specified.");
		}
		
		if (!mapFile.isFile()){
			try {
				String mapFileName = mapFile.getCanonicalPath();
				throw new IllegalArgumentException("Map file " + mapFileName + " cannot be accessed.");
			}
			catch (IOException ioe){
				throw new IllegalArgumentException ("Map file cannot be accessed.");
			}
			
		}
	}
}
