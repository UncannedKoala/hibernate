package hibernateStudy.entity.keys;

import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * the compound Key class must have the hashCode() and equals implementations to
 * compare the IDs of 2 objects
 */
@Embeddable
public class StudentID extends CustomizedObjectID {

	private long rollNumber;

	private String className;

	public StudentID(long rollNumber, String className) {
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

	@Override
	public int hashCode() {
		return Objects.hash(className, rollNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentID other = (StudentID) obj;
		return Objects.equals(className, other.className) && rollNumber == other.rollNumber;
	}

}
