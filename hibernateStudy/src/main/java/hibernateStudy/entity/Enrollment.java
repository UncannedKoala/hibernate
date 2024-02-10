package hibernateStudy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * binding contract between Courses and associated Students
 */
@Data
@Entity
@NoArgsConstructor
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private long id;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private Course course;
}
