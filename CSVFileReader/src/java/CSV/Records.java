package CSV;



    import java.io.*;  
    import javax.servlet.*;  
    import javax.servlet.http.*;  
    import java.sql.*;  
    import javax.servlet.annotation.WebServlet;
        @WebServlet(name = "Records", urlPatterns = {"/Records"})
    public class Records extends HttpServlet  
    {    
         @Override
         public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException 
          {  
             PrintWriter out = res.getWriter();  
             res.setContentType("text/html");  
             out.println("<html><body>");  
             
              Connection con = null;
             try 
             {  
                Class.forName("com.mysql.jdbc.Driver"); 
                 
                 out.println("<h2>driver loaded</h2>");
                 
                  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "");  
                   con.setAutoCommit(false);
                 
                 out.println("<h2>Conncetion established</h2>");

                 Statement stmt = con.createStatement();  
                 ResultSet rs = stmt.executeQuery("select * from student");  
                 out.println("<table border=1 width=50% height=50%>");  
                 out.println("<tr><th>First Name</th><th>Last Name</th><th>DOB</th><th>Age</th><th>Address Line1</th><th>Adress Line2</th><th>City</th><th>State</th><th>Country</th><th>Postal Code</th><tr>");  
                 while (rs.next()) 
                 {  
                     String first = rs.getString("FName");  
                     String last = rs.getString("LName"); 
                    String dob = rs.getString("DOB");
                    
                     int age = rs.getInt("Age");   
                     String add1 = rs.getString("Address1");
                     String add2 = rs.getString("Address2");
                     String city = rs.getString("City");
                     String state = rs.getString("State");
                     String country = rs.getString("Country");
                     int postal = rs.getInt("PosatlCode");  
                     out.println("<tr><td>" + first + "</td><td>" + last + "</td><td>" + dob+ "</td><td>" + age+ "</td><td>" + add1+ "</td><td>" + add2+ "</td><td>" + city+ "</td><td>" + city+ "</td><td>" + state+ "</td><td>" + country+ "</td><td>" + postal+ "</td></tr>");   
                 }  
                 out.println("</table>");  
                 out.println("</html></body>");  
                 con.close();  
                }  
                 catch (Exception e) 
                {  
                 out.println("error");  
                 
             }
          }
     }  
