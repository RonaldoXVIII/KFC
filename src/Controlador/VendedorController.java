
package Controlador;

import Modelo.VendedorModel;
import Vista.FrmLogin;

public class VendedorController
{
    VendedorModel objVendedorModel;

    public VendedorController(FrmLogin objFrmLogin)
    {
        objVendedorModel = new VendedorModel(objFrmLogin);
    }
}
