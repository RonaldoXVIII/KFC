
package DAO;
import java.sql.*;

public class Conexion 
{
    protected Connection cn;
    private final String jdbc_driver = "com.mysql.cj.jdbc.Driver"; 
    private final String urlXamp = "jdbc:mysql://localhost/tienda_KFC";
    private final String urlWorkBench = "jdbc:mysql://localhost:3306/tienda_KFC";
    private final String user = "root";
    private final String passwordXamp = "";
    private final String passwordWorkBench = "1234";
    
    public void conectar() throws Exception
    {
        try
        {
            cn = DriverManager.getConnection(urlXamp,user,passwordXamp);        
            Class.forName(jdbc_driver);                 
        }
        catch(Exception e)
        {   
            System.out.println("Error no se puede cargar el driver: " + e.getMessage());
        }
    }
    
    public void cerrar() throws SQLException
    {
        if(cn != null)
        {
            if(!cn.isClosed())
                cn.close();
        }
    }
}
