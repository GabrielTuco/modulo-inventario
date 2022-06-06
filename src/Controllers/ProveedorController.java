package Controllers;
import Models.Producto;
import Models.Proveedor;
import Models.Sucursal;
import Views.user_Interface.AddProductSucursal;
import Views.user_Interface.AddProveedor;
import Views.user_Interface.DialogueBoxPopUp;
import Views.user_Interface.ListaProductos;
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
        this.dialog= new DialogueBoxPopUp();
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
                if(!validarCamposLlenos()){
                    dialog.mensaje("Debe llenar todos los campos");
                }else if(Proveedor.proveedorEnBD(nombre)==true){
                    dialog.mensaje("El nombre ya existe");
                }
                else if (!validarProveedor(nombre)){
                    dialog.mensaje("Nombre incorrecto: no ingresar caracteres especiales");
                }else{
                    addProveedor();
                    model = new DefaultComboBoxModel();
                    llenarOpciones();
                    model.addElement(addprov.Fieldnombre.getText());
                    addprov.dispose();
                    listProv.proveedores.setSelectedIndex(model.getSize()-1);
                }
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
        for (int i = 0 ; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);
            valorASCII = (int) caracter;
           
            if ((valorASCII < 97 || valorASCII >122) && (valorASCII < 65 || valorASCII >90) &&(valorASCII < 48 || valorASCII >57) && valorASCII != 241 && valorASCII != 209 && valorASCII != 32){
               return false;
            }
            if(i<4){
                if((valorASCII < 97 || valorASCII >122) && (valorASCII < 65 || valorASCII >90)){
                    return false;
                }
            }

        }
        return true;
      
    }
    public boolean validarCamposLlenos(){
        if(addprov.Fieldnombre.getText().equals("")){
            return false;
        }
        return true;
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
