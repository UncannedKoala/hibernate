package hibernateStudy.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
/**
 * <li> Name of the query should be UNIQUE on the application level
 * <li> Can interact with multiple entities
 * <li> SQL->...LIKE %name<-  ==  JPQL->...LIKE '%' || :name<-
 * <li> SQL->...LIKE name%<-  ==  JPQL->...LIKE :name || '%'<-
 * <li> SQL->...LIKE %name%<-  ==  JPQL->...LIKE '%' || :name || '%'<-
 * */
@NamedQueries({
		@NamedQuery(name = "getAllStudents", query = "SELECT s FROM Student s"),
		@NamedQuery(name = "findStudentsByName", query = "SELECT s FROM Student s WHERE s.name LIKE '%' || :name || '%'") })
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@OneToMany(mappedBy = "student")
	private List<Enrollment> enrollments;

	public String toString() {
		return this.name;
	}
}
