package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import java.util.Arrays;
import net.craigrm.dip.scanners.BracketedCSVScanner;

import org.junit.Test;

public class BracketedCSVScannerTest {

	@Test(expected=IllegalArgumentException.class) 
	public void passedNullString() {
		new BracketedCSVScanner(null);
	}

	@Test(expected=IllegalArgumentException.class) 
	public void passedEmptyString() {
		new BracketedCSVScanner("");
	}

	@Test(expected=IllegalArgumentException.class) 
	public void passedBadString() {
		new BracketedCSVScanner("X");
	}

	@Test
	public void passedEmptyList() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("()");
		assertEquals("Should be empty",0 ,bcs.getElements().length);
	}

	@Test
	public void passedSingleElementString() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("(XYZ)");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an XYZ Element", Arrays.asList(elements).contains("XYZ"));
	}

	@Test
	public void passedManyElementStrings() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("(ABC,DEF,GHI,JKL)");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an ABC Element", Arrays.asList(elements).contains("ABC"));
		assertTrue("Should contain a DEF Element", Arrays.asList(elements).contains("DEF"));
		assertTrue("Should contain a GHI Element", Arrays.asList(elements).contains("GHI"));
		assertTrue("Should contain a JKL Element", Arrays.asList(elements).contains("JKL"));
	}

	@Test
	public void passedManyElementStringsWithWhiteSpace() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("( ABC, DEF ,GHI,  JKL  )");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an ABC Element", Arrays.asList(elements).contains(" ABC"));
		assertTrue("Should contain a DEF Element", Arrays.asList(elements).contains(" DEF "));
		assertTrue("Should contain a GHI Element", Arrays.asList(elements).contains("GHI"));
		assertTrue("Should contain a JKL Element", Arrays.asList(elements).contains("  JKL  "));
	}

	@Test
	public void passedSingleElementStringWithBrackets() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("(XYZ(NC))");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an ABC Element", Arrays.asList(elements).contains("XYZ(NC)"));
	}

	@Test
	public void passedManyElementStringsWithBrackets() {
		BracketedCSVScanner bcs = new BracketedCSVScanner("( ABC(NC), DEF(EC) ,GHI(SC),  JKL(WC)  )");
		String[] elements = bcs.getElements();
		assertTrue("Should contain an ABC Element", Arrays.asList(elements).contains(" ABC(NC)"));
		assertTrue("Should contain a DEF Element", Arrays.asList(elements).contains(" DEF(EC) "));
		assertTrue("Should contain a GHI Element", Arrays.asList(elements).contains("GHI(SC)"));
		assertTrue("Should contain a JKL Element", Arrays.asList(elements).contains("  JKL(WC)  "));
	}

}
