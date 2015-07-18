package edu.gatech.seclass.gradescalculator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
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
	public InputStream file;
	public OutputStream outFile;
	public String gradesDB;
	
	//constructor
	public Grades (String gradesDB){
		//InputStream file;
        try {
            file = new FileInputStream(gradesDB);
            //outFile =new FileOutputStream(gradesDB);
            try {
            	this.gradesDB=gradesDB;
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
		Row row=individualGrades.getRow(0);
		return row.getLastCellNum()-1;
	}
	
	public int getNumProjects(){
		Row row=individualContribs.getRow(0);
		return row.getLastCellNum()-1;
	}
	
	public void output(){
		try{
			file.close();
			outFile =new FileOutputStream(gradesDB);
			workbook.write(outFile);
			outFile.close();
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }				
	}
	
	
	public void addAssignment(String assname) {
		Row row=individualGrades.getRow(0);
		int i=row.getLastCellNum();
		Cell c=row.createCell(i);
		c.setCellValue(assname);
		output();
	}
	
    public int findStudentRow(String gtid){
		int target=1;
		for (int i=1; i<=individualContribs.getLastRowNum(); i++){
			Row row=individualContribs.getRow(i);
			if (String.valueOf((int) row.getCell(0).getNumericCellValue()).equals(gtid)){
				target=i;
				break;
			}		
		}
		return target;
    }
	
	public void addGradesForAssignment(String assignmentName, HashMap<Student, Integer> grades){
		// find assignment
		int col=1;
		Row r=individualGrades.getRow(0);		
		for (int i=1; i<=getNumAssigments(); i++){
			if (r.getCell(i).getStringCellValue().equals(assignmentName)){
				col=i;
				break;
			}
		}
		// find student row
		for (Student s : grades.keySet()){
			//System.out.println(s.getGtid());
			int rowNum=findStudentRow(s.getGtid());
			Row targetRow=individualGrades.getRow(rowNum);
			//System.out.println(rowNum);
			Cell c= targetRow.createCell(col);
			c.setCellType(Cell.CELL_TYPE_NUMERIC);
			c.setCellValue((double) grades.get(s));
		}
		
		output();
		
	}
	
	public void addIndividualContributions(String projectName, HashMap<Student, Integer> contributions){
		//find project
		int col=1;
		Row r=individualContribs.getRow(0);		
		for (int i=1; i<=getNumProjects(); i++){
			if (r.getCell(i).getStringCellValue().equals(projectName)){
				col=i;
				break;
			}
		}
		// find student row
		for (Student s : contributions.keySet()){
			//System.out.println(s.getGtid());
			int rowNum=findStudentRow(s.getGtid());
			Row targetRow=individualContribs.getRow(rowNum);
			//System.out.println(rowNum);
			Cell c= targetRow.createCell(col);
			c.setCellType(Cell.CELL_TYPE_NUMERIC);
			c.setCellValue((double) contributions.get(s));
		}
		
		output();
	}
	
	
}