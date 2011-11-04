package scanners;

import static org.junit.Assert.*;

import java.util.Set;

import scanners.BracketedCSVScanner;
import map.Identifier;

import org.junit.Test;

public class BracketedCSVScannerTest {

	@Test(expected=IllegalArgumentException.class) 
	public void passedNullString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		@SuppressWarnings("unused")
		Set<Identifier> rs = bcs.getUniqueIdentifiers(null);
	}

	@Test(expected=IllegalArgumentException.class) 
	public void passedEmptyString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		@SuppressWarnings("unused")
		Set<Identifier> rs = bcs.getUniqueIdentifiers("");
	}

	@Test(expected=IllegalArgumentException.class) 
	public void passedBadString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		@SuppressWarnings("unused")
		Set<Identifier> rs = bcs.getUniqueIdentifiers("X");
	}

	@Test
	public void passedNoBracketedCSVScanner() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		Set<Identifier> rs = bcs.getUniqueIdentifiers("()");
		assertTrue("Should be empty", rs.isEmpty());
	}

	@Test
	public void passedSingleIdentifierString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		Set<Identifier> rs = bcs.getUniqueIdentifiers("(XYZ)");
		assertTrue("Should contain an XYZ Identifier", rs.contains(new Identifier("XYZ")));
	}

	@Test
	public void passedManyIdentifierStrings() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		Set<Identifier> rs = bcs.getUniqueIdentifiers("(ABC,DEF,GHI,JKL)");
		assertTrue("Should contain an ABC Identifier", rs.contains(new Identifier("ABC")));
		assertTrue("Should contain an DEF Identifier", rs.contains(new Identifier("DEF")));
		assertTrue("Should contain an GHI Identifier", rs.contains(new Identifier("GHI")));
		assertTrue("Should contain an JKL Identifier", rs.contains(new Identifier("JKL")));
	}

	@Test
	public void passedManyIdentifierStringsWithWhiteSpace() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		Set<Identifier> rs = bcs.getUniqueIdentifiers("( ABC, DEF ,GHI,  JKL  )");
		assertTrue("Should contain an ABC Identifier", rs.contains(new Identifier("ABC")));
		assertTrue("Should contain an DEF Identifier", rs.contains(new Identifier("DEF")));
		assertTrue("Should contain an GHI Identifier", rs.contains(new Identifier("GHI")));
		assertTrue("Should contain an JKL Identifier", rs.contains(new Identifier("JKL")));
	}

	@Test
	public void passedSingleCoastalIdentifierStrings() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		Set<Identifier> rs = bcs.getUniqueIdentifiers("(XYZ(NC))");
		assertTrue("Should contain an ABC Identifier", rs.contains(new Identifier("XYZ(NC)")));
	}

	@Test
	public void passedManyCoastalIdentifierStrings() {
		BracketedCSVScanner bcs = new BracketedCSVScanner();
		Set<Identifier> rs = bcs.getUniqueIdentifiers("( ABC(NC), DEF(EC) ,GHI(SC),  JKL(WC)  )");
		assertTrue("Should contain an ABC Identifier", rs.contains(new Identifier("ABC(NC)")));
		assertTrue("Should contain an DEF Identifier", rs.contains(new Identifier("DEF(EC)")));
		assertTrue("Should contain an GHI Identifier", rs.contains(new Identifier("GHI(SC)")));
		assertTrue("Should contain an JKL Identifier", rs.contains(new Identifier("JKL(WC)")));
	}

}
