package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Product;
import hibernateStudy.entity.Student;
import hibernateStudy.entity.keys.ProductID;
import hibernateStudy.entity.keys.StudentID;
import hibernateStudy.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
	public static void main(String[] args) {
		String name = "pu-name";
		
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create");		//create, none, update
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try {
		
			emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(name), props);
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			/*
			 * TestEr city = new TestEr("TEMP", "tMp", "TEMP district", 21);
			 * System.out.println("before persist()>> "+city); em.persist(city);
			 * System.out.println("after persist()>> "+city); em.getTransaction().commit();
			 * System.out.println("persisted >> "+city);
			 */
			

			
//			  COMPLEX ID GENERATION APPROACHES AND SINCE THE IDS ARE PART OF ENTITY THEY LIVE ON THE SAME TABLE
			 
			/*
			 * COMPLEX ID approach 1 using @Id (in the entity class on id instance
			 * variables) and @IdClass(value = ProductID.class) on the entity class
			 */
			Product product1 = new Product("code", 15, "Green");
			em.persist(product1);
			
			/* The following line will not work as the passed 'id' type does not match the <ProductID>
			 * System.out.println(em.find(Product.class, 15)); */
			System.out.println(em.find(Product.class, new ProductID("code", 15)));

			/*
			 * COMPLEX ID approach 1 using @EmbeddedId (in the entity class on <StudentID>
			 * variable) and @Embeddable (on the StudentID class)
			 */
			StudentID key = new StudentID(152, "third");
			Student student1 = new Student(key , "James Potter");
			em.persist(student1);
			
			/* The following line will not work because 'id' passed is not of type <StudentID>
			 * System.out.println(em.find(Student.class, 152)); */
			System.out.println(em.find(Student.class, new StudentID(152, "third")));
			
			
			em.getTransaction().commit();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(em!=null)
				em.close();
			if(emf!=null)
				emf.close();
		}
	}
}
