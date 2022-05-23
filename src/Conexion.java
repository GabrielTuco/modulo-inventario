import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Conexion {
	public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://bcoezkam4s8dlb2yyc7o-mysql.services.clever-cloud.com:3306/bcoezkam4s8dlb2yyc7o/bcoezkam4s8dlb2yyc7o","ujib6gic4laepk37","qPz2XYXURrrWmEm6XPCH");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("show databases;");
            System.out.println("Connected");  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }  
}
