package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import java.util.List;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorCliente;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.controller.ControladorTipoHabitacion;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;

public class ReservaForm extends FormLayout {

    // Campos del formulario
    DateField fechaLlegada = new DateField("Fecha de Llegada");
    DateField fechaSalida = new DateField("Fecha de Salida");
    ComboBox tipo = new ComboBox("Tipo de habitación");
    ComboBox hotel = new ComboBox("Hotel");

    // Constructor por defecto
    public ReservaForm() {
        //hotel.addItems(getHoteles());
        tipo.addItems(getTipos());
        setSpacing(true);
        addComponents(fechaLlegada, fechaSalida, tipo,hotel);
    }

    private List<Hotel> getReservas() {
        Controlador c = new ControladorCliente();
        return c.listar();
    }

    private List<TipoHabitacion> getTipos() {
        Controlador c = new ControladorTipoHabitacion();
        return c.listar();
    }
    
    private List<Hotel> getHoteles() {
        Controlador c = new ControladorHotel();
        return c.listar();
    }
}
