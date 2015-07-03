package edu.gatech.seclass.assignment8;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;

public class BuggyClassTestBC1 {
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
	public void testMethod1a() {		
		buggyclass.setString("banana");
		assertEquals(6,buggyclass.method1());
	}
	
	@Test
	public void testMethod1b() {
		buggyclass.setString(null);
		buggyclass.method1();
	}
}
