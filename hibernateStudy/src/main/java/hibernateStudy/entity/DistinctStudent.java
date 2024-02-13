package hibernateStudy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * <li>A DTO representing the response of a VIEW query in the database
 * <li>Despite being an @Entity, it does not represent the database table. This is because, "Entity is anything that we want to be able to query for"
 */
@Entity
@Data
@Table(name = "view_unique_students")		//here instead of table name use the name of the view(saved in the Database prior to use)
public class DistinctStudent {
	@Id
	private long id;

	private String name;
}
