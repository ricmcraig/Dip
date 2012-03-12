/**
 * Provides an immutable representation of a Diplomacy map. 
 * <p>
 * The map is represented by {@link net.craigrm.dip.map.DipMap}
 * <p>
 * To instantiate a map, the client provides a {@link net.craigrm.dip.map.IMapDataSource} 
 * implementation which parses a map definition and makes available the set of 
 * {@link net.craigrm.dip.map.Province} objects that together make up the map.
 * <p>
 * This package does not hold game or turn state. 
 */
package net.craigrm.dip.map;
