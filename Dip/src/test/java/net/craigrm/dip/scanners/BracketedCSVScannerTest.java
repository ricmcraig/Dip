package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Set;

import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.scanners.BracketedCSVScanner;

import org.junit.Test;

public class BracketedCSVScannerTest {

	@Test(expected=IllegalArgumentException.class) 
	public void passedNullString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner(null);
	}

	@Test(expected=IllegalArgumentException.class) 
	public void passedEmptyString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("");
	}

	@Test(expected=IllegalArgumentException.class) 
	public void passedBadString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("X");
	}

	@Test
	public void passedNoBracketedCSVScanner() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("()");
		assertEquals("Should be empty",0 ,bcs.getElements().length);
	}

	@Test
	public void passedSingleIdentifierString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("(XYZ)");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an XYZ Identifier", Arrays.asList(elements).contains("XYZ"));
	}

	@Test
	public void passedManyIdentifierStrings() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("(ABC,DEF,GHI,JKL)");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an ABC Identifier", Arrays.asList(elements).contains("ABC"));
		assertTrue("Should contain a DEF Identifier", Arrays.asList(elements).contains("DEF"));
		assertTrue("Should contain a GHI Identifier", Arrays.asList(elements).contains("GHI"));
		assertTrue("Should contain a JKL Identifier", Arrays.asList(elements).contains("JKL"));
	}

	@Test
	public void passedManyIdentifierStringsWithWhiteSpace() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("( ABC, DEF ,GHI,  JKL  )");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an ABC Identifier", Arrays.asList(elements).contains(" ABC"));
		assertTrue("Should contain a DEF Identifier", Arrays.asList(elements).contains(" DEF "));
		assertTrue("Should contain a GHI Identifier", Arrays.asList(elements).contains("GHI"));
		assertTrue("Should contain a JKL Identifier", Arrays.asList(elements).contains("  JKL  "));
	}

	@Test
	public void passedSingleCoastalIdentifierStrings() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("(XYZ(NC))");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an ABC Identifier", Arrays.asList(elements).contains("XYZ(NC)"));
	}

	@Test
	public void passedManyCoastalIdentifierStrings() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("( ABC(NC), DEF(EC) ,GHI(SC),  JKL(WC)  )");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an ABC Identifier", Arrays.asList(elements).contains(" ABC(NC)"));
		assertTrue("Should contain a DEF Identifier", Arrays.asList(elements).contains(" DEF(EC) "));
		assertTrue("Should contain a GHI Identifier", Arrays.asList(elements).contains("GHI(SC)"));
		assertTrue("Should contain a JKL Identifier", Arrays.asList(elements).contains("  JKL(WC)  "));
	}

}
