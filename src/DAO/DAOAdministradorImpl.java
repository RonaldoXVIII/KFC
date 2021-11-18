
package DAO;

import Modelo.Administrador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOAdministradorImpl extends Conexion implements DAOAdministrador
{
    ResultSet rs;
    
    @Override
    public List<Administrador> Listar() 
    {
        List<Administrador> datos = new ArrayList<>();
               
        try
        {
            this.conectar();
            PreparedStatement ps = this.cn.prepareStatement("select * from Trabajador where fk_idTipoDeTrabajador=1;");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Administrador a = new Administrador();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setApellidoPaterno(rs.getString(3));
                a.setApellidoMaterno(rs.getString(4));
                a.setDni(rs.getString(5));
                a.setTelefono(rs.getString(6));
                a.setDistrito(rs.getString(7));
                a.setCalle(rs.getString(8));
                a.setNumero(rs.getInt(9));                
                datos.add(a);
            }          
            this.cerrar();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return datos;
    }

    @Override
    public int Agregar(Administrador a) 
    {
        try
        {
            this.conectar();
            PreparedStatement ps = this.cn.prepareStatement("insert into Trabajador values(NULL,?,?,?,?,?,?,?,?,?,?,1)");
          //  ps.setInt(1, 0);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellidoPaterno());
            ps.setString(3, a.getApellidoMaterno());
            ps.setString(4, a.getDni());
            ps.setString(5, a.getTelefono());
            ps.setString(6, a.getDistrito());
            ps.setString(7, a.getCalle());
            ps.setInt(8, a.getNumero());
            ps.setString(9, a.getNombreUsuario());
            ps.setString(10, a.getClaveUsuario());          
            ps.executeUpdate();
            this.cerrar();
        }
        catch(Exception e)
        {        
            System.out.println(e.getMessage());
        }
        
        return 1;
    }

    @Override
    public int Actualizar(Administrador a) 
    {
        try
        {
            this.conectar();
            PreparedStatement ps = this.cn.prepareStatement("update Trabajador set nombre=?, apellidoPaterno=?, apellidoMaterno=?, dni=?, telefono=?, distrito=?, calle=?, numero=? where id=?");
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellidoPaterno());
            ps.setString(3, a.getApellidoMaterno());
            ps.setString(4, a.getDni());
            ps.setString(5, a.getTelefono());
            ps.setString(6, a.getDistrito());
            ps.setString(7, a.getCalle());
            ps.setInt(8, a.getNumero());
            ps.setInt(9, a.getId());
            ps.executeUpdate();
            this.cerrar();
        }
        catch(Exception ex)
        {        
            System.out.println(ex.getMessage());
        }
        
        return 1;
    }

    @Override
    public void Eliminar(int id) 
    {
        try
        {
            this.conectar();
            PreparedStatement ps = this.cn.prepareStatement("delete from Trabajador where id=" + id);
            ps.executeUpdate();
            this.cerrar();
        }
        catch(Exception e)
        {        
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Administrador Buscar(int id) 
    {
        Administrador a = new Administrador();
        
        try
        {
            this.conectar();
            PreparedStatement ps = this.cn.prepareStatement("select id, nombre, apellidoPaterno, apellidoMaterno, dni, telefono, distrito, calle, numero from Trabajador where id=" + id);
            rs = ps.executeQuery();
            
            if(rs.next())
            {   
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setApellidoPaterno(rs.getString(3));
                a.setApellidoMaterno(rs.getString(4));
                a.setDni(rs.getString(5));
                a.setTelefono(rs.getString(6));
                a.setDistrito(rs.getString(7));
                a.setCalle(rs.getString(8));
                a.setNumero(rs.getInt(9)); 
            }
            this.cerrar();
        }
        catch(Exception ex)
        {        
            System.out.println(ex.getMessage());
        }
        
        return a;
    }  
}
