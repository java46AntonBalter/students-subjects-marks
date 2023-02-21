package telran.spring.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.data.entities.MarkEntity;
import telran.spring.data.proj.*;

public interface MarkRepository extends JpaRepository<MarkEntity, Long>{
	static final String STUDENTS_MARKS = " from students st join marks ms on st.stid=ms.stid ";
	static final String STUDENTS_SUBJECTS_MARKS = STUDENTS_MARKS + " join subjects sb on sb.suid=ms.suid ";
	static final String MIN_INTERVAL = "floor(mark/:interval) * :interval";
	List<MarkProj> findByStudentNameAndSubjectSubject(String name, String subject);
	@Query(value = "select name, subject, mark"
			+ STUDENTS_SUBJECTS_MARKS
			+ "where name=:name",nativeQuery = true)
	List<StudentSubjectMark> findByStudentName(String name);
	/*********************************************************/
	@Query(value="select name, round(avg(mark)) as avgMark " + STUDENTS_MARKS + "group by name", 
			nativeQuery = true)
	List<StudentAvgMark> studentsAvgMarks();
	/*********************************************************/
//	@Query(value="select name " + STUDENTS_MARKS + "group by name having avg(mark) >"
//			+ " (select avg(mark) from marks)", 
//			nativeQuery = true)
	@Query("select student.name as name from MarkEntity group by student.name having avg(mark) > "
			+ "(select avg(mark) from MarkEntity)")
	List<StudentName> bestStudents();
	/*********************************************************/
	@Query(value="select name " +STUDENTS_MARKS + "group by name order by avg(mark) desc"
			+ " limit :nStudents", 
			nativeQuery = true)
	List<StudentName> topBestStudents(int nStudents);
	/*********************************************************/
	@Query(value="select name " +  STUDENTS_SUBJECTS_MARKS + " where subject=:subject "
			+ "group by name order by avg(mark) desc limit :nStudents", 
			nativeQuery = true)
	List<StudentName> topBestStudentsSubject(int nStudents, String subject);
	/*********************************************************/
	@Query(value="select name, subject, mark " +  STUDENTS_SUBJECTS_MARKS + " where subject=:subject "
			+ "group by name order by avg(mark) asc limit :nStudents", 
			nativeQuery = true)
	List<StudentSubjectMark> worstStudentsMarks(int nStudents);
	/*********************************************************/
//	@Query(value="select " + MIN_INTERVAL +" as min,"
//			+  MIN_INTERVAL + " + :interval - 1 as max, "
//			+ "count(*) as count from marks group by min, max order by min", nativeQuery = true)
	@Query("select " + MIN_INTERVAL +" as min,"
			+  MIN_INTERVAL + " + :interval - 1 as max, "
			+ "count(*) as count from MarkEntity group by min, max order by min")
	List<IntervalMarksCount> marksDistribution(int interval);
	
	

}
