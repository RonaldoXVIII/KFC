
package Modelo;

import Vista.FrmSistemaVenta;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class InventarioModel 
{
    Inventario objInventario;
    FrmSistemaVenta objFrmSistemaVenta;

    public InventarioModel(FrmSistemaVenta objFrmSistemaVenta) 
    {
        this.objFrmSistemaVenta = objFrmSistemaVenta;
        objInventario = new Inventario();
    }
    
    public void LlenaProductosInventario()
    {  
        objInventario.productosInventario.clear();
        long cantidadProductos = ObtenerCantidadProductos();
        int codigo;
        String nombre;
        double precio;
         int stock;        
         
        for(int i=0; i<cantidadProductos; i++)
        {
            codigo = Integer.parseInt(obtenerAtributo("Códigos.txt",i));
            nombre = obtenerAtributo("Productos.txt",i);
            precio = Double.parseDouble(obtenerAtributo("Precios.txt",i));
            stock = Integer.parseInt(obtenerAtributo("Stock.txt",i));
            
            Producto producto = new Producto(nombre, codigo, precio, stock); 
            objInventario.productosInventario.add(producto);         
            
            if(codigo < 10)
                objFrmSistemaVenta.cboCodigoProducto.addItem("0000" + codigo);
            else
                objFrmSistemaVenta.cboCodigoProducto.addItem("000" + codigo);
        }       
       
    }

    private long ObtenerCantidadProductos()
    {
        File Ffichero = new File("Códigos.txt");
        long n = 0;
        
        try
        {
            BufferedReader Flee = new BufferedReader(new FileReader(Ffichero));
            n = Flee.lines().count();                    
            Flee.close();
        }
        catch(Exception e){}
        
        return n;
    }
    
    private String obtenerAtributo(String nombreArch, int indice)
    {
        String cadena = "";
        int j = 0;
        File Ffichero = new File(nombreArch);
        
        try
        {
            BufferedReader Flee = new BufferedReader(new FileReader(Ffichero));
            String Slinea;
                
            while((Slinea = Flee.readLine()) != null && j != indice)  
                j++;
            
            cadena = Slinea;
                       
            Flee.close();
        }
        catch (Exception ex){}
        
        return cadena;
    }        
    
    public ArrayList<Producto> ObtenerProductosInventario()
    {
        return objInventario.productosInventario;
    }
    
    public void RegistrarInventario()
    {
        HSSFWorkbook libro = new HSSFWorkbook(); //creamos el libro      
        HSSFSheet hoja2 = libro.createSheet("Registro de Inventario");  //creamos la hoja
        HSSFRow fila;
        HSSFCell celda;
        String cadena = "", cabecera[] = {"Fecha y Hora", "Producto", "Stock", "Precio"};
        
        fila = hoja2.createRow(1);
        
        for(int i=0; i<4; i++)
        {         
            celda = fila.createCell(i);
            HSSFRichTextString texto = new HSSFRichTextString(cabecera[i]); //insertamos texto
            celda.setCellValue(texto); 
        }
            
        int i = 0;
        FechaYHora fechaActual = new FechaYHora();
        
        for(Producto producto: objInventario.productosInventario)
        {
                fila = hoja2.createRow(i+3);  //creamos una fila específica dentro de la hoja
                
                for(int j=0; j<4 ;j++)
                {                    
                    celda = fila.createCell(j); //creamos una celda específica que debe estar dentro de la fila

                    switch(j)
                    {
                        case 0: cadena = fechaActual.toString(); break;
                        case 1: cadena = producto.getNombre();break;                        
                        case 2: cadena = producto.getStock() + ""; break; 
                        case 3: cadena = producto.getPrecio() + "";  break;
                    }
                    
                    HSSFRichTextString texto = new HSSFRichTextString(cadena); //insertamos texto
                    celda.setCellValue(texto); //agregamos ese valor(texto) a la celda          
                    hoja2.autoSizeColumn(j);                 
                }              
                i++;
        }

        try //crearemos el libro
        {
            FileOutputStream arch = new FileOutputStream("Libro de Inventario.xls");
            
            libro.write(arch); //escribimos
            arch.close();
        }
        catch(Exception e)
        {
            System.out.println("NO SIRVE :V");
        }    
        
        //abrimos
        
        String fileLocal = new String("Libro de Inventario.xls"); 
        
        try
        { 
            File path = new File (fileLocal);
            Desktop.getDesktop().open(path);
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el archivo","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }  
    }    
}
