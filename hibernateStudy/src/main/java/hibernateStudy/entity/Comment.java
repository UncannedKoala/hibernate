package hibernateStudy.entity;

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
import lombok.ToString;


/**
 * Has two {@code toString} implementations {@code toString()} excludes the {@code post} field, {@code completeToString()} including all the values of the {@code post} as well
 */
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
	@ManyToOne
	@ToString.Exclude
	private Post post;

	public Comment(Post post, String content) {
		super();
		post.addComment(this);
		this.post = post;
		this.content = content;
	}

	public String completeToString() {
		return "Comment [id = " + id + ", content = " + content + ", post title = " + post.getTitle() + "post content = " + post.getContent() + "]";
	}
	
	

}
