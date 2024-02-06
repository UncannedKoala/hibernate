package hibernateStudy.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicDevice extends Product{
	
	private int voltage;
	
	private String brand;
}
