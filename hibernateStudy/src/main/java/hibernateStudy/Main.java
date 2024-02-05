package hibernateStudy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Group;
import hibernateStudy.entity.User;
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
			
			User usr1 = new User("Ron");
			User usr2 = new User("Fred");
			User usr3 = new User("Percy");
			User usr4 = new User("Goyl");
			User usr5 = new User("Neviel");
			User usr6 = new User("Anjlena");
			User usr7 = new User("Luna");
			
			Group group1 = new Group("Gryphindor", Set.of(usr1, usr2, usr3, usr4));
			Group group2 = new Group("Slytherin", Set.of(usr5, usr6, usr7));
			
			em.persist(group1);
			em.persist(group2);
			
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
