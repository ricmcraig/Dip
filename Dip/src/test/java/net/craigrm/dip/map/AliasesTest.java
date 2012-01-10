package net.craigrm.dip.map;

import static org.junit.Assert.*;

import net.craigrm.dip.map.Aliases;
import net.craigrm.dip.map.Identifier;

import org.junit.Test;

public class AliasesTest {

	@Test
	public void isEmptyWhenConstructedWithNoAliases() {
		Aliases a = new Aliases("()");
		assertTrue("Should be empty", a.isEmpty());
	}

	@Test
	public void containsAliasWhenConstructedWithSingleAlias() {
		Aliases a = new Aliases("(XYZ)");
		assertTrue("Should contain an XYZ Identifier", a.contains(new Identifier("XYZ")));
	}

	@Test
	public void containsAllAliasWhenConstructedWithManyAliases() {
		Aliases a = new Aliases("(ABC, DEF ,GHI, JKL)");
		assertTrue("Should contain an ABC Identifier", a.contains(new Identifier("ABC")));
		assertTrue("Should contain an DEF Identifier", a.contains(new Identifier("DEF")));
		assertTrue("Should contain an GHI Identifier", a.contains(new Identifier("GHI")));
		assertTrue("Should contain an JKL Identifier", a.contains(new Identifier("JKL")));
	}
}
