package hibernateStudy.entity;

import java.util.Objects;

import hibernateStudy.entity.keys.ProductKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;


/**
 * Object illustrating approach1 to have COMPOUND/COMPLEX primary key
 */
@Entity
@IdClass(value = ProductKey.class)
public class Product {
	
	@Id
	private String code;
	
	@Id
	private long number;
	
	private String color;

	@Override
	public int hashCode() {
		return Objects.hash(code, color, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(code, other.code) && Objects.equals(color, other.color) && number == other.number;
	}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Product(String code, long number, String color) {
		super();
		this.code = code;
		this.number = number;
		this.color = color;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", number=" + number + ", color=" + color + "]";
	}
}
