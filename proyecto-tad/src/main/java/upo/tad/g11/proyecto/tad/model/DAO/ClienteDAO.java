package upo.tad.g11.proyecto.tad.model.DAO;

import dev.morphia.Datastore;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.entity.Cliente;

public class ClienteDAO implements DAO<Cliente> {

    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private final Datastore d;

    /**
     * Constructor que inicializa la coleccion
     *
     */
    public ClienteDAO() {
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
    public Cliente get(Object id) {
        Cliente cliente = d.createQuery(Cliente.class)
                .field("_id")
                .equal(id).first();

        return cliente;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes
                = d.createQuery(Cliente.class).find().toList();
        return clientes;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(Cliente t) {
        d.save(t);
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param t
     */
    @Override
    public void delete(Cliente t) {
        d.delete(t);
    }

}
