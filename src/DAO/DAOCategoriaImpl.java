/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Categoria;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class DAOCategoriaImpl extends Conexion implements DAOCategoria{
    ResultSet rs;
    
    
      public Vector<Categoria> mostrarCategoria()
    {
        Vector<Categoria> vectorCategorias = new Vector<Categoria>();
        
        Categoria categoria = null;
        
        try {
            conectar();
               this.conectar();
            PreparedStatement ps = (PreparedStatement) this.cn.prepareStatement("select * from categoría;");
            rs = ps.executeQuery();
            categoria = new Categoria();
            categoria.setId(0);
            categoria.setNombreCategoria("Seleccionar Categoria");
            vectorCategorias.add(categoria);
           
            
            while(rs.next())
            {
                categoria = new Categoria();
                categoria.setId(rs.getInt(1));
                categoria.setNombreCategoria(rs.getString(2));
                vectorCategorias.add(categoria);
                
            }          
            this.cerrar();
            
            
        } catch (Exception e) {
            
            
        }
        
        return vectorCategorias;
    }
    
}
