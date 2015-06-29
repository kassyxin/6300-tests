package edu.gatech.seclass.assignment8;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuggyClassTestSC2 {

	@Test
	public void testMethod2a() {
		BuggyClass buggyClass = new BuggyClass();
		buggyClass.setString("this is not an empty string");
		assertEquals("this is not an empty string",buggyClass.method2(3));
	}
	
	@Test (expected = NullPointerException.class )
	public void testMethod2b() {
		BuggyClass buggyClass = new BuggyClass();
		buggyClass.setString(null);
		buggyClass.method2(-1);
	}
	
}
