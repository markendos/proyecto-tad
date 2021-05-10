package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.TipoHabitacionDAO;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;

public class ControladorTipoHabitacion implements Controlador<TipoHabitacion> {
    
    TipoHabitacionDAO hotelDAO = new TipoHabitacionDAO();

    @Override
    public void add(T h) {
        hotelDAO.save(h);
    }

    @Override
    public void update(int id, String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<TipoHabitacion> getListado(){
        return hotelDAO.getAll();
    }
    
}
