package net.craigrm.dip.scanners;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import net.craigrm.dip.map.Aliases;
import net.craigrm.dip.map.DuplicateIdentifierException;
import net.craigrm.dip.map.IMapper;
import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.map.MapDefinitionException;
import net.craigrm.dip.map.Neighbours;
import net.craigrm.dip.map.Province;
import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.map.properties.Supply;
import net.craigrm.dip.map.properties.Terrains;

public class StandardMapper implements IMapper{

	private static final String OPTIONAL_WHITESPACE_REGEX = "\\s*";
	private static final String IDENTIFIER_MAIN_PART_REGEX = "[a-zA-Z]{3}";
	private static final String IDENTIFIER_OPTIONAL_PART_REGEX = "(\\([a-zA-Z]{2}\\))?";
	private static final String IDENTIFIER_REGEX = String.format("%1$s%2$s%3$s%1$s", OPTIONAL_WHITESPACE_REGEX, IDENTIFIER_MAIN_PART_REGEX, IDENTIFIER_OPTIONAL_PART_REGEX);
	private static final String EMPTY_IDENTIFIER_LIST_REGEX = String.format("%1$s\\(%1$s\\)%1$s", OPTIONAL_WHITESPACE_REGEX);
	private static final String IDENTIFIER_LIST_REGEX = String.format("%1$s\\(%2$s(,%2$s)*\\)%1$s", OPTIONAL_WHITESPACE_REGEX, IDENTIFIER_REGEX);

	private File mapFile;
	private Set<Province> provinces = new HashSet<Province>();
	
	public StandardMapper(File mapFile){
		this.mapFile = mapFile;
		checkFile();
		parseMapDefinition();
	}

	public StandardMapper(String mapFileName){
		if (mapFileName == null)
		{
			throw new IllegalArgumentException("Map file name not specified.");
		}
		this.mapFile = new File(mapFileName);
		checkFile();
		parseMapDefinition();
	}
	
	public Set<Province> getProvinces(){
		return provinces;
	}

	private void parseMapDefinition(){
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
				
				if (!lineScanner.hasNext())
					throw new NoSuchMapElementException("Identifier");
				Identifier id = new Identifier(lineScanner.next());
				
				if (!lineScanner.hasNext())
					throw new NoSuchMapElementException("Terrain");
				Terrains terrain = Terrains.getTerrain(lineScanner.next());
				
				if (!lineScanner.hasNext())
					throw new NoSuchMapElementException("Suppply");
				Supply supply = Supply.getSupply(lineScanner.next());
				
				if (!lineScanner.hasNext())
					throw new NoSuchMapElementException("Power");
				Powers owner = Powers.getPower(lineScanner.next());
				
				if (!lineScanner.hasNext())
					throw new NoSuchMapElementException("Full Name");
				String fullName = lineScanner.next();
				
				String aliasesString = getIdentifierList(lineScanner);
				if (aliasesString == null)
					throw new NoSuchMapElementException("Aliases");
				Aliases aliases = new Aliases(new BracketedCSVScanner(aliasesString));
				
				String neighboursString = getIdentifierList(lineScanner);
				if (neighboursString == null)
					throw new NoSuchMapElementException("Neighbours");
				Neighbours neighbours = new Neighbours(new BracketedCSVScanner(neighboursString));
				
				if (!provisionalProvinces.add(new Province(id, terrain, supply, owner, fullName, aliases, neighbours)))
					throw new DuplicateIdentifierException(id.getID());
				
				if (terrain == Terrains.SEA) {
					seaProvincesIds.add(id);
				}
			}
		}
		catch (FileNotFoundException fnfe){
			throw new MapDefinitionException("Map file cannot be found.", fnfe, mapFile.getAbsolutePath(), lineNo);
		} 
		catch (IOException ioe){
			throw new MapDefinitionException("Map file cannot be read.", ioe, mapFile.getAbsolutePath(), lineNo);
		}
		catch (NoSuchMapElementException nsmee){
			throw new MapDefinitionException("Map file has a missing element.", nsmee, mapFile.getAbsolutePath(), lineNo);
		}
		catch (DuplicateIdentifierException die){
			throw new MapDefinitionException("Map file has a duplicate alias.", die, mapFile.getAbsolutePath(), lineNo);
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
	
	private String getIdentifierList(Scanner lineScanner){
		if(lineScanner.hasNext(EMPTY_IDENTIFIER_LIST_REGEX)){
			return lineScanner.next(EMPTY_IDENTIFIER_LIST_REGEX);
		}

		return lineScanner.findInLine(IDENTIFIER_LIST_REGEX);
	}
	
	private void checkFile() {
		if (mapFile == null) {
			throw new IllegalArgumentException("Map file not specified.");
		}
		
		if (!mapFile.isFile())
			throw new IllegalArgumentException("Map file " + mapFile.getAbsolutePath() + " cannot be accessed.");
	}
}
