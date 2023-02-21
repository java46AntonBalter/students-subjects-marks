package telran.spring.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import telran.spring.data.model.QueryData;
import telran.spring.data.model.QueryType;
import telran.spring.data.proj.*;
import telran.spring.data.service.CollegeService;

import java.util.*;

@RestController
@RequestMapping("college")
public class CollegeController {
	@Autowired
	CollegeService collegeService;

	@GetMapping("marks")
	List<MarkProj> getMarksByNameSubject(@RequestParam(name = "subject") String subject,
			@RequestParam(name = "name") String name) {
		return collegeService.getMarksByNameSubject(name, subject);
	}

	@GetMapping("marks/subjects")
	List<StudentSubjectMark> getMarksByName(@RequestParam(name = "name") String name) {
		return collegeService.getMarksByName(name);
	}

	@GetMapping("marks/average")
	List<StudentAvgMark> studentsAvgMarks() {
		return collegeService.getStudentsAvgMark();
	}

	@GetMapping("students/best")
	List<StudentName> bestStudents(@RequestParam(name = "n_students", defaultValue = "-1") int nStudents,
			@RequestParam(name = "subject", defaultValue = "") String subject) {
		List<StudentName> res = null;
		if (nStudents > 0) {
			if (subject.isEmpty()) {
				res = collegeService.getTopBestStudents(nStudents);
			} else {
				res = collegeService.getTopBestStudentsSubject(nStudents, subject);
			}
		} else {
			res = collegeService.getBestStudents();
		}
		return res;

	}

	@GetMapping("students/worst")
	List<StudentSubjectMark> worstStudents(@RequestParam(name = "n_students", required = true) int nStudents) {
		return collegeService.getMarksOfWorstStudents(nStudents);
	}

	@GetMapping("marks/distribution")
	List<IntervalMarksCount> marksDistribution(@RequestParam(defaultValue = "10", name = "interval") int interval) {
		return collegeService.marksDistribution(interval);
	}

	@PostMapping("query")
	List<String> getQuery(@RequestBody QueryData queryData) {
		return queryData.type == QueryType.JPQL ? collegeService.getJpqlQuery(queryData.query)
				: collegeService.getSqlQuery(queryData.query);
	}

	@DeleteMapping("students")
	List<String> removeStudents(@RequestParam("score") int markCountLess) {
		return collegeService.removeStudents(markCountLess);
	}

	@DeleteMapping("subjects")
	List<String> removeLeastPopularSubjects(@RequestParam("threshold") int marksThreshold) {
		return collegeService.removeLeastPopularSubjects(marksThreshold);
	}
}
