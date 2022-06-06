package Controllers;
import Models.Producto;
import Models.Proveedor;
import Models.Stock;
import Models.Sucursal;
import Views.user_Interface.AddProduct;
import Views.user_Interface.ListaProductos;
import Views.user_Interface.AddProductSucursal;
import Views.user_Interface.DialogueBoxPopUp;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;



public class ProductoController {
    ListaProductos listProd;
    AddProductSucursal addProd;
    ProveedorController conProv;
    AddProduct addProdNom;
    DefaultComboBoxModel modelNameProd;
    StockController conStock;
    Producto produc;
    DialogueBoxPopUp dialog;

    public ProductoController(ListaProductos _listProd,StockController _conStock){
        modelNameProd = new DefaultComboBoxModel();

        this.conStock = _conStock;
        this.listProd = _listProd;
        this.addProd = new AddProductSucursal();
        this.addProd.setLocationRelativeTo(null);

        this.addProdNom = new AddProduct();
        this.addProdNom.setLocationRelativeTo(null);

        this.conProv = new ProveedorController(this.addProd);
        this.conProv.llenarOpciones();
        this.dialog= new DialogueBoxPopUp();

        listProd.BotaddProduct.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {

                addProd.FieldPrecio.setText("");
                addProd.FieldStock.setText("");
                addProd.Fieldnombre.setSelectedIndex(0);
                addProd.proveedores.setSelectedIndex(0);
                addProd.show();
            }
        });
        this.addProd.BotAtras.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {

                addProd.dispose();

            }
        });
        this.addProd.createProduct.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                if(!validarCamposLlenos()){
                    dialog.mensaje("Debe llenar todos los campos");
                }
                else if(addProd.FieldPrecio.isEditable()){
                    if (!validarPrecio() || !isValidDouble(addProd.FieldPrecio.getText())){
                        dialog.mensaje("El precio debe ser un numero");
                    }
                }else if (!validarCantidad()){
                    dialog.mensaje("La cantidad debe ser un numero entero");
                }else{
                    addProduct();
                    addProd.dispose();
                }
                
            }
        });
        this.addProd.Fieldnombre.addItemListener(new ItemListener() {
             public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                    if(!addProd.Fieldnombre.getSelectedItem().toString().equals("-")){
                        
                        for(Stock s: Stock.listaStocks){
                            if(s.getProducto().getNameProducto().equals(addProd.Fieldnombre.getSelectedItem().toString())){
                                addProd.FieldPrecio.setEditable(false);
                                addProd.FieldPrecio.setBackground(new Color(190,190,190));
                                return;
                            }
                            
                        }
                        addProd.FieldPrecio.setEditable(true);
                        addProd.FieldPrecio.setBackground(new Color(235,237,255));
                    }else{
                        addProd.FieldPrecio.setEditable(true);
                        addProd.FieldPrecio.setBackground(new Color(235,237,255));
                    }
                }
            }
        });

        //añadir nombre producto
        this.addProd.addProduct1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                addProdNom.Fieldnombre.setText("");
                addProdNom.show();
            }
        });
        this.addProdNom.BotAtras.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {

                addProdNom.dispose();

            }
        });
        this.addProdNom.createProduct.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                String nombre = addProdNom.Fieldnombre.getText();
                if(nombre.equals("")){
                    dialog.mensaje("Debe llenar todos los campos");
                }else if(Producto.productoEnBD(nombre)==true){
                    dialog.mensaje("El nombre ya existe");
                }
                else if (!validarProducto(nombre)){
                    dialog.mensaje("Nombre incorrecto: no ingresar caracteres especiales");
                }else{
                    modelNameProd = new DefaultComboBoxModel();
                    llenarOpciones();
                    modelNameProd.addElement(addProdNom.Fieldnombre.getText());
                    addProd.Fieldnombre.setSelectedIndex(modelNameProd.getSize()-1);
                    addProdNom.dispose();
                }
            }
        });

        Producto.listarProductos();
        llenarOpciones();

    }
    public boolean validarCamposLlenos(){
        if(this.addProd.Fieldnombre.getSelectedItem().toString().equals("-") || this.addProd.FieldStock.getText().equals("")){
            return false;
        }
        if(this.addProd.FieldPrecio.isEditable() && this.addProd.FieldPrecio.getText().equals("")){
            return false;
        }
        return true;
    }
    public boolean validarPrecio(){
        
        String cadena = addProd.FieldPrecio.getText();
        int valorASCII= 0;
        for (int i = 0 ; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);
            valorASCII = (int) caracter;

            if ((valorASCII < 48 || valorASCII >57)  && valorASCII != 46){
               return false;
            }


        }
        
        return true;

    }
    private boolean isValidDouble(String s) {
        final String Digits     = "(\\p{Digit}+)";
        final String HexDigits  = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally
        // signed decimal integer.
        final String Exp        = "[eE][+-]?"+Digits;
        final String fpRegex    =
            ("[\\x00-\\x20]*"+  // Optional leading "whitespace"
             "[+-]?(" + // Optional sign character
             "NaN|" +           // "NaN" string
             "Infinity|" +      // "Infinity" string

             // A decimal floating-point string representing a finite positive
             // number without a leading sign has at most five basic pieces:
             // Digits . Digits ExponentPart FloatTypeSuffix
             //
             // Since this method allows integer-only strings as input
             // in addition to strings of floating-point literals, the
             // two sub-patterns below are simplifications of the grammar
             // productions from section 3.10.2 of
             // The Java Language Specification.

             // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
             "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

             // . Digits ExponentPart_opt FloatTypeSuffix_opt
             "(\\.("+Digits+")("+Exp+")?)|"+

             // Hexadecimal strings
             "((" +
              // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
              "(0[xX]" + HexDigits + "(\\.)?)|" +

              // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
              "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

              ")[pP][+-]?" + Digits + "))" +
             "[fFdD]?))" +
             "[\\x00-\\x20]*");// Optional trailing "whitespace"

        return Pattern.matches(fpRegex, s);
    }
    
    public boolean validarCantidad(){
        String cadena = addProd.FieldStock.getText();
        int valorASCII= 0;
        for (int i = 0 ; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);
            valorASCII = (int) caracter;
            
            if ((valorASCII < 48 || valorASCII >57)){
               return false;
            }

        }
        return true;

    }
    
    public boolean validarProducto(String cadena){
        int valorASCII= 0;
        for (int i = 0 ; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);
            valorASCII = (int) caracter;
            
            if ((valorASCII < 97 || valorASCII >122) && (valorASCII < 65 || valorASCII >90) &&(valorASCII < 48 || valorASCII >57) && valorASCII != 241 && valorASCII != 209 && valorASCII != 32 && valorASCII != 40 && valorASCII != 41 && valorASCII != 46 && valorASCII != 45){
               return false;
            }
            if(i<4){
                if((valorASCII < 97 || valorASCII >122) && (valorASCII < 65 || valorASCII >90) && valorASCII != 241 && valorASCII != 209){
                    return false;
                }
            }
        }
        return true;

    }

    public void addProduct(){
        String nombre= this.addProd.Fieldnombre.getSelectedItem().toString()
               ,precio= this.addProd.FieldPrecio.getText()
               ,stock= this.addProd.FieldStock.getText()
               ,proveedor = Proveedor.listaProveedores.get(addProd.proveedores.getSelectedIndex()).getIdProveedor();
        DateTimeFormatter fechEntrada = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String id= getNewId();
        if(!productExists(nombre,proveedor)){

            Producto.agregarProducto(id, nombre, fechEntrada.format(LocalDateTime.now()), proveedor);
            this.llenarOpciones();
            Producto.listarProductos();
        }

        if(productExistsStock(nombre)){

            String idProd="";
            String idSuc= Sucursal.listaSucursales.get(listProd.storeJComboBox.getSelectedIndex()).getIdSucursal();
            int cantidad=Integer.parseInt(stock);
            for(Producto p : Producto.listaProductos){
                if(p.getNameProducto().equals(nombre)){
                    idProd=p.getIdProducto();
                    break;
                }
            }
            for( Stock s: Stock.listaStocks){
                if(s.getProducto().getNameProducto().equals(nombre)){
                    cantidad += s.getCantidad();
                    break;
                }
            }
            Stock.updateStock(idProd, idSuc, cantidad);
        }else{

            String idProd="";
            for(Producto p : Producto.listaProductos){
                if(p.getNameProducto().equals(nombre)){
                    idProd=p.getIdProducto();
                    break;
                }
            }
            System.out.println(idProd);
            Stock.agregarStock(idProd, Float.valueOf(precio), Integer.parseInt(stock), Sucursal.listaSucursales.get(listProd.storeJComboBox.getSelectedIndex()).getIdSucursal());
        }

        conStock.llenarTabla();
    }

    private String getNewId(){

        int lastNum = Integer.parseInt(Producto.listaProductos.get(Producto.listaProductos.size()-1).getIdProducto().substring(1))+1;
        String newId= "A";
        for(int i=4;i>String.valueOf(lastNum).length();i--){
                newId += "0";
        }
        newId += String.valueOf(lastNum);

        return newId;
    }

    public void llenarOpciones(){


        modelNameProd.addElement("-");
        for( String s:Producto.listarNameProducts()){
            modelNameProd.addElement(s);
        }

        this.addProd.Fieldnombre.setModel(modelNameProd);


    }

    private boolean productExistsStock(String nombre){
        for( Stock s: Stock.listaStocks){
                if(s.getProducto().getNameProducto().equals(nombre)){
                    return true;
                }
        }
        return false;
    }
    private boolean productExists(String nombre, String prov){
        for( Producto p: Producto.listaProductos){
                if(p.getNameProducto().equals(nombre) && p.getIdProveedor().equals(prov)){
                    return true;
                }
        }
        return false;
    }

}
