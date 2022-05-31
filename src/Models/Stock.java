package Models;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
public class Stock {
	Producto producto;
	float precioProducto;
	int cantidad;
	String idSucursal;
	public static List<Stock> listaStocks;
        
        
        
        
	public Stock(Producto idProducto,float precioProducto, int cantidad, String idSucursal) {
            this.producto = idProducto;
            this.precioProducto = precioProducto;
            this.cantidad = cantidad;
            this.idSucursal = idSucursal;
        }
            /*
        public void updateStock( String id , float amount , int price){
            id= id==null?this.idProducto:id;
            amount= amount==0.00?this.precioProducto:amount;
            price= price==0?this.cantidad:price;
            Statement st;
            try {
                st = Conexion.con.createStatement();
                st.executeQuery("update stock set cantidad='"+amount+"' precioProducto='"+price+"'");
                

            } catch (Exception e){
                System.out.println("Error");
            }
        }*/
    
        public static void listarStockBySucursal(String idSuc){
            listaStocks= new ArrayList<Stock>();
            Statement st;
            ResultSet rs;
            try {
                st = Conexion.con.createStatement();
                rs = st.executeQuery("select * from stock inner join producto on stock.idProducto=producto.idProducto where idSucursal='"+idSuc+"'");
                
                while(rs.next()) {
                    listaStocks.add(new Stock(new Producto(rs.getString("idProducto"),rs.getString("nameProducto"),rs.getString("fechaIngreso"),rs.getString("idProveedor")),rs.getFloat("precioProducto"),rs.getInt("cantidad"),rs.getString("idSucursal")));

                }
                
            } catch (Exception e){
                System.out.println("Error stock");
            }
        }
        
	public Producto getProducto() {
		return producto;
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

	public String getIdSucursal() {
		return idSucursal;
	}

	
}
