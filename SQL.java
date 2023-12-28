import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLExample {

    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/your_database";
            String user = "saikanth";
            String password = "Kusianu@143";
            Connection connection = DriverManager.getConnection(url, user, password);

            // Perform database operations
            createTable(connection);
            insertData(connection);
            fetchData(connection);

            // Close the connection when done
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        // Creating a sample table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "username VARCHAR(255)," +
                "email VARCHAR(255)" +
                ")";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.execute();
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        // Inserting data into the table
        String insertDataSQL = "INSERT INTO users (username, email) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
            preparedStatement.setString(1, "john_doe");
            preparedStatement.setString(2, "john@example.com");
            preparedStatement.executeUpdate();
        }
    }

    private static void fetchData(Connection connection) throws SQLException {
        // Fetching data from the table
        String fetchDataSQL = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchDataSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("email"));
            }
        }
    }
}
