package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import upo.tad.g11.proyecto.tad.controller.ControladorTipoHabitacion;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.model.entity.Habitacion;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;

public class HabitacionForm extends FormLayout {

    BeanItemContainer<Hotel> hoteles;
    BeanItemContainer<TipoHabitacion> tipos;

    // Campos del formulario
    TextField numero = new TextField("Nº de habitación");
    CheckBox fumador = new CheckBox("¿Fumador?");
    ComboBox tipo = new ComboBox("Tipo de habitación", tipos);
    ComboBox hotel = new ComboBox("Hotel", hoteles);

    // Constructor por defecto
    public HabitacionForm() {
        tipo.addValidator(new BeanValidator(Habitacion.class, "tipo"));
        hotel.addValidator(new BeanValidator(Habitacion.class, "hotel"));

        cargarHoteles();
        hotel.setContainerDataSource(hoteles);
        hotel.setItemCaptionPropertyId("nombre");

        cargarTipos();
        tipo.setContainerDataSource(tipos);
        tipo.setItemCaptionPropertyId("nombre");

        setSpacing(true);
        addComponents(numero, fumador, tipo, hotel);
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
