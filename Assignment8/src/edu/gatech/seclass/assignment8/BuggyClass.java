package edu.gatech.seclass.assignment8;


public class BuggyClass {
	private String myString;
	
	public void setString(String string) {
		this.myString = string;
	}
	
	public void method1{
		if (myString.length()>0){
			println(myString);
		}
		
	}
	
	public void method2{
		println("such a method cannot be created.");
		
	}
	
	
	public void method3{
		println("such a method cannot be created.");
	}
}
