package edu.gatech.seclass.gradescalculator;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MyCourseTest {
	
    Students students = null;
    Grades grades = null;
    Course course = null;
    static final String GRADES_DB = "DB" + File.separator + "GradesDatabase6300-grades.xlsx";
    static final String GRADES_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-grades-goldenversion.xlsx";
    static final String STUDENTS_DB = "DB" + File.separator + "GradesDatabase6300-students.xlsx";
    static final String STUDENTS_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-students-goldenversion.xlsx";
    
    @Before
    public void setUp() throws Exception {
        FileSystem fs = FileSystems.getDefault();
        Path gradesdbfilegolden = fs.getPath(GRADES_DB_GOLDEN);
        Path gradesdbfile = fs.getPath(GRADES_DB);
        Files.copy(gradesdbfilegolden, gradesdbfile, REPLACE_EXISTING);
        Path studentsdbfilegolden = fs.getPath(STUDENTS_DB_GOLDEN);
        Path studentsdbfile = fs.getPath(STUDENTS_DB);
        Files.copy(studentsdbfilegolden, studentsdbfile, REPLACE_EXISTING);    	
    	students = new Students(STUDENTS_DB);
        grades = new Grades(GRADES_DB);
        course = new Course(students, grades);
    }

    @After
    public void tearDown() throws Exception {
        students = null;
        grades = null;
        course = null;
    }
    
    // new tests written by me
    
    @Test
    public void testAddStudents() {
    	HashSet <Student> newStudents=new HashSet <Student> ();
    	Student student1 = new Student("Johnathan Smith", "901234517", course);
        Student student2 = new Student("Anna Betty", "901234518", course);
    	newStudents.add(student1);
    	newStudents.add(student2);
    	course.addStudents(newStudents);//assuming the students database is updated by the addStudents function
    	course.updateGrades(new Grades(GRADES_DB));
    	assertEquals(18, course.getNumStudents());    	
    }
       
    @Test
    public void testAddProjects() {
    	HashSet <String> newProjects=new HashSet <String> ();
    	newProjects.add("Project 4");
    	newProjects.add("Project 5");
    	course.addProjects(newProjects);
    	course.updateGrades(new Grades(GRADES_DB));
    	assertEquals(5, course.getNumProjects());    	
    }
    

    @Test
    public void testAddGradesForProject() {
    	//add a new project
        String projectName = "Project 4";
    	HashSet <String> newProjects=new HashSet <String> ();
    	newProjects.add(projectName);
    	course.addProjects(newProjects);    	
        course.updateGrades(new Grades(GRADES_DB));
        //add team grades
        HashMap<Student, Integer> teamGrades = new HashMap<Student, Integer>();
        Student student1=new Student("Ernesta Anderson", "901234510", course);//in team1
        Student student2=new Student("Josepha Jube", "901234502", course);//in team2
        grades.put(student1, 98);
        grades.put(student2, 100);
        //assuming the addGradesForProject will match the team number from individual student's information
        course.addGradesForProject(projectName, teamGrades);
        course.updateGrades(new Grades(GRADES_DB));
        
        //add individual contributions
        HashMap<Student, Integer> contributions2 = new HashMap<Student, Integer>();
        contributions2.put(student1, 100);
        contributions2.put(student2, 100);
        course.addIndividualContributions(projectName, contributions2);
        course.updateGrades(new Grades(GRADES_DB)); 
        
        //assertions
        assertEquals(91, course.getAverageProjectsGrade(student1));
        assertEquals(90, course.getAverageProjectsGrade(student2));
    }

}
