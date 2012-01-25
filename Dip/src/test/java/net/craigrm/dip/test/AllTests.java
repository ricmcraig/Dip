package net.craigrm.dip.test;

import net.craigrm.dip.map.MapTests;
import net.craigrm.dip.map.properties.MapPropertiesTests;
import net.craigrm.dip.orders.properties.OrdersPropertiesTests;
import net.craigrm.dip.scanners.ScannersTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({MapTests.class, MapPropertiesTests.class, OrdersPropertiesTests.class, ScannersTests.class})
public class AllTests {

}
