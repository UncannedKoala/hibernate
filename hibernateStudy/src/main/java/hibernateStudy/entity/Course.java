package hibernateStudy.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private long id;
	
	private String title;
	
	@OneToMany(mappedBy = "course")
	private List<Enrollment> enrollments;
	
	public String toString() {
		return title;
	}
}
