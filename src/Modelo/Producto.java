
package Modelo;

public class Producto
{
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private int stock;

    public Producto(int codigo, String nombre, double precio, int cantidad) 
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Producto(String nombre, int codigo,double precio, int stock) 
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getCodigo() 
    {
        return codigo;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public double getPrecio() 
    {
        return precio;
    }

    public int getCantidad() 
    {
        return cantidad;
    }

    public int getStock() 
    {
        return stock;
    }
    
    public double obtenerTotal()
    {
        return precio * cantidad;
    }
}
