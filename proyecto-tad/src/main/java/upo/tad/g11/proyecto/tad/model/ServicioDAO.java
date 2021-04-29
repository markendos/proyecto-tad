/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.g11.proyecto.tad.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import modelo.DTO.ServicioDTO;
import org.bson.Document;

/**
 * Este DAO se encarga de aplicar las operaciones de los clientes
 *
 *
 * @author Amalio
 */
public class ServicioDAO implements DAO<ServicioDTO> {

    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private Connection con;
    private MongoCollection<Document> col;

    /**
     * Constructor que inicializa la coleccion
     *
     * @param c
     */
    public ServicioDAO(Connection c) {
        this.con = c;
        this.col = con.db.getCollection("Servicios");
    }

    /**
     * Metodo que recupera un Documento de la coleccion segun el id pasado por
     * parametro
     *
     * @param id
     * @return
     */
    @Override
    public ServicioDTO get(int id) {

        Document doc = (Document) this.col.find(Filters.eq("id", id)).first();
        ServicioDTO servicio = null;
        if (doc != null) {
            servicio = new ServicioDTO(
                    doc.getInteger("id"),
                    doc.getString("nombre"),
                    doc.getString("descripcion"),
                    doc.getString("horario"),
                    doc.getDouble("tarifa")
            );
        }
        return servicio;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<ServicioDTO> getAll() {
        List<ServicioDTO> lista = new ArrayList<>();
        for (Document doc : this.col.find()) {
            lista.add(new ServicioDTO(
                    doc.getInteger("id"),
                    doc.getString("nombre"),
                    doc.getString("descripcion"),
                    doc.getString("horario"),
                    doc.getDouble("tarifa")));
        }

        return lista;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(ServicioDTO t) {
        this.col.insertOne(t.toDocument());
    }

    /**
     * Metodo que actualiza un documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void update(ServicioDTO t) {
        this.col.updateOne(Filters.eq("id", t.getId()), new Document("$set", t.toDocument()));
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        this.col.deleteOne(Filters.eq("id", id));
    }

}
