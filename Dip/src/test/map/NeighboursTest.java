package map;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class NeighboursTest {

	@Test
	public void containsNeighbourWhenConstructedWithSingleNeighbour() {
		Neighbours n = new Neighbours("(XYZ)");
		assertTrue("Should contain an XYZ Identifier", n.contains(new Identifier("XYZ")));
	}

	@Test
	public void containsAllNeighbourWhenConstructedWithManyNeighbours() {
		Neighbours n = new Neighbours("(ABC, DEF ,GHI, JKL)");
		assertTrue("Should contain an ABC Identifier", n.contains(new Identifier("ABC")));
		assertTrue("Should contain an DEF Identifier", n.contains(new Identifier("DEF")));
		assertTrue("Should contain an GHI Identifier", n.contains(new Identifier("GHI")));
		assertTrue("Should contain an JKL Identifier", n.contains(new Identifier("JKL")));
	}

	@Test
	public void containsAnySingleNeighbour() {
		Neighbours n = new Neighbours("(ABC, DEF ,GHI, JKL)");
		Set<Identifier> any = new HashSet<Identifier>();
		any.add(new Identifier("ABC"));
		assertTrue("Should contain neighbouring identifiers", n.containsAny(any));
	}

	@Test
	public void containsAnyMixNeighboursAndNonNeighbours() {
		Neighbours n = new Neighbours("(ABC, DEF ,GHI, JKL)");
		Set<Identifier> any = new HashSet<Identifier>();
		any.add(new Identifier("ABC"));
		any.add(new Identifier("XYZ"));
		assertTrue("Should contain some neighbouring identifiers", n.containsAny(any));
	}

	@Test
	public void doesNotContainAnyNonNeighbours() {
		Neighbours n = new Neighbours("(ABC, DEF ,GHI, JKL)");
		Set<Identifier> any = new HashSet<Identifier>();
		any.add(new Identifier("XYZ"));
		assertFalse("Should not contain any non neighbourng identifiers", n.containsAny(any));
	}

	@Test
	public void formatsCorrectlyAsString() {
		final String TO_STRING = "ABC, DEF, GHI, JKL";   
		Neighbours n = new Neighbours("(ABC, DEF, GHI, JKL)");
		assertEquals("Show print as comma-and-space separated list of identifiers", TO_STRING, n.toString());
	}

	@Test
	public void formatsAlphabeticallyAsString() {
		final String TO_STRING = "ABC, DEF, GHI, JKL";   
		Neighbours n = new Neighbours("(JKL, DEF, ABC, GHI)");
		assertEquals("Show print as comma-and-space separated list of identifiers", TO_STRING, n.toString());
	}

}
