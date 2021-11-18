
package Controlador;

import Modelo.InventarioModel;
import Modelo.Producto;
import Vista.FrmSistemaVenta;
import java.util.ArrayList;

public class InventarioController 
{
    InventarioModel objInventarioModel;

    public InventarioController(FrmSistemaVenta objFrmSistemaVenta)
    {
        objInventarioModel = new InventarioModel(objFrmSistemaVenta);
    }
    
    public void LlenarController()
    {
        objInventarioModel.LlenaProductosInventario();
    }
    
    public void RegistrarController()
    {
        objInventarioModel.RegistrarInventario();
    }    
    
    public ArrayList<Producto>  ObtenerProductosController()
    {
        return objInventarioModel.ObtenerProductosInventario();
    }
}
