import java.sql.*;
import java.util.*;

public class StudentCourseRegistration {

    static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root"; // your MySQL username
    static final String PASS = "root"; // your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            while (true) {
                System.out.println("\n--- Student Course Registration System ---");
                System.out.println("1. Register Student for Course");
                System.out.println("2. View All Registrations");
                System.out.println("3. Update Course Registration");
                System.out.println("4. Delete Course Registration");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        registerStudent(con, sc);
                        break;
                    case 2:
                        viewRegistrations(con);
                        break;
                    case 3:
                        updateRegistration(con, sc);
                        break;
                    case 4:
                        deleteRegistration(con, sc);
                        break;
                    case 5:
                        con.close();
                        System.out.println("Exiting... Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 1️⃣ Register new student
    public static void registerStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course Name: ");
        String course = sc.nextLine();
        System.out.print("Enter Semester: ");
        String sem = sc.nextLine();

        String query = "INSERT INTO course_registration VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setString(3, course);
        pst.setString(4, sem);
        pst.executeUpdate();
        System.out.println("✅ Registration successful!");
    }

    // 2️⃣ View all registrations
    public static void viewRegistrations(Connection con) throws SQLException {
        String query = "SELECT * FROM course_registration";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        System.out.println("\n--- All Student Registrations ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) +
                    ", Name: " + rs.getString(2) +
                    ", Course: " + rs.getString(3) +
                    ", Semester: " + rs.getString(4));
        }
    }

    // 3️⃣ Update course registration
    public static void updateRegistration(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter New Course Name: ");
        String newCourse = sc.nextLine();

        String query = "UPDATE course_registration SET course_name=? WHERE student_id=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, newCourse);
        pst.setInt(2, id);
        int rows = pst.executeUpdate();
        if (rows > 0)
            System.out.println("✅ Course updated successfully!");
        else
            System.out.println("⚠️ Student ID not found!");
    }

    // 4️⃣ Delete course registration
    public static void deleteRegistration(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM course_registration WHERE student_id=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        int rows = pst.executeUpdate();
        if (rows > 0)
            System.out.println("🗑️ Record deleted successfully!");
        else
            System.out.println("⚠️ Student ID not found!");
    }
    }
