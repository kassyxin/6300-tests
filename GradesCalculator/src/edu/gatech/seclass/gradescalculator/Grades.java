package edu.gatech.seclass.gradescalculator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class Grades {
	
	public Workbook workbook;
	public Sheet attendance;
	public Sheet individualGrades;
	public Sheet individualContribs;
	public Sheet teamGrades;
	
	//constructor
	public Grades (String gradesDB){
		InputStream file;
        try {
            file = new FileInputStream(gradesDB);
            try {
                this.workbook = WorkbookFactory.create(file);
                this.attendance = workbook.getSheet("Attendance");
                this.individualGrades = workbook.getSheet("IndividualGrades");
                this.individualContribs = workbook.getSheet("IndividualContribs");
                this.teamGrades=workbook.getSheet("TeamGrades");
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }				
	}
	
	
	public int getNumAssigments(){
		//System.out.println(individualGrades.getLastRowNum());
		//System.out.println(teamGrades.getLastRowNum());
		Row row=individualGrades.getRow(1);
		return row.getLastCellNum()-1;
	}
	
	public int getNumProjects(){
		Row row=individualContribs.getRow(1);
		return row.getLastCellNum()-1;
	}
	
}