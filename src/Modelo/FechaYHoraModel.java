
package Modelo;

import Vista.FrmSistemaVenta;

public class FechaYHoraModel implements Runnable
{
    FechaYHora objFechaYHora;
    FrmSistemaVenta objFrmSistemaVenta;
    Thread hilo;

    public FechaYHoraModel(FrmSistemaVenta objFrmSistemaVenta)
    {
        this.objFrmSistemaVenta = objFrmSistemaVenta;
    }
    
    public void MostrarFechaYHora()
    {      
        hilo = new Thread(this);
        hilo.start();      
    }
    
    @Override
    public void run()
    {
        Thread courrent = Thread.currentThread();
        
        while(courrent == hilo)
        {
            objFechaYHora = new FechaYHora();
            objFrmSistemaVenta.lblFechaYHora.setText(objFechaYHora.toString()); 
        }                
    }

    public FechaYHora getObjFechaYHora() 
    {
        return objFechaYHora;
    }
    
}
