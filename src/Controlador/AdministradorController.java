
package Controlador;

import Modelo.AdministradorModel;
import Vista.FrmLogin;

public class AdministradorController
{
    AdministradorModel objAdministradorModel;

    public AdministradorController(FrmLogin objFrmLogin)
    {
        objAdministradorModel = new AdministradorModel(objFrmLogin);
    }
 
    public void AgregarController()
    {
        objAdministradorModel.ArgegarAministrador();
    }
    
    public void ListarController()
    {
        objAdministradorModel.ListarAdministradores();
    }
    
    public void ActualizarController()
    {
        objAdministradorModel.Actualizar();
    }
    
    public void EliminarController()
    {
        objAdministradorModel.Eliminar();
    }
    
    public void Buscar()
    {
        objAdministradorModel.BuscarAdm();
    }
    
    public void Limpiar()
    {
        objAdministradorModel.Limpiar();
    }
}
