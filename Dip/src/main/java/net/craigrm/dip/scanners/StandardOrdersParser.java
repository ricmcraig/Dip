package net.craigrm.dip.scanners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import net.craigrm.dip.gameturn.IOrdersParser;
import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.orders.Order;
import net.craigrm.dip.orders.SupportingOrder;
import net.craigrm.dip.orders.properties.OrderType;
import net.craigrm.dip.orders.properties.OrderTypeFormatException;
import net.craigrm.dip.state.Unit;
import net.craigrm.dip.state.properties.UnitType;

public class StandardOrdersParser implements IOrdersParser {

	private File ordersFile;
	private Set<Order> orders = new HashSet<Order>();
	
	public StandardOrdersParser(File ordersFile){
		this.ordersFile = ordersFile;
		checkFile();
	}

	public StandardOrdersParser(String ordersFileName){
		if (ordersFileName == null)
		{
			throw new IllegalArgumentException("Orders file name not specified.");
		}
		this.ordersFile = new File(ordersFileName);
		checkFile();
		parseOrders();
	}
	
	public Set<Order> getOrders(){
		return orders;
	}

	private void parseOrders(){
		Scanner lineScanner = null;
		String line = null; 
		int lineNo = 0;
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(ordersFile));
			while ((line = br.readLine()) != null){
				lineNo++;
				lineScanner = new Scanner(line);
				lineScanner.useDelimiter(" ");
				Order order = null;
				Powers power = Powers.getPower(lineScanner.next());
				UnitType unitType = UnitType.getType(lineScanner.next());
				Identifier id = new Identifier(lineScanner.next());
				Unit unit = new Unit(id, power, unitType);
				OrderType orderType = OrderType.getOrderType(lineScanner.next());
				
 				if (orderType == OrderType.HOLD){
 					order = new Order(unit, orderType, null);
 					orders.add(order);
					continue;
				}

				if (orderType == OrderType.MOVE){
					Identifier destination = new Identifier(lineScanner.next());
 					order = new Order(unit, orderType, destination);
 					orders.add(order);
					continue;
				}

				// orderType is SUPPORT or CONVOY which are supporting orders
				UnitType supportedUnitType = UnitType.getType(lineScanner.next());
				Identifier supportedUnitPosition = new Identifier(lineScanner.next());
				Unit supportedUnit = new Unit(supportedUnitPosition, power, supportedUnitType);
				OrderType supportedOrderType = OrderType.getOrderType(lineScanner.next());
				Identifier destination = new Identifier(lineScanner.next());
				order = new SupportingOrder(unit, orderType, supportedUnit, supportedOrderType, destination);
				orders.add(order);
			}
		}
		catch (FileNotFoundException fnfe){
			throw new IllegalArgumentException("Map file " + ordersFile.getAbsolutePath() + " cannot be found.", fnfe);
		}
		catch (IOException ioe){
			throw new IllegalArgumentException("Map file " + ordersFile.getAbsolutePath() + " cannot be read.", ioe);
		}
		catch (NoSuchElementException nsee){
			throw new IllegalArgumentException("Map file " + ordersFile.getAbsolutePath() + " has problem at line: " + lineNo, nsee);
		}
		catch (OrderTypeFormatException ofe){
			throw new IllegalArgumentException("Map file " + ordersFile.getAbsolutePath() + " has problem with on Order Type at line: " + lineNo, ofe);
		}
		
	}
	
	private void checkFile() {
		if (ordersFile == null) {
			throw new IllegalArgumentException("Map file not specified.");
		}
		
		if (!ordersFile.isFile()){
			try {
				String mapFileName = ordersFile.getCanonicalPath();
				throw new IllegalArgumentException("Map file " + mapFileName + " cannot be accessed.");
			}
			catch (IOException ioe){
				throw new IllegalArgumentException ("Map file cannot be accessed.");
			}
			
		}
	}
}
