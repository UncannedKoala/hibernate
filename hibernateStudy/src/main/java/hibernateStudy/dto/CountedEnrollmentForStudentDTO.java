package hibernateStudy.dto;

import lombok.ToString;

@ToString
public class CountedEnrollmentForStudentDTO {
	
	public final String studentName;
	public final Long courseCount;
	
	public CountedEnrollmentForStudentDTO(String student, Long count) {
		this.studentName = student;
		this.courseCount = count;
	}

}
