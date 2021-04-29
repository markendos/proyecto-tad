/*
 * Esta interfaz define el contenido minimo del controlador
 */
package upo.tad.g11.proyecto.tad.controller;

/**
 *
 * @author Amalio
 */
interface Controlador<T> {

    public void add(String args[]);

    public void update(int id, String args[]);

    public void delete(int id);

    public T get(int id);

    public String listar();
}
