package edu.gatech.seclass.assignment8;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestBC3 {
	
	private BuggyClass buggyclass;
	
	@Before
	public void setUp() throws Exception {
		buggyclass = new BuggyClass();
	}

	@After
	public void tearDown() throws Exception {
		buggyclass = null;
	}
	
	@Test
	public void testMethod3a() {
		buggyclass.setString("cherry");
		assertEquals(6,buggyclass.method3(3));
	}
	
	@Test
	public void testMethod3b() {
		buggyclass.setString("grape");
		assertEquals(5,buggyclass.method3(0));
	}
	
}
