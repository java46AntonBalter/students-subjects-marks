package telran.spring.data.entities;
import jakarta.persistence.*;
@Entity
@Table(name="marks")
public class MarkEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	long id;
	@ManyToOne
	@JoinColumn(name = "stid")
	StudentEntity student;
	@ManyToOne
	@JoinColumn(name = "suid")
	SubjectEntity subject;
	int mark;
	public MarkEntity(StudentEntity student, SubjectEntity subject, int mark) {
		this.student = student;
		this.subject = subject;
		this.mark = mark;
	}
	public MarkEntity() {
	}
	public long getId() {
		return id;
	}
	public StudentEntity getStudent() {
		return student;
	}
	public SubjectEntity getSubject() {
		return subject;
	}
	public int getMark() {
		return mark;
	}
	
	
	
	
}
