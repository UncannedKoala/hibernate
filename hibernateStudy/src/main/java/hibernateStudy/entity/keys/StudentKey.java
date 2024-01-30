package hibernateStudy.entity.keys;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentKey implements Serializable{
	
	private long rollNumber;
	
	private String className;

	public StudentKey(long rollNumber, String className) {
		super();
		this.rollNumber = rollNumber;
		this.className = className;
	}

	public long getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
