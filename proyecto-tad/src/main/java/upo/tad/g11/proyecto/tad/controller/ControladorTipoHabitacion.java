package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.TipoHabitacionDAO;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;

public class ControladorTipoHabitacion implements Controlador<TipoHabitacion> {
    
    TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();

    @Override
    public void add(TipoHabitacion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TipoHabitacion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TipoHabitacion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoHabitacion get(TipoHabitacion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoHabitacion> listar() {
        return tipoHabitacionDAO.getAll();
    }
}
