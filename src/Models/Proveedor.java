package Models;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class Proveedor {
	String idProveedor;
	String nameProveedor;
	public static List<Proveedor> listaProveedores= new ArrayList<Proveedor>();
        
        
	public Proveedor( String idProveedor, String nameProveedor) {
            this.idProveedor = idProveedor;
            this.nameProveedor = nameProveedor;
          
	}
        
        public static void listarProveedor(){
            listaProveedores= new ArrayList<Proveedor>();
            Statement st;
            ResultSet rs;
            try {
                st = Conexion.con.createStatement();
                rs = st.executeQuery("select * from proveedor");
                while(rs.next()) {
                    listaProveedores.add(new Proveedor(rs.getString("idProveedor"),rs.getString("nameProveedor")));

                }
                
            } catch (Exception e){
                System.out.println("Error");
            }
        }
        
        public static void agregarProveedor(String idProveedor,String nameProveedor){
            PreparedStatement ps;
        
            try {
                ps = Conexion.con.prepareStatement("insert into proveedor(idProveedor, nameProveedor) values(?,?)");
                ps.setString(1, idProveedor);
                ps.setString(2, nameProveedor);
                
                ps.executeUpdate();

            } catch (Exception e){
                System.out.println(e);
            }
        }
        
	public String getIdProveedor() {
		return idProveedor;
	}
	public String getNameProveedor() {
		return nameProveedor;
	}
	
	
}
