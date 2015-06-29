package edu.gatech.seclass.assignment8;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuggyClassTestSC1 {

	@Test
	public void testMethod1() {
		BuggyClass buggyClass = new BuggyClass();
		buggyClass.setString("this is not an empty string");
		assertEquals("this is not an empty string",buggyClass.method1());
	}

}
