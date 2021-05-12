package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.model.entity.Evento;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

public class EventoForm extends FormLayout {

    BeanItemContainer<Hotel> hoteles;

    // Campos del formulario
    TextField nombre = new TextField("Nombre");
    TextField descripcion = new TextField("Descripción");
    DateField fecha = new DateField("Fecha");
    ComboBox hotel = new ComboBox("Hotel", hoteles);

    // Constructor por defecto
    public EventoForm() {
        
        nombre.addValidator(new BeanValidator(Evento.class, "nombre"));
        descripcion.addValidator(new BeanValidator(Evento.class, "descripcion"));
        fecha.addValidator(new BeanValidator(Evento.class, "fecha"));
        hotel.addValidator(new BeanValidator(Evento.class, "hotel"));
        
        cargarHoteles();
        hotel.setContainerDataSource(hoteles);
        hotel.setItemCaptionPropertyId("nombre");
        
        setSpacing(true);
        addComponents(nombre, descripcion, fecha, hotel);
    }

    private void cargarHoteles() {
        hoteles = new BeanItemContainer<>(Hotel.class);
        Controlador c = new ControladorHotel();
        hoteles.addAll(c.listar());
    }
}
