package Models;
import conexion.Conexion;
import java.sql.PreparedStatement;
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
	public static void agregarSucursal(String idSucursal,String nombreSucursal){
            PreparedStatement ps;
        
            try {
                ps = Conexion.con.prepareStatement("insert into sucursal(idSucursal, nombreSucursal) values(?,?)");
                ps.setString(1, idSucursal);
                ps.setString(2, nombreSucursal);
                ps.executeUpdate();
                

            } catch (Exception e){
                System.out.println(e);
            }
        }
        
        public static boolean  sucursalEnBD(String nombreSucursal){
            for(Sucursal s : listaSucursales){
                if (s.getNombreSucursal().toLowerCase().equals(nombreSucursal.toLowerCase())){
                    return true;
                }
            }
            return false;
            
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
