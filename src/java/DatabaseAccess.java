/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author shivang.bhatt
 */
@WebServlet(urlPatterns = {"/DatabaseAccess"})
public class DatabaseAccess extends HttpServlet{
    
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      Connection conn;
      Statement stmt;
      // JDBC driver name and database URL
      final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
      final String DB_URL="jdbc:mysql://localhost/TEST";

      //  Database credentials
      final String USER = "root";
      final String PASS = "";

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
//      String docType =
//        "<!doctype html public \"-//w3c//dtd html 4.0 " +
//         "transitional//en\">\n";
//         out.println(docType +
//         "<html>\n" +
//         "<head><title>" + title + "</title></head>\n" +
//         "<body bgcolor=\"#f0f0f0\">\n" +
//         "<h1 align=\"center\">" + title + "</h1>\n");
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);

         // Execute SQL query
         stmt = conn.createStatement();
         String sql;
         sql = "SELECT id, first, last, age FROM Employees";
         ResultSet rs = stmt.executeQuery(sql);
         int counter = 0;
         out.println("{ \"contacts\":[");

         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            int age = rs.getInt("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            //Display values
            out.println("{");
            out.println("\"id\": " + id + " ");
            out.println(", \"first\": \"" + first + "\" ");
            out.println(", \"last\": \"" + last + "\"");
            out.println(", \"age\": " + age + " ");
            out.println("}");
            if(counter<3){
                out.println(",");
                counter+=1;
            }
         }
//         out.println("</body></html>");
         out.println("]}");

         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
      }finally{
         //finally block used to close resources
//         try{
//            if(stmt!=null)
//               stmt.close();
//         }catch(SQLException se2){
//         }// nothing we can do
//         try{
//            if(conn!=null)
//            conn.close();
//         }catch(SQLException se){
//            se.printStackTrace();
//         }//end finally try
      } //end try
   }
} 