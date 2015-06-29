package edu.gatech.seclass.assignment8;

public class BuggyClass {
	private String myString;
	
	public void setString(String str){
		myString=str;
	}
	
	public String method1(){
		if (myString.length()>0){
			System.out.println(myString);
		}
		return myString;
	}
	
	public String method2(int i){
		if (i>0 && myString.length()>0){
			System.out.println(myString);
		}
		return myString;
	}

}
