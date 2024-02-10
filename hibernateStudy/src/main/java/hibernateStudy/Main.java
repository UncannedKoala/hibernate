package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.dto.CountedEnrollmentForStudentDTO;
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
//			String jpqlDTO = "SELECT new hibernateStudy.dto.EnrolledStudentsDTO(s, e) FROM Student s, Enrollment e WHERE s.id = e.student.id";
//			TypedQuery<EnrolledStudentsDTO> q = em.createQuery(jpqlINNER, EnrolledStudentsDTO.class);

			//inner query
//			String jpqlINNER = "SELECT s FROM Student s WHERE (SELECT COUNT(e) FROM Enrollment e WHERE e.student.id = s.id) >= 2";		//selects all the student who have enrolled for more than 1 courses
//			TypedQuery<Student> q = em.createQuery(jpqlINNER, Student.class);

			//inner query in the projection
//			String jpqlINNERprojection = "SELECT (SELECT COUNT(e) FROM Enrollment e WHERE e.student = s) FROM Student s";				//select number of enrollments for each student
//			TypedQuery<Long> q = em.createQuery(jpqlINNERprojection, Long.class);

			//inner query in the projection using DTO
			String jpqlINNERprojection = "SELECT new CountedEnrollmentForStudentDTO(s, (SELECT COUNT(e) FROM Enrollment e WHERE e.student = s)) FROM Student s";				//select number of enrollments for each student
			TypedQuery<CountedEnrollmentForStudentDTO> q = em.createQuery(jpqlINNERprojection, CountedEnrollmentForStudentDTO.class);
			q.getResultStream().forEach(obj -> System.out.println(obj));
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
