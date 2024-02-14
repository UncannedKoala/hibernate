package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Customer;
import hibernateStudy.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 * In this approach,
 * <li>We put the query in the DataBase as a View and give it a name
 * <li>We create a DTO(DistinctStudent) to represent the expected response of a
 * complex query, Mark it as an @Entity and @Table(name = "<view name from
 * database>").
 * <li>Create a simple JPQL query that select all from the <Entity> @code(SELECT
 * s FROM DistinctStudent s)
 */
public class Main {
	public static void main(String[] args) {
		String name = "pu-name";

		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "none"); // create, none, update
		props.put("hibernate.format_sql", "true");
//		props.put("hibernate.use_sql_comments", "true");
//		<property name="hibernate.show_sql" value="true"/>
//		<property name="hibernate.format_sql" value="true"/>
//		<property name="hibernate.use_sql_comments" value="true"/>

		try (EntityManagerFactory emf = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(name), props);
				EntityManager em = emf.createEntityManager();) {

			em.getTransaction().begin();

//			Long index = 1l;
//			while(index++<10)
//				em.persist(new Customer("John"));
//			em.getTransaction().commit();
			
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<String> cQuery = builder.createQuery(String.class);	//typed to the <Type> of the expected response

			/* the following line describe the <Entity> to be dealt with */
			Root<Customer> customerRoot = cQuery.from(Customer.class);	//represents "FROM Customer c" 	part of the Query "SELECT c FROM Customer c"

			/* following query defines the operation to be performed on the <Entity> */
//			cQuery.select(customerRoot);								//represents "SELECT c FROM Customer c"
			cQuery.select(customerRoot.get("name"));					//we can use the .get() function on rootCriteriaQuery to get specific attributes if the Entity only, i.e. here we Get only 'name' attribute instead of the entire Entity

			TypedQuery<String> query = em.createQuery(cQuery);
			
			query.getResultList().stream().forEach(System.out::println);
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
