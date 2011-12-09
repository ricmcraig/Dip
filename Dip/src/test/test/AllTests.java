package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import map.MapTests;
import map.properties.MapPropertiesTests;
import scanners.ScannersTests;

@RunWith(Suite.class)
@SuiteClasses({MapTests.class, MapPropertiesTests.class, ScannersTests.class})
public class AllTests {

}
