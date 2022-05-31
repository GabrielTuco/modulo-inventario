package Controllers;
import Models.Producto;
import Models.Stock;
import Models.Sucursal;
import Views.userinterface.CVSadmin.ListaProductos;
import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;
import javax.swing.table.DefaultTableModel;
public class StockController {
    ListaProductos listProd;
    public StockController(ListaProductos _listProd){
        this.listProd = _listProd;
        listProd.storeJComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                   llenarTabla();
                   // do something with object
                }
            } 
        });

        
    }
    
    public void llenarTabla(){
        Sucursal.listarSucursal();
        Stock.listarStockBySucursal(Sucursal.listaSucursales.get(listProd.storeJComboBox.getSelectedIndex()).getIdSucursal());
        Producto.listarProductos();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Fecha de ingreso");
        model.addColumn("Proveedor");
        
        
        for( Stock s: Stock.listaStocks){
            model.addRow(new Object[]{s.getProducto().getIdProducto(),s.getProducto().getNameProducto(),s.getProducto().getFechaIngreso(),s.getProducto().getIdProveedor()});
        }
        
        listProd.productsTable.setModel(model);
        
    }
}
