package scanners;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import map.Aliases;
import map.IMapper;
import map.Identifier;
import map.Neighbours;
import map.Province;
import map.properties.Powers;
import map.properties.Supply;
import map.properties.Terrains;

public class StandardMapper implements IMapper{

	private static final String ALIASES_REGEX = "\\(.*?\\)";
	private static final String NEIGHBOURS_REGEX = "\\(.*?[^\\(]..\\)";

	private File mapFile;
	
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
		Set<Province> provisionalProvinces = new HashSet<Province>();
		Set<Province> provinces = new HashSet<Province>();
		Set<Identifier> seaProvincesIds = new HashSet<Identifier>();
		Scanner lineScanner = null;
		String line = null; 
		int lineNo = 0;
		String mapFileName = null;
		
		// First pass: read the file and create set of provisional provinces and capture list of sea province names
		try{
			mapFileName = mapFile.getCanonicalPath();
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
				provisionalProvinces.add(new Province(id, terrain, supply, owner, fullName, aliases, neighbours));
				if (terrain == Terrains.SEA) {
					seaProvincesIds.add(id);
				}
			}
		}
		catch (FileNotFoundException fnfe){
			if (mapFileName == null){
				throw new IllegalArgumentException ("Map file cannot be found.");
			} else {
				throw new IllegalArgumentException("Map file " + mapFileName + " cannot be found.");
			}
		}
		catch (IOException ioe){
			if (mapFileName == null){
				throw new IllegalArgumentException ("Map file cannot be read.");
			} else {
				throw new IllegalArgumentException("Map file " + mapFileName + " cannot be read.");
			}
		}
		catch (NoSuchElementException nsee){
			throw new IllegalArgumentException("Map file " + mapFileName + " has problem at line: " + lineNo);
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
		
		return provinces;
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
