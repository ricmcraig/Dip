package net.craigrm.dip.map;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.craigrm.dip.map.properties.Terrains;

import org.junit.Test;

public class DipMapConvoyTest {

	@Test
	public void testSingleSimpleConvoyRoute() {
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "ABC", "DEF");
		Province p2 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "GHI", "DEF");
		Province p3 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "DEF", "ABC, GHI");
		Set<ProvinceIdentifier> cps = makeConvoys(p3);
		DipMap.reloadMap(new MapperStub(p1, p2, p3));
		DipMap dm = DipMap.getMap();
		Set<List<ProvinceIdentifier>> routes = dm.getConvoyRoutes(new ProvinceIdentifier("ABC"), new ProvinceIdentifier("GHI"), cps);
		assertEquals("Should be a single convoy route", 1, routes.size());
		assertEquals("Should be correct convoy route", makeRoutes("ABC, DEF, GHI"), routes);
	}

	@Test
	public void testNoConvoyingFleets() {
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "ABC", "DEF");
		Province p2 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "GHI", "DEF");
		Province p3 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "DEF", "ABC, GHI");
		Set<ProvinceIdentifier> cps = makeConvoys();
		DipMap.reloadMap(new MapperStub(p1, p2, p3));
		DipMap dm = DipMap.getMap();
		Set<List<ProvinceIdentifier>> routes = dm.getConvoyRoutes(new ProvinceIdentifier("ABC"), new ProvinceIdentifier("GHI"), cps);
		assertTrue("Should be a no convoy routes", routes.isEmpty());
	}

	@Test
	public void testNoConvoyRoute() {
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "ABC", "DEF");
		Province p2 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "GHI", "JKL");
		Province p3 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "DEF", "ABC");
		Set<ProvinceIdentifier> cps = makeConvoys();
		DipMap.reloadMap(new MapperStub(p1, p2, p3));
		DipMap dm = DipMap.getMap();
		Set<List<ProvinceIdentifier>> routes = dm.getConvoyRoutes(new ProvinceIdentifier("ABC"), new ProvinceIdentifier("GHI"), cps);
		assertTrue("Should be a no convoy routes", routes.isEmpty());
	}

	@Test
	public void testStartNotCoastal() {
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.INLAND, "ABC", "DEF");
		Province p2 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "GHI", "DEF");
		Province p3 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "DEF", "ABC, GHI");
		Set<ProvinceIdentifier> cps = makeConvoys(p3);
		DipMap.reloadMap(new MapperStub(p1, p2, p3));
		DipMap dm = DipMap.getMap();
		Set<List<ProvinceIdentifier>> routes = dm.getConvoyRoutes(new ProvinceIdentifier("ABC"), new ProvinceIdentifier("GHI"), cps);
		assertTrue("Should be a no convoy routes", routes.isEmpty());
	}

	@Test
	public void testDestinationNotCoastal() {
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "ABC", "DEF");
		Province p2 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "GHI", "DEF");
		Province p3 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "DEF", "ABC, GHI");
		Set<ProvinceIdentifier> cps = makeConvoys(p3);
		DipMap.reloadMap(new MapperStub(p1, p2, p3));
		DipMap dm = DipMap.getMap();
		Set<List<ProvinceIdentifier>> routes = dm.getConvoyRoutes(new ProvinceIdentifier("ABC"), new ProvinceIdentifier("GHI"), cps);
		assertTrue("Should be a no convoy routes", routes.isEmpty());
	}

	@Test
	public void testSingleLongConvoyRoute() {
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "ABC", "DEF");
		Province p2 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "PQR", "MNO");
		Province p3 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "DEF", "ABC, GHI");
		Province p4 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "GHI", "DEF, JKL");
		Province p5 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "JKL", "GHI, MNO");
		Province p6 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "MNO", "JKL, PQR");
		Set<ProvinceIdentifier> cps = makeConvoys(p3, p4, p5, p6);
		DipMap.reloadMap(new MapperStub(p1, p2, p3, p4, p5, p6));
		DipMap dm = DipMap.getMap();
		Set<List<ProvinceIdentifier>> routes = dm.getConvoyRoutes(new ProvinceIdentifier("ABC"), new ProvinceIdentifier("PQR"), cps);
		assertEquals("Should be a single convoy route", 1, routes.size());
		assertEquals("Should be correct convoy route", makeRoutes("ABC, DEF, GHI, JKL, MNO, PQR"), routes);
	}

	@Test
	public void testMultipleSimpleConvoyRoutes() {
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "ABC", "DEF, GHI");
		Province p2 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "JKL", "DEF, GHI");
		Province p3 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "DEF", "ABC, JKL");
		Province p4 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "GHI", "ABC, JKL");
		Set<ProvinceIdentifier> cps = makeConvoys(p3, p4);
		DipMap.reloadMap(new MapperStub(p1, p2, p3, p4));
		DipMap dm = DipMap.getMap();
		Set<List<ProvinceIdentifier>> routes = dm.getConvoyRoutes(new ProvinceIdentifier("ABC"), new ProvinceIdentifier("JKL"), cps);
		assertEquals("Should be two convoy routes", 2, routes.size());
		assertEquals("Should be correct convoy routes", makeRoutes("ABC, DEF, JKL", "ABC, GHI, JKL"), routes);
	}

	@Test
	public void testShortestFromMultipleRedundentConvoyRoutes() {
		Province p1 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "ABC", "DEF, GHI");
		Province p2 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.COAST, "JKL", "GHI");
		Province p3 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "DEF", "ABC, GHI");
		Province p4 = ProvinceMaker.makeProvinceWithNeighbours(Terrains.SEA, "GHI", "ABC, DEF, JKL");
		Set<ProvinceIdentifier> cps = makeConvoys(p3, p4);
		DipMap.reloadMap(new MapperStub(p1, p2, p3, p4));
		DipMap dm = DipMap.getMap();
		Set<List<ProvinceIdentifier>> routes = dm.getConvoyRoutes(new ProvinceIdentifier("ABC"), new ProvinceIdentifier("JKL"), cps);
		assertEquals("Should be one convoy route", 1, routes.size());
		assertEquals("Should be correct convoy routes", makeRoutes("ABC, GHI, JKL"), routes);
	}

	private Set<ProvinceIdentifier> makeConvoys(Province... convoyProvinces) {
		Set<ProvinceIdentifier> convoys = new HashSet<ProvinceIdentifier>();
		for(Province province:convoyProvinces) {
			convoys.add(province.getIdentifier());
		}
		return convoys;
	}

	private Set<List<ProvinceIdentifier>> makeRoutes(String... routes) {
		Set<List<ProvinceIdentifier>> convoyRoutes = new HashSet<List<ProvinceIdentifier>>();
		for(String route:routes) {
			List<ProvinceIdentifier> convoyRoute= new LinkedList<ProvinceIdentifier>();
			for(String pi:route.split("\\s*,\\s*")) {
				convoyRoute.add(new ProvinceIdentifier(pi));
			}
			convoyRoutes.add(convoyRoute);
		}
		return convoyRoutes;
	}
}
