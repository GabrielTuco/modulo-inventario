package Models;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
public class Producto {
    String idProducto;
    String nameProducto;
    String fechaIngreso;
    String idProveedor;
    public static List<Producto> listaProductos= new ArrayList<Producto>();
    
    
    public Producto(String idProducto, String nameProducto, String fechaIngreso, String idProveedor) {
        this.idProducto = idProducto;
        this.nameProducto = nameProducto;
        this.fechaIngreso = fechaIngreso;
        this.idProveedor = idProveedor;
        
    }
   
    public Producto(String id) {
        
        Statement st;//
        ResultSet rs;//
        try {
            st = Conexion.con.createStatement();
            rs = st.executeQuery("select * from producto where idProducto='"+id+"'");
            this.idProducto=rs.getString("idProducto");
            this.nameProducto=rs.getString("nameProducto");
            this.fechaIngreso=rs.getString("fechaIngreso");
            this.idProveedor=rs.getString("idProveedor");
            

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateProducto( String id, String nombre, String fechI){
        id= id==null?this.idProducto:id;
        nombre= nombre==null?this.nameProducto:nombre;
        fechI= fechI==null?this.fechaIngreso:fechI;
        Statement st;
        try {
            st = Conexion.con.createStatement();
            st.executeQuery("update producto set idProducto='"+id+"' nameProducto='"+nombre+"' fechaIngreso='"+fechI+"'");
            

        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    public static void listarProductos(){
        
        listaProductos= new ArrayList<Producto>();
        Statement st;
        ResultSet rs;
        try {
            st = Conexion.con.createStatement();
            rs = st.executeQuery("select * from producto");
            while(rs.next()) {
                listaProductos.add(new Producto(rs.getString("idProducto"),rs.getString("nameProducto"),rs.getString("fechaIngreso"),rs.getString("idProveedor")));
                
            }
            
        } catch (Exception e){
            System.out.println(e);
        }
    }
     public static ArrayList<String> listarNameProducts(){
         ArrayList<String> temp = new ArrayList<String>();
         Statement st;
         ResultSet rs;
         try {
            st = Conexion.con.createStatement();
            rs = st.executeQuery("select * from producto group by nameProducto");
            while(rs.next()) {
                temp.add(rs.getString("nameProducto"));
                
            }
            
        } catch (Exception e){
            System.out.println(e);
        }
         return temp;
         
     }
    
    public static void agregarProducto( String idProducto, String nameProducto, String fechaIngreso, String idProveedor){
        PreparedStatement ps;
        
        try {
            ps = Conexion.con.prepareStatement("insert into producto(idProducto, nameProducto, fechaIngreso,idProveedor) values(?,?,?,?)");
            ps.setString(1, idProducto);
            ps.setString(2, nameProducto);
            ps.setString(3, fechaIngreso);
            ps.setString(4, idProveedor);
            ps.executeUpdate();
            
        } catch (Exception e){
            System.out.println(e);
        }
    }
    

    public String getIdProducto() {
            return idProducto;
    }
    public String getNameProducto() {
            return nameProducto;
    }
    public String getFechaIngreso() {
            return fechaIngreso;
    }
    public String getIdProveedor() {
            return idProveedor;
    }
}
