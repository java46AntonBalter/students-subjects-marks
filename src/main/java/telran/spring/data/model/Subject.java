package telran.spring.data.model;

import java.util.Objects;

public class Subject {
public long id;
public String subject;
public Subject(long id, String subject) {
	this.id = id;
	this.subject = subject;
}
public Subject() {
}
@Override
public int hashCode() {
	return Objects.hash(id, subject);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Subject other = (Subject) obj;
	return id == other.id && Objects.equals(subject, other.subject);
}


}
