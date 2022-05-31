package Controllers;
import Models.Producto;
import Models.Stock;
import Models.Sucursal;
import Views.userinterface.CVSadmin.ListaProductos;
import javax.swing.table.DefaultTableModel;

public class ProductoController {
    ListaProductos listProd;
    public ProductoController(ListaProductos _listProd){
        this.listProd = _listProd;
    }
    
    public void llenarTabla(){
        Producto.listarProductos();
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Fecha de ingreso");
        model.addColumn("Proveedor");
        
        
        for( Producto p:Producto.listaProductos){    
            model.addRow(new Object[]{p.getIdProducto(),p.getNameProducto(),p.getFechaIngreso(),p.getIdProveedor()});
        }
        listProd.productsTable.setModel(model);
        
    }
}
