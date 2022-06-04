package Models;
import conexion.Conexion;
import java.sql.PreparedStatement;
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
            
        public static void updateStock( String idProduc, String idSucur, int cantidad){
            
            PreparedStatement ps;
            try {
                ps = Conexion.con.prepareStatement("update stock set cantidad=? where idProducto=? AND idSucursal=?");
                ps.setInt(1, cantidad);
                ps.setString(2, idProduc);
                ps.setString(3, idSucur);
                ps.executeUpdate();
                

            } catch (Exception e){
                System.out.println(e);
            }
        }
    
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
        public static void agregarStock(String idProducto,Float precioProducto, int cantidad, String idSucursal){
            PreparedStatement ps;
        
            try {
                ps = Conexion.con.prepareStatement("insert into stock(idProducto, precioProducto, cantidad,idSucursal) values(?,?,?,?)");
                ps.setString(1, idProducto);
                ps.setFloat(2, precioProducto);
                ps.setInt(3, cantidad);
                ps.setString(4, idSucursal);
                ps.executeUpdate();

            } catch (Exception e){
                System.out.println(e);
            }
        }
        
	public Producto getProducto() {
		return producto;
	}
	public float getPrecioProducto() {
		return precioProducto;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public String getIdSucursal() {
		return idSucursal;
	}

	
}
