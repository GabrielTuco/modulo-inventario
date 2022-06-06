package Controllers;
import Models.Proveedor;
import Models.Sucursal;
import Views.user_Interface.AddProductSucursal;
import Views.user_Interface.AddSucursal;
import Views.user_Interface.DialogueBoxPopUp;
import Views.user_Interface.ListaProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class SucursalController {
    
    ListaProductos listProd;
    AddSucursal addSuc;
    DefaultComboBoxModel model; 
   
    DialogueBoxPopUp dialog;
    
    public SucursalController(ListaProductos _listProd){
        this.listProd = _listProd;
        addSuc = new AddSucursal();
        addSuc.setLocationRelativeTo(null);
        this.dialog= new DialogueBoxPopUp();
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
                String nombre = addSuc.Fieldnombre.getText();
                if(!validarCamposLlenos()){
                    dialog.mensaje("Debe llenar todos los campos");
                }else if(Sucursal.sucursalEnBD(nombre)==true){
                    dialog.mensaje("El nombre ya existe");
                }
                else if (!validarSucursal(nombre)){
                    dialog.mensaje("Nombre incorrecto: no ingresar caracteres especiales");
                }else{
                    addSucursal();
                    llenarOpciones();
                    addSuc.dispose();
                    listProd.storeJComboBox.setSelectedIndex(model.getSize()-1);
                    addSuc.dispose();
                }
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
    
    public boolean validarSucursal(String cadena){
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
        if(addSuc.Fieldnombre.getText().equals("")){
            return false;
        }
        return true;
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
