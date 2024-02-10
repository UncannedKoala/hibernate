package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Student;
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

			//group by using DTO
//			String jpqlINNERprojection = "SELECT new CountedEnrollmentForStudentDTO(s.name, COUNT(s)) "
//					+ "FROM Student s "
//					+ "GROUP BY s.name "
//					+ "HAVING s.name LIKE '%m%'"
//					+ "ORDER BY s.name DESC"
//					+ " LIMIT 2";				//select number of enrollments for each student
//			TypedQuery<CountedEnrollmentForStudentDTO> q = em.createQuery(jpqlINNERprojection, CountedEnrollmentForStudentDTO.class);
	
			//named query
			TypedQuery<Student> q = em.createNamedQuery("findStudentsByName", Student.class);
			q.setParameter("name", "t");
			q.getResultStream().forEach(obj -> System.out.println(obj));
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
