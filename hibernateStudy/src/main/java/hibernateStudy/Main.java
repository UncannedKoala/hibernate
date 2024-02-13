package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.DistinctStudent;
import hibernateStudy.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

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
//			String nativeQuery = "SELECT * FROM Student";
//			
//			Query qry = em.createNativeQuery(nativeQuery, Student.class);
//			qry.getResultList().forEach(res->System.out.println(res));

			String jpqlQuery = "SELECT s FROM DistinctStudent s";

			Query qry = em.createQuery(jpqlQuery, DistinctStudent.class);
			qry.getResultList().forEach(res -> System.out.println(res));

			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
