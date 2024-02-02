package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Passport;
import hibernateStudy.entity.Person;
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
			
			Person person = new Person("person name", new Passport("ASfa46AsDa5"));
			em.persist(person);

			/*
			 * To avoid SQL Injection attacks, avoid the use of direct concatenation of the
			 * values to the SQL queries and avoid Statement(use PreparedStatement instead).
			 */
			Person queryResult = em.createQuery("SELECT p FROM Person p WHERE p.passport.passportNumber = :number", Person.class).setParameter("number", "ASfa46AsDa5").getSingleResult();
			System.out.println(queryResult);
			
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
