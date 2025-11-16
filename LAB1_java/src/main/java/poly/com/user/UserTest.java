package poly.com.user;

import java.util.List;

public class UserTest {

    public static void main(String[] args) {
        // Khởi tạo UserManager, tự động tạo EntityManager
        UserManager manager = new UserManager();

        try {
            // -----------------------------------------------------
            // BÀI 2: TEST CRUD CƠ BẢN
            // -----------------------------------------------------
            System.out.println("--- 1. BÀI 2: Test CRUD Cơ bản ---");

            // 1. CREATE (Thêm User mới)
            // Đảm bảo ID này chưa tồn tại trong CSDL của bạn
            User newUser = new User("PS99999", "123456", "Phan Thi Thuy", "thuy.p@fpt.edu.vn", false);
            manager.create(newUser); 
            
            // 2. UPDATE (Cập nhật User)
            User userToUpdate = new User("PS99999", "999999", "Phan Thuy Updated", "thuycn@poly.edu.vn", true);
            manager.update(userToUpdate);
            
            // 3. DELETE (Xóa User theo ID)
            manager.deleteById("PS10000"); // Thử xóa một ID không tồn tại hoặc ID nào đó trong DB của bạn

            // 4. FIND ALL (Xuất tất cả User)
            List<User> allUsers = manager.findAll();
            System.out.println("\n✅ Danh sách TẤT CẢ Users hiện tại:");
            allUsers.forEach(System.out::println);
            
            // -----------------------------------------------------
            // BÀI 3: TRUY VẤN CÓ THAM SỐ
            // -----------------------------------------------------
            List<User> filteredUsers = manager.findByEmailAndNonAdminRole();
            System.out.println("\n✅ Users có email '@fpt.edu.vn' và KHÔNG phải Admin:");
            if (!filteredUsers.isEmpty()) {
                 filteredUsers.forEach(u -> System.out.println("  - Họ tên: " + u.getFullname() + " | Email: " + u.getEmail()));
            } else {
                 System.out.println("  > Không tìm thấy User nào thỏa mãn điều kiện.");
            }
            // -----------------------------------------------------
            // BÀI 4: TRUY VẤN PHÂN TRANG
            // -----------------------------------------------------
            // Yêu cầu: Trang thứ 3 (pageNo = 2), kích thước trang = 5
            List<User> pageUsers = manager.findUsersByPage(2, 5);            
            System.out.println("\n✅ Users được truy vấn tại TRANG 3 (tối đa 5 User):");
            if (!pageUsers.isEmpty()) {
                 pageUsers.forEach(System.out::println);
            } else {
                 System.out.println("  > Trang này không có dữ liệu (CSDL của bạn có ít hơn 15 bản ghi).");
            }
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi trong quá trình thực thi: " + e.getMessage());
        } finally {
            // BƯỚC CUỐI CÙNG VÀ QUAN TRỌNG NHẤT: ĐÓNG TÀI NGUYÊN
            // 1. Đóng EntityManager của đối tượng hiện tại
            manager.close();           
            // 2. Đóng EntityManagerFactory của toàn bộ ứng dụng (KHẮC PHỤC LỖI)
            UserManager.shutdown(); 
        }
    }
}