package Controllers;
import Models.Sucursal;
import Views.userinterface.CVSadmin.ListaProductos;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
public class SucursalController {
    
    ListaProductos listProd;
    public SucursalController(ListaProductos _listProd){
        this.listProd = _listProd;
    }
    public void llenarOpciones(){
        Sucursal.listarSucursal();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for( Sucursal s:Sucursal.listaSucursales){    
            model.addElement(s.getNombreSucursal());
        }
        listProd.storeJComboBox.setModel(model);
        
    }
}
