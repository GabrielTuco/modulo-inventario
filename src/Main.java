

import Views.user_Interface.MainJFrame;
import Controllers.ProductoController;
import Models.Producto;

public class main {

	public static void main(String args[]) {
        
           
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Producto prueba= new Producto();
                MainJFrame menu = new MainJFrame();
                menu.setVisible(true);
                ProductoController prodCon=new ProductoController(menu.adminWorkArea);
                prodCon.llenarTabla();
            }
        });
       
        
    }
}
