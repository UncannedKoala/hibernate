package hibernateStudy.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private long id;
	
	private String content;
	
	/* 
	 * @JoinColumn to have custom column_name
	 */
	@JoinColumn(name = "associated_post_id")
	@ManyToOne(cascade = {CascadeType.ALL, CascadeType.REMOVE})
	private Post post;

	public Comment(Post post, String content) {
		super();
		this.post = post;
		this.content = content;
	}
	
}
