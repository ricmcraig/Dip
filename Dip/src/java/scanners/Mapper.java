package scanners;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import map.DipMap;
import map.Province;

public class Mapper {

	private File mapFile;
	
	public Mapper(File mapFile){
		this.mapFile = mapFile;
		checkFile();
	}

	public Mapper(String mapFileName){
		if (mapFileName == null)
		{
			throw new IllegalArgumentException("Map file name not specified.");
		}
		File mapFile = new File(mapFileName);
		this.mapFile = mapFile;
		checkFile();
	}

	
	public void constructMap(DipMap dipMap){
		Scanner lineScanner = null;
		String line = null; 
		int lineNo = 0;
		String mapFileName = null;
		
		try{
			mapFileName = mapFile.getCanonicalPath();
			BufferedReader br = new BufferedReader(new FileReader(mapFile));
			while ((line = br.readLine()) != null){
				lineNo++;
				Province province = new Province();
				lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				province.setIdentifier(lineScanner.next());
				province.setType(lineScanner.next());
				province.setHasSupplyCentre(lineScanner.next());
				province.setOwner(lineScanner.next());
				province.setFullName(lineScanner.next());
				province.setAliases(lineScanner.findInLine("\\(.*?\\)"));
				System.out.println("Getting neighbours for " + province.getIdentifier());
				province.setNeighbours(lineScanner.findInLine("\\(.*?[^\\(]..\\)"));
				dipMap.addProvince(province);
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
