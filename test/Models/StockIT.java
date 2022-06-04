
package Models;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author USER
 */
public class StockIT {
    
    public StockIT() {
    }


    /**
     * Test of updateStock method, of class Stock.
     */
    @Test
    public void testUpdateStock() {
        System.out.println("updateStock");
        String idProduc = "";
        String idSucur = "";
        int cantidad = 0;
        Stock.updateStock(idProduc, idSucur, cantidad);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listarStockBySucursal method, of class Stock.
     */
    @Test
    public void testListarStockBySucursal() {
        System.out.println("listarStockBySucursal");
        String idSuc = "";
        Stock.listarStockBySucursal(idSuc);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of agregarStock method, of class Stock.
     */
    @Test
    public void testAgregarStock() {
        System.out.println("agregarStock");
        String idProducto = "";
        Float precioProducto = null;
        int cantidad = 0;
        String idSucursal = "";
        Stock.agregarStock(idProducto, precioProducto, cantidad, idSucursal);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getProducto method, of class Stock.
     */
    
    /*
    @Test
    public void testGetProducto() {
        System.out.println("getProducto");
        Stock instance = null;
        Producto expResult = null;
        Producto result = instance.getProducto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    */
    
    /**
     * Test of getPrecioProducto method, of class Stock.
     */
    
    /*
    @Test
    public void testGetPrecioProducto() {
        System.out.println("getPrecioProducto");
        Stock instance = null;
        float expResult = 0.0F;
        float result = instance.getPrecioProducto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    */
    
    /**
     * Test of getCantidad method, of class Stock.
     */
    
    /*
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        Stock instance = null;
        int expResult = 0;
        int result = instance.getCantidad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    */

    /**
     * Test of getIdSucursal method, of class Stock.
     */
    
    /*
    @Test
    public void testGetIdSucursal() {
        System.out.println("getIdSucursal");
        Stock instance = null;
        String expResult = "";
        String result = instance.getIdSucursal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    */
}
