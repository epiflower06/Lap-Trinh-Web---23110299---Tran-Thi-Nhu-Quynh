package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@PersistenceContext
public class JPAConfig {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("dataSource");
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	public static void shutdown() {
        if (factory.isOpen()) factory.close();
    }
}
