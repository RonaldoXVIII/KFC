
package Modelo;

import Vista.FrmSistemaVenta;
import java.awt.Desktop;
import java.util.ArrayList;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class VentaModel implements Serializable
{
    Venta objVenta;
    ArrayList<Venta> listaVentas;
    FrmSistemaVenta objFrmSistemaVenta;
    ProductoModel objProductoModel;
    FechaYHoraModel objFechaYHoraModel;

    public VentaModel(FrmSistemaVenta objFrmSistemaVenta) 
    {
        this.objFrmSistemaVenta = objFrmSistemaVenta;
        listaVentas = new ArrayList<Venta>();
    }
    
    public void AgregarVenta()
    {
        String codigoVenta = objFrmSistemaVenta.lblNumeroVenta.getText(),
               nombreVendedor = objFrmSistemaVenta.txtNombreVendedor.getText();  
        double totalVenta = Double.parseDouble(objFrmSistemaVenta.lblMonto.getText());
        ArrayList<Producto> listaProductos = objFrmSistemaVenta.objProductoController.ObtenerController();
        FechaYHora objFechaYHora = objFrmSistemaVenta.objFechaYHoraController.ObtenerController();
        
        objVenta = new Venta(codigoVenta, totalVenta, nombreVendedor, listaProductos, objFechaYHora);
   
        listaVentas.add(objVenta);
        
        objFrmSistemaVenta.dtm.setRowCount(0);
    }
    
    public void MostrarVenta()
    {
        File Ffichero = new File("Factura.txt");
        
        try
        {
            if(!Ffichero.exists())
                Ffichero.createNewFile();
            
            BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ffichero)));
            Fescribe.write("-----------REGISTRO DE VENTA-----------\n"
                         + "              Nº "+ objVenta.getCodigoVenta() +"          \n\n"
                         + "VENDEDOR: " + objFrmSistemaVenta.txtNombreVendedor.getText() + "\n"
                         + "PEDIDOS: \n");
            
            ArrayList<Producto> listaProductos = objVenta.getProductos();
            
            for(Producto objeto : listaProductos)            
                Fescribe.write("\t-" + objeto.getNombre() +":   $ " + objeto.getPrecio() + " x " + objeto.getCantidad() + "unid. = $ " +objeto.obtenerTotal()+"\n");        

            FechaYHora fechaYHora = objVenta.getFechaYHora();
            
            Fescribe.write("\nFecha - Hora: "+fechaYHora.toString() +"\n"
                         + "Monto Total: $ " + objVenta.getTotalVenta() + "\n"
                         + "---------------------------------------\n");
                    
            Fescribe.close();
            
            abrirarchivo("Factura.txt");
        }
        catch (Exception ex){}        
    }
    
    public void RegistrarVenta()
    {
        HSSFWorkbook libro = new HSSFWorkbook(); //creamos el libro      
        HSSFSheet hoja1 = libro.createSheet("Registro de Ventas");  //creamos la hoja
        HSSFRow fila;
        HSSFCell celda;
        String cadena = "", cabecera[] = {"Fecha y Hora", "Código", "Producto", "Lote", "Precio", "Cantidad","SubTotal"};
        
        fila = hoja1.createRow(1);
        
        for(int i=0; i<7; i++)
        {         
            celda = fila.createCell(i);
            HSSFRichTextString texto = new HSSFRichTextString(cabecera[i]); //insertamos texto
            celda.setCellValue(texto); 
        }
            
        int i = 0;
        for(Producto producto: objVenta.getProductos())
        {
                fila = hoja1.createRow(i+3);  //creamos una fila específica dentro de la hoja
                
                for(int j=0; j<7 ;j++)
                {                    
                    celda = fila.createCell(j); //creamos una celda específica que debe estar dentro de la fila
            
                    switch(j)
                    {
                        case 0: cadena = objVenta.getFechaYHora().toString(); break;
                        case 1: cadena = producto.getCodigo() + ""; break;
                        case 2: cadena = producto.getNombre(); break;
                        case 3: cadena = objVenta.getCodigoVenta(); break;
                        case 4: cadena = producto.getPrecio() + ""; break;
                        case 5: cadena = producto.getCantidad() + ""; break;
                        case 6: cadena = producto.obtenerTotal() + ""; break;
                    }
                    
                    HSSFRichTextString texto = new HSSFRichTextString(cadena); //insertamos texto
                    celda.setCellValue(texto); //agregamos ese valor(texto) a la celda          
                    hoja1.autoSizeColumn(j); //AUTOAJUSTAR EL TAMAÑO DE LA CELDA MÁS GRANDE
                }              
                i++;
        }

        try //crearemos el libro
        {
            FileOutputStream arch = new FileOutputStream("Libro de Control de Ventas.xls");
            
            libro.write(arch); //escribimos
            arch.close();
        }
        catch(Exception e)
        {
            System.out.println("NO SIRVE :V");
        }    
    }
    
    public void abrirarchivo(String archivo)
    {
        try 
        {
            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);
        }
        catch (IOException ex) 
        {
         System.out.println(ex);
        }
    }    
    
    public Venta obtenerVenta()
    {
        return objVenta;
    }

    public ArrayList<Venta> getListaVentas() 
    {
        return listaVentas;
    }
}
