package hibernateStudy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import hibernateStudy.entity.Book;
import hibernateStudy.entity.ElectronicDevice;
import hibernateStudy.entity.Product;
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
			
			Book a = new Book("author A");
			Book b = new Book("author B");
			Product c = new Book("author C");
			Product e = new ElectronicDevice(17);
			ElectronicDevice f = new ElectronicDevice(18);
			ElectronicDevice g = new ElectronicDevice(19);
			ElectronicDevice h = new ElectronicDevice(20);
			
			em.persist(a);
			em.persist(b);
			em.persist(c);

			em.persist(e);
			em.persist(f);
			em.persist(g);
			em.persist(h);
			
			/*
			 * we can straight away use the class type, and not required to say what 'DTYPE'
			 * value we need, this is because the 'DTYPE' is something ORM uses not us 
			 */
			System.out.println(em.find(Book.class, 3));
			em.createQuery("SELECT p FROM Product p", Product.class).getResultList().forEach(res -> System.out.println(res));
			
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
