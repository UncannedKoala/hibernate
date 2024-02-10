package hibernateStudy.dto;

import hibernateStudy.entity.Student;
import lombok.ToString;

@ToString
public class CountedEnrollmentForStudentDTO {
	
	public final Student student;
	public final Long courseCount;
	
	public CountedEnrollmentForStudentDTO(Student student, Long count) {
		this.student = student;
		this.courseCount = count;
	}

}
