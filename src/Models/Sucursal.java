package Models;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class Sucursal {
	String idSucursal;
	String nombreSucursal;
	public static List<Sucursal> listaSucursales;
       
        
     
	public Sucursal(String idSucursal, String nombreSucursal) {
            this.idSucursal = idSucursal;
            this.nombreSucursal = nombreSucursal;

	}
        public Sucursal(int id ) {
            
            Statement st;//
            ResultSet rs;//
            try {
                st = Conexion.con.createStatement();
                rs = st.executeQuery("select * from sucursal where id='"+id+"'");
                this.idSucursal=rs.getString("idSucursal");
                this.nombreSucursal=rs.getString("nombreSucursal");
                

            } catch (Exception e){
                System.out.println("Error");
            }
	}
        
        public void updateSucursal( String id, String nombre){
            id= id==null?this.idSucursal:id;
            nombre= nombre==null?this.nombreSucursal:nombre;
            Statement st;
            try {
                st = Conexion.con.createStatement();
                st.executeQuery("update sucursal set idSucursal='"+id+"' nombreSucursal='"+nombre+"'");
             

            } catch (Exception e){
                System.out.println("Error");
            }
        }
    
        public static void listarSucursal(){
            listaSucursales= new ArrayList<Sucursal>();
            Statement st;
            ResultSet rs;
            try {
                st = Conexion.con.createStatement();
                rs = st.executeQuery("select * from sucursal");
                while(rs.next()) {
                    listaSucursales.add(new Sucursal(rs.getString("idSucursal"),rs.getString("nombreSucursal")));

                }
                
            } catch (Exception e){
                System.out.println("Error");
            }
        }
	
	public String getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	
	
}
