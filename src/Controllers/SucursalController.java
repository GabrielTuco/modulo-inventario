package Controllers;
import Models.Proveedor;
import Models.Sucursal;
import Views.userinterface.CVSadmin.AddProductSucursal;
import Views.userinterface.CVSadmin.AddSucursal;
import Views.userinterface.CVSadmin.ListaProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
public class SucursalController {
    
    ListaProductos listProd;
    AddSucursal addSuc;
    DefaultComboBoxModel model; 
    
    public SucursalController(ListaProductos _listProd){
        this.listProd = _listProd;
        addSuc = new AddSucursal();
        addSuc.setLocationRelativeTo(null);
        this.listProd.addSucursal.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                addSuc.Fieldnombre.setText("");
                addSuc.show();
                
            }
        });
        this.addSuc.BotAtras.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                
                addSuc.dispose();
                
            }
        });
        this.addSuc.createSucursal.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                addSucursal();
                
                llenarOpciones();
                
                addSuc.dispose();
                listProd.storeJComboBox.setSelectedIndex(model.getSize()-1);
                addSuc.dispose();
            }
        });
    }
    public void llenarOpciones(){
        Sucursal.listarSucursal();
        model = new DefaultComboBoxModel();
        for( Sucursal s:Sucursal.listaSucursales){    
            model.addElement(s.getNombreSucursal());
        }
        listProd.storeJComboBox.setModel(model);
        
    }
    public void addSucursal(){
        String idSuc = this.getNewId(),
               nombre = addSuc.Fieldnombre.getText();
        Sucursal.agregarSucursal(idSuc, nombre);
    }
    private String getNewId(){
        
        int lastNum = Integer.parseInt(Sucursal.listaSucursales.get(Sucursal.listaSucursales.size()-1).getIdSucursal().substring(1))+1;
        String newId= "S";
        for(int i=4;i>String.valueOf(lastNum).length();i--){
                newId += "0";
        }
        newId += String.valueOf(lastNum);
        
        return newId;
    }
    
}
