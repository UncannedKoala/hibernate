package hibernateStudy.entity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
public class Post{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	private String title;
	
	private String content;
	
	@OneToMany(mappedBy = "post", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Comment> comments = new LinkedList<>();

	public Post(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	public void addComment(Comment... comments) {
		this.comments.addAll(Arrays.asList(comments));
	}
}