package Models;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
public class Producto {
    String idProducto;
    String nameProducto;
    String fechaIngreso;
    String idProveedor;
    static List<Producto> listaProductos= new ArrayList<Producto>();
    static Conexion conex;//
    
    public Producto(String idProducto, String nameProducto, String fechaIngreso, String idProveedor) {
        this.idProducto = idProducto;
        this.nameProducto = nameProducto;
        this.fechaIngreso = fechaIngreso;
        this.idProveedor = idProveedor;
    }
    
    public Producto(int id) {
        this.conex = new Conexion();//
        Statement st;//
        ResultSet rs;//
        try {
            st = conex.con.createStatement();
            rs = st.executeQuery("select * from producto where id='"+id+"'");
            this.idProducto=rs.getString("idProducto");
            this.nameProducto=rs.getString("nameProducto");
            this.fechaIngreso=rs.getString("fechaIngreso");
            this.idProveedor=rs.getString("idProveedor");
            conex.con.close();

        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public void updateProducto( String id, String nombre, String fechI){
        id= id==null?this.idProducto:id;
        nombre= nombre==null?this.nameProducto:nombre;
        fechI= fechI==null?this.fechaIngreso:fechI;
        Statement st;
        try {
            st = conex.con.createStatement();
            st.executeQuery("update producto set idProducto='"+id+"' nameProducto='"+nombre+"' fechaIngreso='"+fechI+"'");
            conex.con.close();

        } catch (Exception e){
            System.out.println("Error");
        }
    }
    
    public static void listarProductos(){
        Statement st;
        ResultSet rs;
        try {
            st = conex.con.createStatement();
            rs = st.executeQuery("select * from producto");
            while(rs.next()) {
                listaProductos.add(new Producto(rs.getString("idProducto"),rs.getString("nameProducto"),rs.getString("fechaIngreso"),rs.getString("idProveedor")));
                
            }
            conex.con.close();
        } catch (Exception e){
            System.out.println("Error");
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
