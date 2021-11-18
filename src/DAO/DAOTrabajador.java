
package DAO;

import Modelo.Trabajador;

public interface DAOTrabajador 
{
    public Trabajador Buscar(String nombreUsuario, String Contraseña);
}
