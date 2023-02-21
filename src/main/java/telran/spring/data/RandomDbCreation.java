package telran.spring.data;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import telran.spring.data.model.Mark;
import telran.spring.data.model.Student;
import telran.spring.data.model.Subject;
import telran.spring.data.service.CollegeService;

@Component
public class RandomDbCreation {
	static Logger LOG = LoggerFactory.getLogger(RandomDbCreation.class);
	@Value("${app.marks.amount: 100}")
	int nMarks;
	@Value("${spring.jpa.hibernate.ddl-auto: update}")
	String ddlAutoProp;
	@Autowired
	CollegeService collegeService;
	String names[] = { "Abraham", "Sarah", "Itshak", "Rahel", "Asaf", "Yacob", "Rivka", "Yosef", "Benyanim", "Dan",
			"Ruben", "Moshe", "Aron", "Yehashua", "David", "Salomon", "Nefertity", "Naftaly", "Natan", "Asher" };
	String subjects[] = { "Java core", "Java Technologies", "Spring Data", "Spring Security", "Spring Cloud", "CSS",
			"HTML", "JS", "React", "Material-UI" };

	@PostConstruct
	void createDB() {
		if (ddlAutoProp.equals("create")) {
			addStudents();
			addSubjects();
			addMarks();
			LOG.info("created {} random marks in DB", nMarks);
		} else {
			LOG.warn("DB no created - assumed that it exists");
		}
	}
	private int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	private void addMarks() {
		IntStream.range(0, nMarks).forEach(i -> addOneMark());
		
		
		
	}
	private void addOneMark() {
		int stid = getRandomNumber(1, names.length);
		int suid = getRandomNumber(1, subjects.length);
		int mark = getRandomNumber(60, 100);
		collegeService.addMark(new Mark(stid, suid, mark));
	}

	private void addSubjects() {
		IntStream.range(0, subjects.length)
		.forEach(i -> {
			collegeService.addSubject(new Subject(i + 1, subjects[i]));
		});
		
	}

	private void addStudents() {
		IntStream.range(0, names.length)
		.forEach(i -> {
			collegeService.addStudent(new Student(i + 1, names[i]));
		});
		
	}
}
