package net.craigrm.dip.scanners;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import net.craigrm.dip.map.DuplicateProvinceIdentifierException;
import net.craigrm.dip.map.IMapDataSource;
import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.map.MapDefinitionException;
import net.craigrm.dip.map.Province;
import net.craigrm.dip.map.properties.Owner;
import net.craigrm.dip.map.properties.Supply;
import net.craigrm.dip.map.properties.Terrains;

public class StandardMapper implements IMapDataSource{

	private static final String OPTIONAL_WHITESPACE_REGEX = "\\s*";
	private static final String IDENTIFIER_MAIN_PART_REGEX = "[a-zA-Z]{3}";
	private static final String IDENTIFIER_OPTIONAL_PART_REGEX = "(\\([a-zA-Z]{2}\\))?";
	private static final String IDENTIFIER_REGEX = String.format("%1$s%2$s%3$s%1$s", OPTIONAL_WHITESPACE_REGEX, IDENTIFIER_MAIN_PART_REGEX, IDENTIFIER_OPTIONAL_PART_REGEX);
	private static final String EMPTY_IDENTIFIER_LIST_REGEX = String.format("%1$s\\(%1$s\\)%1$s", OPTIONAL_WHITESPACE_REGEX);
	private static final String IDENTIFIER_LIST_REGEX = String.format("%1$s\\(%2$s(,%2$s)*\\)%1$s", OPTIONAL_WHITESPACE_REGEX, IDENTIFIER_REGEX);

	private File mapFile;
	private Set<Province> provinces = new HashSet<Province>();
	
	public StandardMapper(File mapFile) {
		this.mapFile = mapFile;
		checkFile();
		parseMapDefinition();
	}

	public StandardMapper(String mapFileName) {
		if (mapFileName == null) {
			throw new IllegalArgumentException("Map file name not specified.");
		}
		this.mapFile = new File(mapFileName);
		checkFile();
		parseMapDefinition();
	}
	
	public Set<Province> getProvinces() {
		return provinces;
	}

	private void parseMapDefinition() {
		FileReader fr = null;
		Set<Province> provisionalProvinces = new HashSet<Province>();
		Set<ProvinceIdentifier> seaProvincesIds = new HashSet<ProvinceIdentifier>();
		Scanner lineScanner = null;
		String line = null; 
		int lineNo = 0;

		try {
			 fr = new FileReader(mapFile);
		}
		catch (FileNotFoundException fnfe) {
			throw new IllegalArgumentException("Position file " + mapFile.getAbsolutePath() + " cannot be found.");
		} 
		
		BufferedReader br = new BufferedReader(fr);
		// First pass: read the file and create set of provisional provinces and capture list of sea province names
		try {
			while ((line = br.readLine()) != null) {
				lineNo++;
				lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				
				if (!lineScanner.hasNext()) {
					throw new NoSuchMapElementException("Identifier");
				}
				ProvinceIdentifier id = new ProvinceIdentifier(lineScanner.next());
				
				if (!lineScanner.hasNext()) {
					throw new NoSuchMapElementException("Terrain");
				}
				Terrains terrain = Terrains.getTerrain(lineScanner.next());
				
				if (!lineScanner.hasNext()) {
					throw new NoSuchMapElementException("Suppply");
				}
				Supply supply = Supply.getSupply(lineScanner.next());
				
				if (!lineScanner.hasNext()) {
					throw new NoSuchMapElementException("Power");
				}
				Owner owner = new Owner(lineScanner.next());
				
				if (!lineScanner.hasNext()) {
					throw new NoSuchMapElementException("Full Name");
				}
				String fullName = lineScanner.next();
				
				String aliasesString = getIdentifierList(lineScanner);
				if (aliasesString == null) {
					throw new NoSuchMapElementException("Aliases");
				}
				String[] aliaseIDs = new BracketedCSVScanner(aliasesString).getElements();
				Set<ProvinceIdentifier> aliases = new HashSet<ProvinceIdentifier>(); 
				for(String alias:aliaseIDs) {
					ProvinceIdentifier aliasID = new ProvinceIdentifier(alias);
					if (!aliases.add(aliasID)) {
						throw new DuplicateProvinceIdentifierException(aliasID.getID());
					}
				}
				
				String neighboursString = getIdentifierList(lineScanner);
				if (neighboursString == null) {
					throw new NoSuchMapElementException("Neighbours");
				}
				String[] neighbourIDs = new BracketedCSVScanner(neighboursString).getElements();
				Set<ProvinceIdentifier> neighbours =  new HashSet<ProvinceIdentifier>();
				for(String neighbour:neighbourIDs) {
					ProvinceIdentifier neighbourID = new ProvinceIdentifier(neighbour);
					if (!neighbours.add(neighbourID)) {
						throw new DuplicateProvinceIdentifierException(neighbourID.getID());
					}
				}
				
				if (!provisionalProvinces.add(new Province(id, terrain, supply, owner, fullName, aliases, neighbours))) {
					throw new DuplicateProvinceIdentifierException(id.getID());
				}

				if (terrain == Terrains.SEA) {
					seaProvincesIds.add(id);
				}
			}
		}
		catch (IOException ioe) {
			throw new IllegalArgumentException("Map file " + mapFile.getAbsolutePath() + " cannot be read.");
		}
		catch (NoSuchMapElementException nsmee) {
			throw new MapDefinitionException(mapFile.getAbsolutePath(), lineNo, line,  nsmee.getMissingElementName() + " element", nsmee);
		}
		catch (DuplicateProvinceIdentifierException die) {
			throw new MapDefinitionException(mapFile.getAbsolutePath(), lineNo, line, "single " + die.getDuplicateIdentifier() + "identifier", die);
		}
		finally{
			if (br != null) {
				try {
					br.close();
				}
				catch(IOException ioe) {
					//TODO Log exception. Continue anyway: we read the file successfully
				}
			}
		}
		
		// Second pass: adjust provisional inland provinces to coastal provinces if they have a neighbouring sea province
		for(Province p: provisionalProvinces) {
			Province adjustedProvince = null;
			if(p.getType() == Terrains.INLAND && containsAny(p.getNeighbours(), seaProvincesIds)) {
				 adjustedProvince = new Province(p.getIdentifier(), Terrains.COAST, p.getSupply(), p.getOwner(), p.getFullName(), p.getAliases(), p.getNeighbours());}
			else {
				adjustedProvince = p;
			}
			provinces.add(adjustedProvince);
		}
		
	}
	
	private String getIdentifierList(Scanner lineScanner) {
		if(lineScanner.hasNext(EMPTY_IDENTIFIER_LIST_REGEX)) {
			return lineScanner.next(EMPTY_IDENTIFIER_LIST_REGEX);
		}

		return lineScanner.findInLine(IDENTIFIER_LIST_REGEX);
	}
	
	private void checkFile() {
		if (mapFile == null) {
			throw new IllegalArgumentException("Map file not specified.");
		}
		
		if (!mapFile.isFile()) {
			throw new IllegalArgumentException("Map file " + mapFile.getAbsolutePath() + " cannot be accessed.");
		}
	}
	
	private boolean containsAny(Set<ProvinceIdentifier> s1, Set<ProvinceIdentifier> s2) {
		Set<ProvinceIdentifier> intersection = new HashSet<ProvinceIdentifier>(s1);
		intersection.retainAll(s2);
		return !intersection.isEmpty();
	}

}
