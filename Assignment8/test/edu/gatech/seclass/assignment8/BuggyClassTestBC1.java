package edu.gatech.seclass.assignment8;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuggyClassTestBC1 {

	@Test
	public void testMethod1a() {
		BuggyClass buggyClass = new BuggyClass();
		buggyClass.setString("this is not an empty string");
		assertEquals("this is not an empty string",buggyClass.method1());
	}
	
	@Test (expected = NullPointerException.class )
	public void testMethod1b() {
		BuggyClass buggyClass = new BuggyClass();
		buggyClass.setString(null);
		buggyClass.method1();
	}
}
