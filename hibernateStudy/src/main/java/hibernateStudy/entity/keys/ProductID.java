package hibernateStudy.entity.keys;

import java.util.Objects;

import jakarta.persistence.Id;

/**
 * the compound id class must have the hashCode() and equals implementations to
 * compare the IDs of 2 objects
 */
public class ProductID extends CustomizedObjectID {

	@Id
	private String code;
	
	@Id
	private long number;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ProductKey [code=" + code + ", number=" + number + "]";
	}

	public ProductID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductID(String code, long number) {
		super();
		this.code = code;
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductID other = (ProductID) obj;
		return Objects.equals(code, other.code) && number == other.number;
	}
	
}
