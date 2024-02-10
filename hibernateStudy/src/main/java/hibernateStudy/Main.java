package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

/**
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
			
//			String jpql = "SELECT s, e FROM Student s JOIN s.enrollments e";					//(all the students having enrollments)OR 
//			String jpql = "SELECT s, e FROM Student s, Enrollment e WHERE s = e.student";		//(all the students having enrollments)OR
			String jpql = "SELECT s, e FROM Student s, Enrollment e WHERE s.id = e.student.id";	//(all the students having enrollments)OR
			String jpql1 = "SELECT s, e FROM Student s LEFT JOIN s.enrollments e";				//all students, even the once without enrollments
			String jpql2 = "SELECT s, e FROM Student s OUTER JOIN s.enrollments e";				//all students, even the once without enrollments
			String jpql3 = "SELECT s, e FROM Student s RIGHT JOIN s.enrollments e";				//only students with the enrollments
			
			TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
			q.getResultStream().forEach(obj -> System.out.println(obj[0]+" "+obj[1]));
			System.out.println(q.getResultList().size());
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
