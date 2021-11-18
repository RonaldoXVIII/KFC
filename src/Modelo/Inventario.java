
package Modelo;

import java.util.ArrayList;

public class Inventario
{
    ArrayList<Venta> ventasInventario;
    ArrayList<Producto> productosInventario;

    public Inventario() 
    {
        ventasInventario = new ArrayList<>();
        productosInventario = new ArrayList<>();
    }

    public ArrayList<Venta> getVentas() 
    {
        return ventasInventario;
    }

    public ArrayList<Producto> getProductosInventario()
    {
        return productosInventario;
    }

    public void setProductosInventario(ArrayList<Producto> productosInventario)
    {
        this.productosInventario = productosInventario;
    }

}
