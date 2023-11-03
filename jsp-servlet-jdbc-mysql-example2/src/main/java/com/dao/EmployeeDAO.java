package com.dao;

import com.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private String url = "jdbc:mysql://localhost:3306/users";
    private String username = "root";
    private String password = "1326";

    private static final String INSERT_EMPLOYEE_SQL =
            "INSERT INTO employee (First_Name, Last_Name, Designation, DOB, Email, Mobile_No) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_EMPLOYEE_BY_ID =
            "SELECT Employee_No, First_Name, Last_Name, Designation, DOB, Email, Mobile_No FROM employee WHERE Employee_No = ?;";
    private static final String SELECT_ALL_EMPLOYEE = "SELECT * FROM employee";
    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE Employee_No = ?;";
    private static final String UPDATE_EMPLOYEE_SQL ="UPDATE employee SET First_Name = ?, Last_Name = ?, Designation = ?, DOB = ?, Email = ?, Mobile_No = ? WHERE Employee_No = ?;";

    public EmployeeDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
        return connection;
    }

    public void insertEmployee(Employee employee) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            preparedStatement.setString(1, employee.getFirst_Name());
            preparedStatement.setString(2, employee.getLast_Name());
            preparedStatement.setString(3, employee.getDesignation());
            preparedStatement.setString(4, employee.getDOB());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setString(6, employee.getMobile_No());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Employee selectEmployee(int Employee_No) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setInt(1, Employee_No);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String First_Name = rs.getString("First_Name");
                String Last_Name = rs.getString("Last_Name");
                String Designation = rs.getString("Designation");
                String DOB = rs.getString("DOB");
                String Email = rs.getString("Email");
                String Mobile_No = rs.getString("Mobile_No");
                employee = new Employee(Employee_No, First_Name, Last_Name, Designation, DOB, Email, Mobile_No);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }

    public List<Employee> selectAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Employee_No = rs.getInt("Employee_No");
                String First_Name = rs.getString("First_Name");
                String Last_Name = rs.getString("Last_Name");
                String Designation = rs.getString("Designation");
                String DOB = rs.getString("DOB");
                String Email = rs.getString("Email");
                String Mobile_No = rs.getString("Mobile_No");
                employees.add(new Employee(Employee_No, First_Name, Last_Name, Designation, DOB, Email, Mobile_No));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return employees;
    }

    public boolean deleteUser(int Employee_No) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL)) {
            statement.setInt(1, Employee_No);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            statement.setString(1, employee.getFirst_Name());
            statement.setString(2, employee.getLast_Name());
            statement.setString(3, employee.getDesignation()); 
            statement.setString(4, employee.getDOB()); 
            statement.setString(5, employee.getEmail()); 
            statement.setString(6, employee.getMobile_No()); 
            statement.setInt(7, employee.getEmployee_No());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
