package Controllers;
import Models.Producto;
import Models.Proveedor;
import Models.Sucursal;
import Views.userinterface.CVSadmin.AddProductSucursal;
import Views.userinterface.CVSadmin.AddProveedor;
import Views.userinterface.CVSadmin.ListaProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
public class ProveedorController {
    AddProductSucursal listProv;
    AddProveedor addprov;
    DefaultComboBoxModel model;
    public ProveedorController(AddProductSucursal _listProv){
        model = new DefaultComboBoxModel();
        this.listProv = _listProv;
        this.addprov = new AddProveedor();
        this.addprov.setLocationRelativeTo(null);
        this.listProv.addProveedor.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                addprov.Fieldnombre.setText("");
                addprov.show();
            }
        });
        this.addprov.BotAtras.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                
                addprov.dispose();
                
            }
        });
        this.addprov.createProduct.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                
                addProveedor();
                model = new DefaultComboBoxModel();
                llenarOpciones();
                model.addElement(addprov.Fieldnombre.getText());
                addprov.dispose();
                listProv.proveedores.setSelectedIndex(model.getSize()-1);
                
            }
        });
    }
    public void llenarOpciones(){
        Proveedor.listarProveedor();
        
        for( Proveedor s:Proveedor.listaProveedores){    
            model.addElement(s.getNameProveedor());
        }
        listProv.proveedores.setModel(model);
        
    }
    
    public void addProveedor(){
        String idProv = this.getNewId(),
               nombre = addprov.Fieldnombre.getText();
        Proveedor.agregarProveedor(idProv, nombre);
    }
    private String getNewId(){
        
        int lastNum = Integer.parseInt(Proveedor.listaProveedores.get(Proveedor.listaProveedores.size()-1).getIdProveedor().substring(1))+1;
        String newId= "P";
        for(int i=4;i>String.valueOf(lastNum).length();i--){
                newId += "0";
        }
        newId += String.valueOf(lastNum);
        
        return newId;
    }
}
