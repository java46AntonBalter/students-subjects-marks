package telran.spring.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.data.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{
    @Query("select students from StudentEntity students where id in"
    		+ " (select student.id from MarkEntity"
    		+ " group by student.id having avg(mark) * count(mark) < :markCountLess)")
	List<StudentEntity> worstStudents(double markCountLess);

}
