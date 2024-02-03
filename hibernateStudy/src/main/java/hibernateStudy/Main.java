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
			
			new Comment(post1, "POST1 :: nice post");
			new Comment(post1, "POST1 :: wooho, looking clean");
			new Comment(post2, "POST2 :: Damn, you look handsome");
			new Comment(post2, "POST2 :: oi, you look like a parrot mate");
			new Comment(post2, "POST2 :: why the pig face??");
			
			em.persist(post1);
			em.persist(post2);
			
			System.out.println(em.find(Post.class, 1));
			System.out.println(em.find(Post.class, 2).getComments().get(0).completeToString());
			
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
