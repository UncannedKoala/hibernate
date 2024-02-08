package hibernateStudy.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private BigDecimal price;

	public Product(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}
	
}
