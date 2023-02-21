package telran.spring.data.service;

import telran.spring.data.model.*;
import telran.spring.data.proj.*;

import java.util.*;

public interface CollegeService {
	void addStudent(Student student);
	void addSubject(Subject subject);
	void addMark(Mark mark);
	List<MarkProj> getMarksByNameSubject(String name, String subject);
	List<StudentSubjectMark> getMarksByName(String name);
	List<StudentAvgMark> getStudentsAvgMark(); //Returns student names and average mark
	List<StudentName> getBestStudents();//returns names of students having average mark greater than average mark of all students
	List<StudentName> getTopBestStudents(int nStudents); //returns names of nStudents best students
	List <StudentName> getTopBestStudentsSubject(int nStudents, String subject); //returns names of nStudents best students on a given subject
	List<StudentSubjectMark> getMarksOfWorstStudents(int nStudents); //returns data about marks for nStudents worst student
	List<IntervalMarksCount> marksDistribution(int interval);//returns distribution of marks as list of objects, 
	//each object contains minimal / maximal interval values and count of occurrences
	List<String> getSqlQuery(String sqlQuery);
	List<String> getJpqlQuery(String jpqlQuery);
	List<String> removeStudents(double markCountLess); //removing all students having avg(mark) * count(mark) less than a given value
	List<String> removeLeastPopularSubjects(int marksThreshold);
}
