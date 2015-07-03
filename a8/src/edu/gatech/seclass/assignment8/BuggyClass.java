package edu.gatech.seclass.assignment8;

public class BuggyClass {
	private String myString;
	
	public void setString(String str){
		myString=str;
	}
	
	public int method1(){
		if (myString!=null){
			System.out.println("this is not a null string.");
		}
		return myString.length();
	}
	
	public void method2(int i){
		System.out.println("such a method cannot be created");
	}
   
	public int method3(int i){
		if (i>0){
			System.out.println("the true branch is covered.");
		}
		return myString.length();
	}
}
