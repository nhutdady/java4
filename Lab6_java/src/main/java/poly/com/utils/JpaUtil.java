package poly.com.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory factory;

    static {
        try {
        	factory = Persistence.createEntityManagerFactory("ASM_JAVA4");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khởi tạo EntityManagerFactory", e);
        }
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
