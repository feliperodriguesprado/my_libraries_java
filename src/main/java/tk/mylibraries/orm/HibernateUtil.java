package tk.mylibraries.orm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = buildSessionFactory();
	
	private static EntityManagerFactory buildSessionFactory() {
		try {
			return Persistence.createEntityManagerFactory("POSTGRESQL"); // Create the Factory
		} catch (Throwable e) {
			// Make sure you log the exception , as it might be swallowed
			System.err.println(" Error creating EntityManagerFactory ." + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	// metodo que utilizaremos para obter o EntityManager
		public static EntityManager getEntityManager() {
			return ENTITY_MANAGER_FACTORY.createEntityManager();
		}
	
}
