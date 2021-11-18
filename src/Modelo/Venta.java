
package Modelo;

import java.util.ArrayList;

public class Venta 
{
    private String codigoVenta;
    private double totalVenta;  
    private String nombreVendedor;
    private ArrayList<Producto> productos;
    private FechaYHora fechaYHora;

    public Venta(String codigoVenta, double totalVenta, String nombreVendedor, ArrayList<Producto> productos, FechaYHora fechaYHora)
    {
        this.codigoVenta = codigoVenta;
        this.totalVenta = totalVenta;
        this.nombreVendedor = nombreVendedor;
        this.productos = productos;
        this.fechaYHora = fechaYHora;
    }

    public String getCodigoVenta()
    {
        return codigoVenta;
    }

    public String getNombreVendedor()
    {
        return nombreVendedor;
    }

    public FechaYHora getFechaYHora() 
    {
        return fechaYHora;
    }

    public ArrayList<Producto> getProductos() 
    {
        return productos;
    } 

    public double getTotalVenta()
    {
        return totalVenta;
    }
}
