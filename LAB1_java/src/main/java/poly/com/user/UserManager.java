package poly.com.user;

import jakarta.persistence.*;
import java.util.List;

public class UserManager {
    
    // 1. FACTORY PHẢI LÀ STATIC VÀ CHỈ ĐƯỢC TẠO MỘT LẦN KHI ỨNG DỤNG KHỞI ĐỘNG
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    private EntityManager em;    
    // Khởi tạo EntityManager
    public UserManager() {
        // Tạo EntityManager từ static factory
        em = factory.createEntityManager();
    }   
    // 2. Đóng EntityManager (CHỈ ĐÓNG EM, KHÔNG ĐÓNG FACTORY)
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
            System.out.println(" EntityManager đã đóng.");
        }
    }   
    // 3. HÀM STATIC MỚI: ĐÓNG TOÀN BỘ HỆ THỐNG FACTORY KHI KẾT THÚC ỨNG DỤNG
    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
            System.out.println(" EntityManagerFactory đã đóng. Hệ thống tắt.");
        }
    }
    // ============================= BÀI 2: CRUD CƠ BẢN =============================
    // 1. Thêm mới (Create)
    public void create(User user) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin(); 
            em.persist(user); // Thêm thực thể mới vào CSDL
            trans.commit(); 
            System.out.println(" Thêm User thành công: " + user.getId());
        } catch (Exception e) {
            trans.rollback(); // Hủy bỏ nếu có lỗi
            System.err.println(" Lỗi khi thêm User: " + e.getMessage());
        }
    }
    // 2. Cập nhật (Update)
    public void update(User user) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user); // Cập nhật các thay đổi
            trans.commit();
            System.out.println(" Cập nhật User thành công: " + user.getId());
        } catch (Exception e) {
            trans.rollback();
            System.err.println(" Lỗi khi cập nhật User: " + e.getMessage());
        }
    }
    // 3. Xóa theo ID (Delete)
    public void deleteById(String id) {
        User user = em.find(User.class, id); // Tìm kiếm thực thể
        if (user != null) {
            EntityTransaction trans = em.getTransaction();
            try {
                trans.begin();
                em.remove(user); // Xóa thực thể
                trans.commit();
                System.out.println(" Xóa User thành công: " + id);
            } catch (Exception e) {
                trans.rollback();
                System.err.println(" Lỗi khi xóa User: " + e.getMessage());
            }
        } else {
            System.out.println("⚠ Không tìm thấy User với ID: " + id);
        }
    }    
    // 4. Tìm tất cả (Find All)
    public List<User> findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }
    // ============================= BÀI 3: TRUY VẤN CÓ THAM SỐ =============================
    public List<User> findByEmailAndNonAdminRole() {
        System.out.println("\n--- BÀI 3: Truy vấn có Tham số ---");
        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);       
        query.setParameter("search", "%@fpt.edu.vn"); 
        query.setParameter("role", false); 
        return query.getResultList();
    }
    // ============================= BÀI 4: TRUY VẤN PHÂN TRANG =============================
    public List<User> findUsersByPage(int pageNo, int pageSize) {
        System.out.println("\n--- BÀI 4: Truy vấn Phân trang ---");
        String jpql = "SELECT o FROM User o ORDER BY o.id"; 
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        int firstResult = pageNo * pageSize;
        query.setFirstResult(firstResult); // Vị trí bắt đầu
        query.setMaxResults(pageSize);     // Số phần tử tối đa       
        System.out.printf("> Yêu cầu: Trang %d (pageNo=%d), bắt đầu từ vị trí %d, tối đa %d User.\n", pageNo + 1, pageNo, firstResult, pageSize);
        return query.getResultList();
    }
}