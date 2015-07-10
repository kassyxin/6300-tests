package edu.gatech.seclass.gradescalculator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Students {
	
	public Workbook workbook;
	public Sheet studentsInfo;
	public Sheet teams;
	
	//constructor
	public Students (String studentsDB){
		InputStream file;
        try {
            file = new FileInputStream(studentsDB);
            try {
                this.workbook = WorkbookFactory.create(file);
                this.studentsInfo = workbook.getSheet("StudentsInfo");
                this.teams = workbook.getSheet("Teams");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }				
	}
	
	public int getNumStudents(){
		return studentsInfo.getLastRowNum();
	}
	
	
}
	
	

