package hibernateStudy.entity;

import java.util.Objects;

import hibernateStudy.entity.keys.StudentID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Student {
	
	@EmbeddedId
	private StudentID id;
	
	private String name;

	public StudentID getId() {
		return id;
	}

	public void setId(StudentID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(StudentID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(name, other.name);
	}
	
	
}
