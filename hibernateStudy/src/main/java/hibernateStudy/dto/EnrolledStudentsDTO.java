package hibernateStudy.dto;

import hibernateStudy.entity.Enrollment;
import hibernateStudy.entity.Student;
import lombok.ToString;

@ToString
public class EnrolledStudentsDTO {
	public final Student student;
	public final Enrollment enrollment;
	
	public EnrolledStudentsDTO(Student student, Enrollment enrollment) {
		this.student = student;
		this.enrollment = enrollment;
	}
}
