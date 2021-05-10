/*
 * Esta interfaz define el contenido minimo del controlador
 */
package upo.tad.g11.proyecto.tad.controller;

import java.util.List;

/**
 *
 * @author Amalio
 */
interface Controlador<T> {

    public void add(T t);

    public void update(T t);

    public void delete(T t);

    public T get(T t);

    public List<T> listar();
}
