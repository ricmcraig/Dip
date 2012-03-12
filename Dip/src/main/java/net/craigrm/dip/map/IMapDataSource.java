package net.craigrm.dip.map;
import java.util.Set;

/**
 * A unit that provides a set of Provinces representing a Diplomacy map.
 * 
 * @author Ric Craig
 *
 */
public interface IMapDataSource {

	/**
	 * 
	 * @return a set of {@link net.craigrm.net.dip.map.Province}
	 */
	Set<Province> getProvinces();
	
}
