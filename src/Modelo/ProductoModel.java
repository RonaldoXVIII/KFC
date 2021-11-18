
package Modelo;

import Vista.FrmSistemaVenta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductoModel 
{ 
    Producto objProducto;
    ArrayList<Producto> listaProductos;
    FrmSistemaVenta objFrmSistemaVenta;
    DecimalFormat df = new DecimalFormat("##.##");

    public ProductoModel(FrmSistemaVenta objFrmSistemaVenta) 
    {
        this.objFrmSistemaVenta = objFrmSistemaVenta;
        listaProductos = new ArrayList<Producto>();
    }
    
    public void AgregarProducto()
    {
        int codigoProducto = Integer.parseInt(objFrmSistemaVenta.cboCodigoProducto.getSelectedItem().toString());
        String nombreProducto = objFrmSistemaVenta.txtNombreProducto.getText();
        double precioProducto = Double.parseDouble(objFrmSistemaVenta.txtPrecioProducto.getText());
        int cantidadProducto = (Integer)objFrmSistemaVenta.spCantidadProducto.getValue();       
    
        objProducto = new Producto(codigoProducto, nombreProducto, precioProducto, cantidadProducto);
        
        listaProductos.add(objProducto);

        objFrmSistemaVenta.cboCodigoProducto.setSelectedIndex(0);
        objFrmSistemaVenta.txtPrecioProducto.setText("");
        objFrmSistemaVenta.spCantidadProducto.setValue(1);
        objFrmSistemaVenta.txtNombreProducto.setText("");
    }
    
    public void ListarProducto()
    {
        objFrmSistemaVenta.dtm.setNumRows(listaProductos.size());
        int i = 0;
        double montoTotal = 0;
        
        for(Producto objeto: listaProductos)
        {
            objFrmSistemaVenta.dtm.setValueAt(objeto.getCodigo(), i, 0);
            objFrmSistemaVenta.dtm.setValueAt(objeto.getNombre(), i, 1);
            objFrmSistemaVenta.dtm.setValueAt(objeto.getCantidad(), i, 2);
            objFrmSistemaVenta.dtm.setValueAt(df.format(objeto.getPrecio()), i, 3);
            objFrmSistemaVenta.dtm.setValueAt(df.format(objeto.obtenerTotal()), i, 4);
            montoTotal += objeto.obtenerTotal();
            i++;
        }
        objFrmSistemaVenta.tblReporte.setModel(objFrmSistemaVenta.dtm);
        objFrmSistemaVenta.lblMonto.setText(df.format(montoTotal)+"");
    }
    
    public void VaciarProducto()
    {
        listaProductos.clear();
        objFrmSistemaVenta.dtm.setRowCount(0);
        objFrmSistemaVenta.lblMonto.setText("0.00");
    }

    public ArrayList<Producto> getListaProductos() 
    {
        return listaProductos;
    }
    

    
    public void BuscarProducto()
    {       
        int cod = Integer.parseInt(objFrmSistemaVenta.cboCodigoProducto.getSelectedItem().toString()), indice;
        ArrayList<Producto> inventarioProductos = objFrmSistemaVenta.objInventarioController.ObtenerProductosController();
        
        for(indice=0; indice<inventarioProductos.size() ;indice++)                  
            if(cod == inventarioProductos.get(indice).getCodigo())
                break;

            objFrmSistemaVenta.txtNombreProducto.setText(inventarioProductos.get(indice).getNombre());
            objFrmSistemaVenta.txtPrecioProducto.setText(df.format(inventarioProductos.get(indice).getPrecio()));
            objFrmSistemaVenta.lblStock.setText(inventarioProductos.get(indice).getStock() + "");
    }
    
    public void ActualizarStock()
    {           
        try
        {
            int cod, indice, nuevoStock;
            ArrayList<Producto> inventarioProductos = objFrmSistemaVenta.objInventarioController.ObtenerProductosController();
            String split[] = obtenerStocksInventario(inventarioProductos).split("\n");

            for(Producto objeto: listaProductos)
            {
                cod = objeto.getCodigo();
                       
                for(indice=0; indice<inventarioProductos.size() ;indice++)                  
                    if(cod == inventarioProductos.get(indice).getCodigo())
                    {
                        nuevoStock = inventarioProductos.get(indice).getStock() - objeto.getCantidad(); 
                    
                        split[indice] = nuevoStock + "";
                    }               
            }
            
            EstablecerStocksInventario(split);       
        }
        catch(Exception e)
        {
            System.out.println(e);
        }       
    }
    
    public String obtenerStocksInventario(ArrayList<Producto> inventarioProductos)
    {
        String stocks = "";
        
           for(Producto objeto: inventarioProductos)
           {
               stocks += objeto.getStock() + "\n";
           }
           
        return stocks;
    }
    
    public void EstablecerStocksInventario(String split[])
    {
        File Ffichero = null;
        
        try
        {
           Ffichero = new File("Stock.txt");
           BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ffichero)));
           Fescribe.write("");
                        
           for(int x = 0; x < split.length; x++)
                  Fescribe.write(split[x]+"\n");

           Fescribe.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
