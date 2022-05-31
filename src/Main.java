

import Views.user_Interface.MainJFrame;
import Controllers.ProductoController;
import Controllers.StockController;
import Controllers.SucursalController;  
import Models.Producto;
import Models.Stock;
import Models.Sucursal;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import conexion.Conexion;

public class main {

	public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Conexion con = new Conexion();
                MainJFrame menu = new MainJFrame();
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                ProductoController prodCon=new ProductoController(menu.adminWorkArea);
                SucursalController sucCon= new SucursalController(menu.adminWorkArea);
                StockController stoCon = new StockController(menu.adminWorkArea);
                stoCon.llenarTabla();
                sucCon.llenarOpciones();
                menu.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        con.closeConexion();
                    }
                });
                
            }
        });
        
        
        
    }
    protected void finalize() {  
        System.out.print("Destroyed ");  
    } 

    
}
