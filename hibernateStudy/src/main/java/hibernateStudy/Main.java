package hibernateStudy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Product;
import hibernateStudy.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

/**
 * <li><strong>DO NOT FETCH DATA AND THEN FILTER IT, ALWAYS FILTER WHILE
 * FETCHING ITSELF (in the query) </strong>
 * <li>SELECT p FROM Product p ===> JPQL Fetch all the attributes of the Product
 * entity from the current context.
 * <li>SELECT * FROM Product ===> SQL Fetch all columns from the table product.
 * <li>The entity name is case-sensitive
 * <li>Using JPQL we can, UPDATE/DELETE BUT NOT INSERT, for insert operation we
 * should use the persist() from the EntityManager.
 * <li>In JPQL, named queries we can not have the space between the ":" and
 * param_ref_name symbol.
 * <li>{@code em.createQuery("QUERY");} is not generally used because
 * {@code em.createQuery("QUERY", Class.class);} returns
 * {@link jakarta.persistence.TypedQuery} which can be mapped to the object
 */
public class Main {
	public static void main(String[] args) {
		String name = "pu-name";

		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update
		props.put("hibernate.format_sql", "true");
//		props.put("hibernate.use_sql_comments", "true");
//		<property name="hibernate.show_sql" value="true"/>
//		<property name="hibernate.format_sql" value="true"/>
//		<property name="hibernate.use_sql_comments" value="true"/>

		try (EntityManagerFactory emf = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(name), props);
				EntityManager em = emf.createEntityManager();) {

			em.getTransaction().begin();

			List<Product> list = new LinkedList<>();
			list.add(new Product("Ice cream", new BigDecimal("43.3421")));
			list.add(new Product("Cake", new BigDecimal("33.5421")));
			list.add(new Product("Brush", new BigDecimal("23.6421")));
			list.add(new Product("Coke", new BigDecimal("13.7421")));

			for (Product temp : list) {
				em.persist(temp);
			}

			String jpql = "";
			jpql = "SELECT p FROM Product p";
			jpql = "SELECT p FROM Product p WHERE p.price > 25"; // where clause remains same
			jpql = "SELECT p FROM Product p WHERE p.name LIKE :pName and p.price >= :price";

			TypedQuery<Product> q = em.createQuery(jpql, Product.class); //
			q.setParameter("pName", "%C%"); // containing 'C' case-insensitive
//			q.setParameter("pName", "C%"); // starting with 'C' case-insensitive
			q.setParameter("price", 20);
			q.getResultList().forEach(System.out::println);
			
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
