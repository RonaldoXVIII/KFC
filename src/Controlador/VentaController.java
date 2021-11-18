
package Controlador;

import Modelo.Venta;
import Modelo.VentaModel;
import Vista.FrmSistemaVenta;
import java.util.ArrayList;

public class VentaController 
{
    VentaModel objVentaModel;
    
    public VentaController(FrmSistemaVenta objFrmSistemaVenta) 
    {
        objVentaModel = new VentaModel(objFrmSistemaVenta);
    }    
    
    public void AdicionarController()
    {
       objVentaModel .AgregarVenta(); 
    }
    
    public void RegistrarController()
    {
       objVentaModel.RegistrarVenta();
    }
    
    public void MostrarController()
    {
       objVentaModel .MostrarVenta(); 
    }      
    
    public Venta ObtenerController()
    {
       return objVentaModel.obtenerVenta(); 
    }          
    
    public ArrayList<Venta> geVentasController() 
    {
        return objVentaModel.getListaVentas();
    }
}
