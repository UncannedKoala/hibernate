package hibernateStudy.entity;

import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;

import hibernateStudy.entity.generator.UUIDGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TestEr {
	@Id
	@GenericGenerator(name = "UUIDGenerator", type = UUIDGenerator.class )
	@GeneratedValue(generator = "UUIDGenerator")
	@Column(length = 700)
	private String id;
	
	private String Name; 
	
	private String CountryCode;
	
	private String District;
	
	private int Population;

	public TestEr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestEr(String name, String countryCode, String district, int population) {
		super();
		Name = name;
		CountryCode = countryCode;
		District = district;
		Population = population;
	}

	public String	 getId() {
		return id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public int getPopulation() {
		return Population;
	}

	public void setPopulation(int population) {
		Population = population;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", Name=" + Name + ", CountryCode=" + CountryCode + ", District=" + District
				+ ", Population=" + Population + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(CountryCode, District, Name, Population);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestEr other = (TestEr) obj;
		return Objects.equals(CountryCode, other.CountryCode) && Objects.equals(District, other.District)
				&& Objects.equals(Name, other.Name) && Population == other.Population;
	}

}
