package Controllers;
import Models.Proveedor;
import Models.Sucursal;
import Views.userinterface.CVSadmin.AddProductSucursal;
import Views.userinterface.CVSadmin.AddSucursal;
import Views.userinterface.CVSadmin.DialogueBoxPopUp;
import Views.userinterface.CVSadmin.ListaProductos;
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
    Sucursal sucur;
    DialogueBoxPopUp dialog;
    
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
                String nombre = addSuc.Fieldnombre.getText();
                int aux=0;
                int cont = 3;
                while (aux != 1){
                    if(sucur.sucursalEnBD(nombre)==false && true==validarSucursal(nombre)){
                        aux=1; 
                    }
                    else{
                        dialog.mensaje(cont);
                        addSuc.Fieldnombre.setText("");
                        addSuc.show();
                    }
                    if (cont == 0){
                        System.exit(0);
                    }
                    cont--;
                }
        
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
    
    public boolean validarSucursal(String cadena){
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
        
        int lastNum = Integer.parseInt(Sucursal.listaSucursales.get(Sucursal.listaSucursales.size()-1).getIdSucursal().substring(1))+1;
        String newId= "S";
        for(int i=4;i>String.valueOf(lastNum).length();i--){
                newId += "0";
        }
        newId += String.valueOf(lastNum);
        
        return newId;
    }
    
}
