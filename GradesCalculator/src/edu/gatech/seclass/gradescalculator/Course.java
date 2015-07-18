package edu.gatech.seclass.gradescalculator;
//import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;


public class Course {

	public Students students;
	public Grades grades;
	
	//constructor
	public Course(Students students, Grades grades){
		this.students=students;
		this.grades=grades;	
	}
	
	public int getNumStudents(){
		return students.getNumStudents();
	}
	
	public int getNumAssignments(){
		return grades.getNumAssigments();
	}
	
	public int getNumProjects(){
		return grades.getNumProjects();
	}
	
	public HashSet<Student> getStudents(){
		HashSet<Student> s = new HashSet<Student>();
		int studentNum=students.getNumStudents();
		for (int i=1; i<=studentNum;i++ ){
			Student student = new Student();			
			//set student's name and gtid
			Row info=students.studentsInfo.getRow(i);
			//System.out.println(info.getCell(0).getStringCellValue());
			student.setName(info.getCell(0).getStringCellValue());
			//System.out.println(String.valueOf((int) info.getCell(1).getNumericCellValue()));
			student.setGtid(String.valueOf((int) info.getCell(1).getNumericCellValue()));
			//System.out.println(student.getName());
			
			//set student's team number
			String name = info.getCell(0).getStringCellValue();
			String teamNum=findTeam(name);
			student.setTeam(teamNum);
			
			//set student's attendance
			Row attendanceinfo=grades.attendance.getRow(i);
			student.setAttendance((int)attendanceinfo.getCell(1).getNumericCellValue());
		    
			//add the student to the set
			s.add(student);
		}
		
		return s;
	}
	
	public String findTeam(String name){
		String teamNum=null;
		int i=1;
		int teamLen=grades.teamGrades.getLastRowNum();
		//System.out.println(teamLen);
		while (i<=teamLen && teamNum==null){
			Row row=students.teams.getRow(i);
			int memberLen=row.getLastCellNum();
			//System.out.println(memberLen);
			for (int j=1; j<memberLen; j++){
				String sname=row.getCell(j).getStringCellValue();
				if (sname.equals(name)){
					teamNum=row.getCell(0).getStringCellValue();
					break;
				}
			}
			i++;
		}
		//System.out.println(teamNum);
		return teamNum;
	}
	
	public Student getStudentByName(String name){
		HashSet<Student> st= getStudents();
		Student s= new Student();
		for (Student individual: st ){
			if (individual.getName().equals(name)){
				 s=individual;
				 break;
			}
		}
		return s;
		
	}
	
	public Student getStudentByID(String id){
		HashSet<Student> st= getStudents();
		Student s= new Student();
		for (Student individual: st ){
			if (individual.getGtid().equals(id)){
				 s=individual;
				 break;
			}
		}
		return s;
		
	}
	
	public void addAssignment(String assname){
		grades.addAssignment(assname);
	}
	
	public void updateGrades(Grades newgrades){
		this.grades=newgrades;
	}
	
	public int getAverageAssignmentsGrade(Student student){
		String GTID= student.getGtid();
		int target=grades.findStudentRow(GTID);	
		Row targetRow=grades.individualGrades.getRow(target);
		double total=0;
		for (int j=1; j<=getNumAssignments();j++){
			//System.out.println(targetRow.getCell(j).getNumericCellValue());
			total=total+ targetRow.getCell(j).getNumericCellValue();
		}
		return (int) (total/getNumAssignments()+0.5);	
	}
	
	public int getAverageProjectsGrade(Student student){
		int projectNum=getNumProjects(); 
		
		//get the individual contribs
		String GTID= student.getGtid();
		int target=grades.findStudentRow(GTID);	
		//System.out.println(target);
		Row targetRow=grades.individualContribs.getRow(target);
		double [] indiContribs=new double[projectNum];
		for (int j=1; j<=projectNum;j++){
			indiContribs[j-1]= targetRow.getCell(j).getNumericCellValue();			
		}
		//System.out.println(indiContribs[0]);
		//get the team grades
		String teamNum=findTeam(student.getName());
		int tNum=1;
		for (int i=1; i<=grades.teamGrades.getLastRowNum(); i++){
			Row row=grades.teamGrades.getRow(i);
			if (row.getCell(0).getStringCellValue().equals(teamNum)){
				tNum=i;
				break;
			}		
		}
		
		Row teamRow=grades.teamGrades.getRow(tNum);
		double [] teamGrades=new double[projectNum];
		for (int j=1; j<=projectNum;j++){
			teamGrades[j-1]=teamRow.getCell(j).getNumericCellValue();			
		}
		//System.out.println(teamGrades[0]);		
		double total=0;
		for (int k=0; k<projectNum; k++){
			total=total+indiContribs[k]*teamGrades[k];
		}
		return (int) (total/(100*projectNum)+0.5);	
	}
	
	public void addGradesForAssignment(String assignmentName, HashMap<Student, Integer> grades){
		this.grades.addGradesForAssignment(assignmentName, grades);	
	}
	
	public void addIndividualContributions(String projectName, HashMap<Student, Integer> contributions){
		this.grades.addIndividualContributions(projectName, contributions);
	}
	
}
