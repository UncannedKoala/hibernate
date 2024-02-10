package hibernateStudy.dto;

import hibernateStudy.entity.Enrollment;
import lombok.ToString;

@ToString
public class EnrolledStudentsDTO {
	public final String studentName;
	public final Enrollment enrollment;
	
	public EnrolledStudentsDTO(String student, Enrollment enrollment) {
		this.studentName = student;
		this.enrollment = enrollment;
	}
}
