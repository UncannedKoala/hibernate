package hibernateStudy.entity.keys;

import java.io.Serializable;

import jakarta.persistence.Id;

public class ProductKey implements Serializable {

	@Id
	private String code;
	
	@Id
	private long number;
}
