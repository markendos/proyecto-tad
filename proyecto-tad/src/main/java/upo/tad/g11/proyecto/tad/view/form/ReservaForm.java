package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.util.BeanItemContainer;
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

    BeanItemContainer<Hotel> hoteles;
    BeanItemContainer<TipoHabitacion> tipos;

    // Campos del formulario
    DateField fechaLlegada = new DateField("Fecha de Llegada");
    DateField fechaSalida = new DateField("Fecha de Salida");
    ComboBox hotel = new ComboBox("Hotel", hoteles);
    ComboBox tipo = new ComboBox("Tipo de habitación", tipos);

    // Constructor por defecto
    public ReservaForm() {
        cargarHoteles();
        hotel.setContainerDataSource(hoteles);
        hotel.setItemCaptionPropertyId("nombre");

        cargarTipos();
        tipo.setContainerDataSource(tipos);
        tipo.setItemCaptionPropertyId("nombre");

        setSpacing(true);
        addComponents(fechaLlegada, fechaSalida, tipo, hotel);
    }

    private void cargarHoteles() {
        hoteles = new BeanItemContainer<>(Hotel.class);
        Controlador c = new ControladorHotel();
        hoteles.addAll(c.listar());
    }

    private void cargarTipos() {
        tipos = new BeanItemContainer<>(TipoHabitacion.class);
        Controlador c = new ControladorTipoHabitacion();
        tipos.addAll(c.listar());
    }
}
