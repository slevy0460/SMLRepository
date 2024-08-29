package stevelevy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUpdater {

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DatabaseUpdater(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    // Method to insert or update a record
    public void insertOrUpdateEmployee(int empId, String firstName, String lastName) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            if (recordExists(conn, empId)) {
                updateEmployee(conn, empId, firstName, lastName);
            } else {
                insertEmployee(conn, empId, firstName, lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if a record with the given empId exists
    private boolean recordExists(Connection conn, int empId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Employees WHERE EmployeeID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, empId);
            return pstmt.executeQuery().next();
        }
    }

    // Insert a new employee record
    private void insertEmployee(Connection conn, int empId, String firstName, String lastName) throws SQLException {
        String insertQuery = "INSERT INTO Employees (EmployeeID, FirstName, LastName) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setInt(1, empId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.executeUpdate();
        }
    }

    // Update an existing employee record
    private void updateEmployee(Connection conn, int empId, String firstName, String lastName) throws SQLException {
        String updateQuery = "UPDATE Employees SET FirstName = ?, LastName = ? WHERE EmployeeID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, empId);
            pstmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        String jdbcUrl = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "your_username";
        String password = "your_password";

        DatabaseUpdater updater = new DatabaseUpdater(jdbcUrl, username, password);
        updater.insertOrUpdateEmployee(1001, "John", "Doe");
    }
}
