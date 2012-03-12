package net.craigrm.dip.scanners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BracketedCSVScannerTest.class, MapperDuplicateIdentifiersTest.class, MapperIdentifierListTest.class, MapperMissingElementsTest.class, MapperTest.class, OrdersParserTest.class , PositionParserTest.class })
public class ScannersTests {

}
