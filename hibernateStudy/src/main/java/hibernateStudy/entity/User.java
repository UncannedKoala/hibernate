package hibernateStudy.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private long id;
	
	private String name;

	/*
	 * mappedby value is the name of the variable representing the Set<User> in the
	 * Group class
	 */
	@ManyToMany(mappedBy = "users")
	private List<Group> groups;
	
	public User(String name) {
		super();
		this.name = name;
	}
	
}
