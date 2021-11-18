
package Controlador;

import Modelo.Producto;
import Modelo.ProductoModel;
import Vista.FrmSistemaVenta;
import java.util.ArrayList;

public class ProductoController 
{
    ProductoModel objProductoModel;
    
    public ProductoController(FrmSistemaVenta objFrmSistemaVenta) 
    {
        objProductoModel = new ProductoModel(objFrmSistemaVenta);
    }    
    
    public void AdicionarController()
    {
       objProductoModel.AgregarProducto(); 
    }
    
    public void ListarController()
    {
       objProductoModel.ListarProducto(); 
    }      
    
    public void VaciarController()
    {
        objProductoModel.VaciarProducto();
    }
    
    public ArrayList<Producto> ObtenerController()
    {
        return objProductoModel.getListaProductos();
    }
    
    public void BuscarController()
    {
        objProductoModel.BuscarProducto();
    }
    
    public void ActualizarController()
    {
        objProductoModel.ActualizarStock();
    }    
}
