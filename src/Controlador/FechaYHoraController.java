
package Controlador;

import Modelo.FechaYHora;
import Modelo.FechaYHoraModel;
import Vista.FrmSistemaVenta;

public class FechaYHoraController 
{
    FechaYHoraModel objFechaYHoraModel;

    public FechaYHoraController(FrmSistemaVenta objFrmSistemaVenta)
    {
        objFechaYHoraModel = new FechaYHoraModel(objFrmSistemaVenta);
    }
    
    public void MostrarController()
    {
        objFechaYHoraModel.MostrarFechaYHora();
    }
    
    public FechaYHora ObtenerController()
    {
        return objFechaYHoraModel.getObjFechaYHora();
    }    
}
