package Models;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
public class Stock {
	String idProducto;
	float precioProducto;
	int cantidad;
	String idSucursal;
	static List<Stock> listaStocks= new ArrayList<Stock>();
        static Conexion conex;//
        
	public Stock(String idProducto,float precioProducto, int cantidad, String idSucursal) {
            this.idProducto = idProducto;
            this.precioProducto = precioProducto;
            this.cantidad = cantidad;
            this.idSucursal = idSucursal;
        }
        
        public Stock(int id) {
            this.conex = new Conexion();//
            Statement st;//
            ResultSet rs;//
            try {
                st = conex.con.createStatement();
                rs = st.executeQuery("select * from stock where id='"+id+"'");
                this.idProducto=rs.getString("idProducto");
                this.precioProducto=rs.getFloat("precio");
                this.cantidad=rs.getInt("cantidad");
                this.idSucursal=rs.getString("idSucursal");
                conex.con.close();

            } catch (Exception e){
                System.out.println("Error");
            }
        }
        
        public void updateStock( String id , float amount , int price){
            id= id==null?this.idProducto:id;
            amount= amount==0.00?this.precioProducto:amount;
            price= price==0?this.cantidad:price;
            Statement st;
            try {
                st = conex.con.createStatement();
                st.executeQuery("update stock set cantidad='"+amount+"' precioProducto='"+price+"'");
                conex.con.close();

            } catch (Exception e){
                System.out.println("Error");
            }
        }
    
        public static void listarStock(){
            Statement st;
            ResultSet rs;
            try {
                st = conex.con.createStatement();
                rs = st.executeQuery("select * from producto");
                while(rs.next()) {
                    listaStocks.add(new Stock(rs.getString("idProducto"),rs.getFloat("precioProducto"),rs.getInt("cantidad"),rs.getString("idSucursal")));

                }
                conex.con.close();
            } catch (Exception e){
                System.out.println("Error");
            }
        }
        
	public String getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
	public float getPrecioProducto() {
		return precioProducto;
	}
	public void getPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}
	
}
