
package Modelo;

import DAO.DAOAdministrador;
import DAO.DAOAdministradorImpl;
import Vista.FrmAdministrador;
import Vista.FrmLogin;
import Vista.FrmRegistro;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdministradorModel 
{
    Administrador a;
    FrmLogin objFrmLogin;
    FrmAdministrador objFrmAdministrador;
    DAOAdministrador dao;

    public AdministradorModel(FrmLogin objFrmLogin) 
    {
        this.objFrmLogin = objFrmLogin;
        this.objFrmAdministrador = objFrmLogin.objFrmAdministrador;
        dao = new DAOAdministradorImpl();
    }
    
    public void ArgegarAministrador()
    {
        a = new Administrador();

        a.setNombre(objFrmLogin.objFrmRegistro.txtNombre.getText());
        a.setApellidoPaterno(objFrmLogin.objFrmRegistro.txtApellPat.getText());
        a.setApellidoMaterno(objFrmLogin.objFrmRegistro.txtApellMat.getText());
        a.setDni(objFrmLogin.objFrmRegistro.txtDNI.getText());
        a.setTelefono(objFrmLogin.objFrmRegistro.txtTelefono.getText());
        a.setDistrito(objFrmLogin.objFrmRegistro.txtDistrito.getText());
        a.setCalle(objFrmLogin.objFrmRegistro.txtCalle.getText());
        a.setNumero(Integer.parseInt(objFrmLogin.objFrmRegistro.txtNumero.getText()));
        a.setNombreUsuario(objFrmLogin.objFrmRegistro.txtNombreUsuario.getText());
        a.setClaveUsuario(objFrmLogin.objFrmRegistro.pswContraseñaUsuario.getText());
        
        int r = dao.Agregar(a);

        if(r == 1)
            JOptionPane.showMessageDialog(null,"Se registró el Aministrador con Éxito!");
        else
            JOptionPane.showMessageDialog(null,"ERROR, no se pudo agregar el autor");

      //  objEpocaModel.ActualizarCboEpocas();
        
        objFrmLogin.objFrmRegistro.txtNombre.setText("");
        objFrmLogin.objFrmRegistro.txtApellPat.setText("");
        objFrmLogin.objFrmRegistro.txtApellMat.setText("");
        objFrmLogin.objFrmRegistro.txtDNI.setText("");
        objFrmLogin.objFrmRegistro.txtTelefono.setText("");
        objFrmLogin.objFrmRegistro.txtDistrito.setText("");
        objFrmLogin.objFrmRegistro.txtCalle.setText("");
        objFrmLogin.objFrmRegistro.txtNumero.setText("");
        objFrmLogin.objFrmRegistro.txtNombreUsuario.setText("");
        objFrmLogin.objFrmRegistro.pswContraseñaUsuario.setText("");  
    }
    
    public void ListarAdministradores()
    {
        objFrmAdministrador.modelo = (DefaultTableModel)objFrmAdministrador.tablaAdm.getModel();
        objFrmAdministrador.modelo.setRowCount(0);

        List<Administrador> lista = dao.Listar();
        Object[] object = new Object[9];

        for(int i=0; i<lista.size(); i++)
        {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getApellidoPaterno();
            object[3] = lista.get(i).getApellidoMaterno();
            object[4] = lista.get(i).getDni();
            object[5] = lista.get(i).getTelefono();
            object[6] = lista.get(i).getDistrito();
            object[7] = lista.get(i).getCalle();
            object[8] = lista.get(i).getNumero();
            objFrmAdministrador.modelo.addRow(object);
        }
    }   
    
    public void Actualizar()
    {
        a = new Administrador();

        a.setId(Integer.parseInt(objFrmAdministrador.txtIDAdm.getText()));
        a.setNombre(objFrmAdministrador.txtNombreAdm.getText());
        a.setApellidoPaterno(objFrmAdministrador.txtApellPatAdm.getText());
        a.setApellidoMaterno(objFrmAdministrador.txtApellMatAdm.getText());
        a.setDni(objFrmAdministrador.txtDNIAdm.getText());
        a.setTelefono(objFrmAdministrador.txtTelefonoAdm.getText());
        a.setDistrito(objFrmAdministrador.txtDistritoAdm.getText());
        a.setCalle(objFrmAdministrador.txtCalleAdm.getText());
        a.setNumero(Integer.parseInt(objFrmAdministrador.txtNAdm.getText()));
        
        int r = dao.Actualizar(a);

        if(r == 1)
            JOptionPane.showMessageDialog(null,"Se actualizó el Administrador con éxito!");
        else
            JOptionPane.showMessageDialog(null,"ERROR, no se pudo actualizar el Administrador");

      //  objEpocaModel.ActualizarCboEpocas();
        
        Limpiar();
    }
    
    public void Eliminar()
    {
        dao.Eliminar(Integer.parseInt(objFrmAdministrador.txtIDAdm.getText()));
        objFrmAdministrador.txtIDAdm.setText("");
        JOptionPane.showMessageDialog(null,"Se eliminó el Administrador con éxito!");
    }
    
    public void BuscarAdm()
    {
        int idAdm = Integer.parseInt(objFrmAdministrador.txtIDAdm.getText());
        a = dao.Buscar(idAdm);
        
        if(a.getNombre() != null)
        {
            objFrmAdministrador.txtNombreAdm.setText(a.getNombre());
            objFrmAdministrador.txtApellPatAdm.setText(a.getApellidoPaterno());
            objFrmAdministrador.txtApellMatAdm.setText(a.getApellidoMaterno());
            objFrmAdministrador.txtDNIAdm.setText(a.getDni());
            objFrmAdministrador.txtTelefonoAdm.setText(a.getTelefono());
            objFrmAdministrador.txtDistritoAdm.setText(a.getDistrito());
            objFrmAdministrador.txtCalleAdm.setText(a.getCalle());
            objFrmAdministrador.txtNAdm.setText(a.getNumero() + "");
        }
        else
            JOptionPane.showMessageDialog(null, "El ID del Administrador no existe!!");

    }    
    
    public void Limpiar()
    {
        objFrmAdministrador.txtNombreAdm.setText("");
        objFrmAdministrador.txtApellPatAdm.setText("");
        objFrmAdministrador.txtApellMatAdm.setText("");
        objFrmAdministrador.txtDNIAdm.setText("");
        objFrmAdministrador.txtTelefonoAdm.setText("");
        objFrmAdministrador.txtDistritoAdm.setText("");
        objFrmAdministrador.txtCalleAdm.setText("");
        objFrmAdministrador.txtNAdm.setText(""); 
    }
}
