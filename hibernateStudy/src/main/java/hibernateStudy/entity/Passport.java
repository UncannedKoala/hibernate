package hibernateStudy.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Passport{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String passportNumber;

	public Passport(String passportNumber) {
		super();
		this.passportNumber = passportNumber;
	}

	public Long getId() {
		return id;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Override
	public String toString() {
		return "Passport [id = " + id + ", passportNumber = " + passportNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, passportNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passport other = (Passport) obj;
		return Objects.equals(id, other.id) && Objects.equals(passportNumber, other.passportNumber);
	}
	
}