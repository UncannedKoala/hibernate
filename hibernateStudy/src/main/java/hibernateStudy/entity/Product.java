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
 * <strong>NOTE: {@code InheritanceType.TABLE_PER_CLASS} is also avoided because when polymorphic queries(one where we operate on parent Entity) are run {@code UNION} is used, and {@code UNION} is not supported by {@code JPQL}.</strong>
 * <li>{@code id} attribute in {@code Product} is marked {@code protected} and not {@code private}to allow inheritance of the {@code id} by the sub-classes
 * <li>{@code @Inheritance(strategy = InheritanceType.JOINED)} creates a single table (named 'product') with all the attributes of the classes in the hierarchy and an extra {@code DTYPE} column that contains the class name of the object type(not the reference due to the fact that reference is in application and has nothing to do with ORM)
 * <li>{@code InheritanceType.JOINED} will use a JOINS each time you fetch, thus less performant
 * <li>{@code @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)} will create a table per <Entity> class, and this table will have columns for all the attributes (it's own as well as the inherited once), IS USUALLY AVOIDED AS IT DESTROYS THE INHERITANCE AND POLYMORPHISM (by not capturing Entity to Entity relationships).<br>
 */

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {

	/**
	 * Each sub-class table's {@code id} column will have a {@code Foreign-Key} constraint to root-class
	 * {@code id} column
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected long id;

}
