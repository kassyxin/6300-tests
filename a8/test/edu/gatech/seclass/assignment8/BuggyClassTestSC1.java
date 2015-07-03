package edu.gatech.seclass.assignment8;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC1 {
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
	public void testMethod1() {
		
		buggyclass.setString("Strawberry");
		assertEquals(10,buggyclass.method1());
	}

}
