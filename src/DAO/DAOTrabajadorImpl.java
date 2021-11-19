
package DAO;

import Modelo.Trabajador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOTrabajadorImpl extends Conexion implements DAOTrabajador 
{
    ResultSet rs;
    
    @Override
    public Trabajador Buscar(String nombreUsuario, String Contraseña) 
    {
        Trabajador t = new Trabajador();
        
        try
        {
            this.conectar();
            PreparedStatement ps = this.cn.prepareStatement("select fk_idTipoDeTrabajador,nombre from Trabajador where usuario=? and contraseña=?");
            ps.setString(1, nombreUsuario);
            ps.setString(2, Contraseña);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {   
                t.idtipoTrabajador = rs.getInt(1);
                t.nombre = rs.getString(2);
            }
            this.cerrar();
        }
        catch(Exception ex)
        {        
            System.out.println(ex.getMessage());
        }
        
        return t;
    }
    
}
