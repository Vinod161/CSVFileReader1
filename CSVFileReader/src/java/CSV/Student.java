
package CSV;

 
import java.io.*;
import java.sql.*;
 
public class Student {
 
    public static void main(String[] args) throws ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/task";
        String username = "root";
        String password = "";
            //csv file path
        String csvFilePath = "C://Users//Vinod Parmar//Desktop/data.csv";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
 
            String sql = "INSERT INTO Student (FName, LName,DOB,Age,Address1,Address2,City,State,Country,PostalCode) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
 
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            int count = 0;
 
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String FName = data[0];
                String LName = data[1];
                String DOB = data[2];
                String Age = data[3];
                String Address1 = data[4];
                String Address2 = data[5];
                String City = data[6];
                String State = data[7];
                String Country = data[8];
                String Postal = data[9];
                
                statement.setString(1, FName);
                statement.setString(2, LName);
                statement.setString(3, DOB);
                statement.setString(4, Age);
                statement.setString(5, Address1);
                statement.setString(6, Address2);
                statement.setString(7, City);
                statement.setString(8, State);
                statement.setString(9, Country);
                statement.setString(10, Postal);
                
                
 
                
 
               statement.addBatch();
                 if (count % batchSize == 0) {
                 statement.executeBatch();
                }
            }
 
            lineReader.close();
 
            // execute the remaining queries
            statement.executeBatch();
 
            connection.commit();
            connection.close();
 
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
 
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
    }
}