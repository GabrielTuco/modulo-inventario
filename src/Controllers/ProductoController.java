package Controllers;
import Models.Producto;
import Models.Proveedor;
import Models.Stock;
import Models.Sucursal;
import Views.userinterface.CVSadmin.AddProduct;
import Views.userinterface.CVSadmin.ListaProductos;
import Views.userinterface.CVSadmin.AddProductSucursal;
import Views.userinterface.CVSadmin.DialogueBoxPopUp;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                String nombre = addProdNom.Fieldnombre.getText();
                int aux=0;
                int cont = 3;
                while (aux != 1){
                    if(validarProducto(nombre)){
                        aux=1; 
                    }
                    else{
                        dialog.mensaje(cont);
                        addProdNom.Fieldnombre.setText("");
                        addProdNom.show();
                    }
                    if (cont == 0){
                        System.exit(0);
                    }
                    cont--;
                }
                addProduct();
                addProd.dispose();
            }
        });
        this.addProd.jCheckBox1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                if(addProd.jCheckBox1.isSelected()){
                    addProd.FieldPrecio.setEditable(false);
                    addProd.FieldPrecio.setBackground(new Color(190,190,190));
                }else{
                    addProd.FieldPrecio.setEditable(true);
                    addProd.FieldPrecio.setBackground(new Color(235,237,255));
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
                modelNameProd = new DefaultComboBoxModel();
                llenarOpciones();
                modelNameProd.addElement(addProdNom.Fieldnombre.getText());
                addProd.Fieldnombre.setSelectedIndex(modelNameProd.getSize()-1);
                addProdNom.dispose();
            }
        });

        Producto.listarProductos();
        llenarOpciones();

    }

     public boolean validarProducto(String cadena){
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
