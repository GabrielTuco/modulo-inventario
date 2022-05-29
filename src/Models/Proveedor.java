package Models;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class Proveedor {
	String idProveedor;
	String nameProveedor;
	static List<Proveedor> listaProveedores= new ArrayList<Proveedor>();
        static Conexion conex;//conexion
        
	public Proveedor( String idProveedor, String nameProveedor) {
            this.idProveedor = idProveedor;
            this.nameProveedor = nameProveedor;
          
	}
	public Proveedor(int id ) {
            this.conex = new Conexion();//
            Statement st;//
            ResultSet rs;//
            try {
                st = conex.con.createStatement();
                rs = st.executeQuery("select * from proveedor where id='"+id+"'");
                this.idProveedor=rs.getString("idProveedor");
                this.nameProveedor=rs.getString("nameProveedor");
                conex.con.close();

            } catch (Exception e){
                System.out.println("Error");
            }
	}
        
        public void updateProveedor( String id, String nombre){
            id= id==null?this.idProveedor:id;
            nombre= nombre==null?this.nameProveedor:nombre;
            Statement st;
            try {
                st = conex.con.createStatement();
                st.executeQuery("update proveedor set idProveedor='"+id+"' nameProveedor='"+nombre+"'");
                conex.con.close();

            } catch (Exception e){
                System.out.println("Error");
            }
        }
    
        public static void listarProveedor(){
            Statement st;
            ResultSet rs;
            try {
                st = conex.con.createStatement();
                rs = st.executeQuery("select * from proveedor");
                while(rs.next()) {
                    listaProveedores.add(new Proveedor(rs.getString("idProveedor"),rs.getString("nameProveedor")));

                }
                conex.con.close();
            } catch (Exception e){
                System.out.println("Error");
            }
        }
        
	public String getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getNameProveedor() {
		return nameProveedor;
	}
	public void setNameProveedor(String nameProveedor) {
		this.nameProveedor = nameProveedor;
	}
	
}
