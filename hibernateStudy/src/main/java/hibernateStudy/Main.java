package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Comment;
import hibernateStudy.entity.Post;
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
			
			Post post1 = new Post("Post title1","post1 content");			
			Post post2 = new Post("Post title2","post2 content");
			
			Comment comment1 = new Comment(post1, "POST1 :: nice post");
			Comment comment2 = new Comment(post1, "POST1 :: wooho, looking clean");
			Comment comment3 = new Comment(post2, "POST2 :: Damn, you look handsome");
			Comment comment4 = new Comment(post2, "POST2 :: oi, you look like a parrot mate");
			Comment comment5 = new Comment(post2, "POST2 :: why the pig face??");
			
			em.persist(comment1);
			em.persist(comment2);
			em.persist(comment3);
			em.persist(comment4);
			em.persist(comment5);
			
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
