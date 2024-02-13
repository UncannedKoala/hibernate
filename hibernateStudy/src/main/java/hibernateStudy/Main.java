package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mysql.cj.Query;

import hibernateStudy.entity.DistinctStudent;
import hibernateStudy.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.StoredProcedureQuery;

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

//			String jpqlQuery = "SELECT s FROM DistinctStudent s";
//
//			Query qry = em.createQuery(jpqlQuery, DistinctStudent.class);
//			qry.getResultList().forEach(res -> System.out.println(res));
			
			/* QUERY TO CREATE STORED PROCEDURE ON D.B.
			 * Delimiter // CREATE PROCEDURE GetStudents(IN id BIGINT) BEGIN SELECT * FROM
			 * student s WHERE s.id = id; END// Delimiter ;
			 */
//			The ParameterMode support is Database dependent
			StoredProcedureQuery procedureQuery = em.createStoredProcedureQuery("GetStudents", DistinctStudent.class)
			.registerStoredProcedureParameter("id", Long.class, ParameterMode.IN)
			.setParameter("id", 2);
			
			procedureQuery.getResultList().stream().forEach(res->System.out.println(res));

			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
