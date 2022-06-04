package Controllers;
import Models.Producto;
import Models.Proveedor;
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
        model.addColumn("Cantidad");
        model.addColumn("Precio");
        
        
        
        
        for( Stock s: Stock.listaStocks){
            String prov = "" ;/*
            for(Proveedor p: Proveedor.listaProveedores){
                if(p.getIdProveedor().equals(s.getProducto().getIdProveedor())){
                    prov = p.getNameProveedor();
                    break;
                }
            }*/
            model.addRow(new Object[]{s.getProducto().getIdProducto(),s.getProducto().getNameProducto(),s.getCantidad(),s.getPrecioProducto()});
        }
        
        listProd.productsTable.setModel(model);
        
    }
    
}
