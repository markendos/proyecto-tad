package upo.tad.g11.proyecto.tad.model.DAO;

import dev.morphia.Datastore;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.entity.Instalacion;

public class InstalacionDAO implements DAO<Instalacion> {
    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private final Datastore d;

    /**
     * Constructor que inicializa la coleccion
     *
     */
    public InstalacionDAO() {
        d = Connection.getConnection();
    }

    /**
     * Metodo que recupera un Documento de la coleccion segun el id pasado por
     * parametro
     *
     * @param id
     * @return
     */
    @Override
    public Instalacion get(Object id) {
        Instalacion instalacion = d.createQuery(Instalacion.class)
                .field("_id")
                .equal(id).first();

        return instalacion;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<Instalacion> getAll() {
        List<Instalacion> instalaciones
                = d.createQuery(Instalacion.class).find().toList();
        return instalaciones;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(Instalacion t) {
        d.save(t);
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param t
     */
    @Override
    public void delete(Instalacion t) {
        d.delete(t);
    }

    @Override
    public void saveAll(List<Instalacion> t) {
        d.save(t);
    }
}
