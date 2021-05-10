package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import java.util.List;
import upo.tad.g11.proyecto.tad.controller.ControladorTipoHabitacion;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;

public class HabitacionForm extends FormLayout {

    // Campos del formulario
    TextField numero = new TextField("Nº de habitación");
    CheckBox fumador = new CheckBox("¿Fumador?");
    ComboBox hotel = new ComboBox("Hotel");
    ComboBox tipo = new ComboBox("Tipo de habitación");

    // Constructor por defecto
    public HabitacionForm() {
        hotel.addItems(getHoteles());
        tipo.addItems(getTipos());
        setSpacing(true);
        addComponents(numero, fumador, hotel, tipo);
    }

    private List<Hotel> getHoteles() {
        Controlador c = new ControladorHotel();
        return c.listar();
    }

    private List<TipoHabitacion> getTipos() {
        Controlador c = new ControladorTipoHabitacion();
        return c.listar();
    }
}
