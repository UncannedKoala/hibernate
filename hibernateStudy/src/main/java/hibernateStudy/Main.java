package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Student;
import hibernateStudy.entity.keys.StudentKey;
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
			
//			TestEr city = new TestEr("TEMP", "tMp", "TEMP district", 21);
//			System.out.println("before persist()>> "+city);
//			em.persist(city);
//			System.out.println("after persist()>> "+city);
//			em.getTransaction().commit();
//			System.out.println("persisted >> "+city);
			

//			Product product1 = new Product("code", 15, "Green");
//			em.persist(product1);
			
			StudentKey key = new StudentKey(152, "third");
			Student student1 = new Student(key , "James Potter");
			em.persist(student1);
			
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
