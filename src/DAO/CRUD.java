
package DAO;

import java.util.List;

public interface CRUD<T>
{
    public List<T> Listar();
    public int Agregar(T t);
    public int Actualizar(T t);
    public void Eliminar(int id);
    public T Buscar(int id);
}
