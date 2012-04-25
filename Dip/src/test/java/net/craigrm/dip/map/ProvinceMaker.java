package net.craigrm.dip.map;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.craigrm.dip.map.properties.Owner;
import net.craigrm.dip.map.properties.Supply;
import net.craigrm.dip.map.properties.Terrains;

public class ProvinceMaker {

	public static final Set<ProvinceIdentifier> EMPTY_ALIASES = Collections.emptySet();
	public static final Set<ProvinceIdentifier>  EMPTY_NEIGHBOURS = Collections.emptySet();
	
	public static Province makeProvince(Terrains type, String id) {
		Province p = new Province(new ProvinceIdentifier(id), type, Supply.NONE, new Owner(""), "Any Name", EMPTY_ALIASES, EMPTY_NEIGHBOURS);
		return p;
	}
	
	public static Province makeProvinceWithAliases(Terrains type, String id, String aliasIds ) {
		Set<ProvinceIdentifier> aliases = new HashSet<ProvinceIdentifier>();
		for(String aliasId:aliasIds.split("\\s*,\\s*")) {
			aliases.add(new ProvinceIdentifier(aliasId));
		}
		Province p = new Province(new ProvinceIdentifier(id), type, Supply.NONE, new Owner(""), "Any Name", aliases, EMPTY_NEIGHBOURS);
		return p;
	}
	
	public static Province makeProvinceWithNeighbours(Terrains type, String id, String neighbourIds ) {
		Set<ProvinceIdentifier> neighbours = new HashSet<ProvinceIdentifier>();
		for(String neighbourId:neighbourIds.split("\\s*,\\s*")) {
			neighbours.add(new ProvinceIdentifier(neighbourId));
		}
		Province p = new Province(new ProvinceIdentifier(id), type, Supply.NONE, new Owner(""), "Any Name", EMPTY_ALIASES, neighbours);
		return p;
	}
	
	public static Province makeProvinceWithAliasesAndNeighbours(Terrains type, String id, String aliasIds, String neighbourIds ) {
		Set<ProvinceIdentifier> aliases = new HashSet<ProvinceIdentifier>();
		Set<ProvinceIdentifier> neighbours = new HashSet<ProvinceIdentifier>();
		for(String aliasId:aliasIds.split("\\s*,\\s*")) {
			aliases.add(new ProvinceIdentifier(aliasId));
		}
		for(String neighbourId:neighbourIds.split("\\s*,\\s*")) {
			neighbours.add(new ProvinceIdentifier(neighbourId));
		}
		Province p = new Province(new ProvinceIdentifier(id), type, Supply.NONE, new Owner(""), "Any Name", aliases, neighbours);
		return p;
	}
	
	
}
