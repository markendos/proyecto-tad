package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

public class ServicioForm extends FormLayout {

    BeanItemContainer<Hotel> hoteles;

    // Campos del formulario
    TextField nombre = new TextField("Nombre");
    TextField descripcion = new TextField("Descripción");
    TextField horario = new TextField("Horario");
    TextField tarifa = new TextField("Tarifa");
    ComboBox hotel = new ComboBox("Hotel", hoteles);

    // Constructor por defecto
    public ServicioForm() {
        cargarHoteles();
        hotel.setContainerDataSource(hoteles);
        hotel.setItemCaptionPropertyId("nombre");
        
        setSpacing(true);
        addComponents(nombre, descripcion, horario, tarifa, hotel);
    }

    private void cargarHoteles() {
        hoteles = new BeanItemContainer<>(Hotel.class);
        Controlador c = new ControladorHotel();
        hoteles.addAll(c.listar());
    }
}
