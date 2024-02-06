package hibernateStudy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <li>{@code id} attribute in {@code Product} is marked {@code protected} and not {@code private}to allow inheritance of the {@code id} by the sub-classes
 * <li>{@code @Inheritance(strategy = InheritanceType.JOINED)} creates a single table (named 'product') with all the attributes of the classes in the hierarchy and an extra {@code DTYPE} column that contains the class name of the object type(not the reference due to the fact that reference is in application and has nothing to do with ORM)
 * <li>{@code InheritanceType.JOINED} will use a JOINS each time you fetch, thus less performant
 */

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

	/**
	 * Each sub-class table's {@code id} column will have a {@code Foreign-Key} constraint to root-class
	 * {@code id} column
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

}
