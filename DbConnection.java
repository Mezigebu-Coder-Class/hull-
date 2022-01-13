
package yyy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

class DbConnection {
     
private static final String url="jdbc:mysql://localhost:3306/ccl";
private static String user="root";
private static String password="";
private static String driver="com.mysql.jdbc.Driver";
 Connection connMethod() throws ClassNotFoundException, SQLException{
   Class.forName(driver);
   Connection con=null;
  
   try{
        con=DriverManager.getConnection(url,user,password); 
       
   }catch(SQLException e){
       
     JOptionPane.showMessageDialog(null, "Error :" +e); 
   }
   try{ 
       Statement  stmt = con.createStatement();
       ResultSet res=stmt.executeQuery("select * from dept_tbl");
       

        while(res.next()){
            
            System.out.println(res.getString(1)+" \t"+res.getString(2)+" \t "+res.getString(3)+" \t"+res.getString(4));       
            
       }
     
   }catch(Exception e){
     JOptionPane.showMessageDialog(null, "Error :" +e); 
       
   }
   
   return con;



    }
}