package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Conexion {
    public Connection con;
    Statement st;
    public ResultSet rs;
	
    public Conexion() {
        try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://ujib6gic4laepk37:qPz2XYXURrrWmEm6XPCH@bcoezkam4s8dlb2yyc7o-mysql.services.clever-cloud.com:3306/bcoezkam4s8dlb2yyc7o",
                    "ujib6gic4laepk37",
                    "qPz2XYXURrrWmEm6XPCH");
            st = con.createStatement();
            
            System.out.println("Connected");  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    /*
    public static void main(String[] args) {
        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        try {
            st = cn.con.createStatement();
            rs = st.executeQuery("select * from producto");
            while(rs.next()) {
                System.out.println(rs.getString("idProducto")+" "+rs.getString("nameProducto")+" "+rs.getString("fechaIngreso")+" "+rs.getString("idProveedor"));
            }
            cn.con.close();
        } catch (Exception e){
            System.out.println("Error");
        }
                
    }*/
}
