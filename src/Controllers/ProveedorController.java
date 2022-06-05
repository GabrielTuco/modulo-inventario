package Controllers;
import Models.Producto;
import Models.Proveedor;
import Models.Sucursal;
import Views.userinterface.CVSadmin.AddProductSucursal;
import Views.userinterface.CVSadmin.AddProveedor;
import Views.userinterface.CVSadmin.DialogueBoxPopUp;
import Views.userinterface.CVSadmin.ListaProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
public class ProveedorController {
    AddProductSucursal listProv;
    AddProveedor addprov;
    DefaultComboBoxModel model;
    Proveedor prove;
    DialogueBoxPopUp dialog;
    
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
                String nombre = addprov.Fieldnombre.getText();
                int aux=0;
                int cont = 3;
                while (aux != 1){
                    if(prove.proveedorEnBD(nombre)==false && true==validarProveedor(nombre)){
                        aux=1; 
                    }
                    else{
                        dialog.mensaje(cont);
                        addprov.Fieldnombre.setText("");
                        addprov.show();
                    }
                    if (cont == 0){
                        System.exit(0);
                    }
                    cont--;
                }
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
    
    public boolean validarProveedor(String cadena){
        int valorASCII= 0;
        int cont = 0;
        int j= 0;
        for (j = 0 ; j <= cont; j++){
           if(cont>0){
               cont = 0;
           }
           for (int i = 0 ; i < cadena.length(); i++){
               char caracter = cadena.charAt(i);
               valorASCII = (int) caracter;
               if (valorASCII < 97 || valorASCII >122){
                   cont++;
               }
 
           }
           if (cont == 0){
               return true;//No hay errores
           }
           else {
               return false;// Hay errores
           }
           
        }
        return false;
      
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
