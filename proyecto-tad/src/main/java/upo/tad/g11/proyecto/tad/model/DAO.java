/**
 * En esta interfaz se detallan las acciones minimas que debe realizar un objeto DAO
 * 
 */
package upo.tad.g11.proyecto.tad.model;

import java.util.List;

/**
 *
 * @author Amalio
 * @param <T>
 */
public interface DAO<T> {

    public T get(int id);

    public List<T> getAll();

    public void save(T t);

    public void update(T t);

    public void delete(int id);
}
