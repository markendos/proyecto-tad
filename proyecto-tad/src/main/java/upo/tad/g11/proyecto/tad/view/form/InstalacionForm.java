package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.Instalacion;

public class InstalacionForm extends FormLayout {

    BeanItemContainer<Hotel> hoteles;

    // Campos del formulario
    TextField nombre = new TextField("Nombre");
    TextField tipo = new TextField("Tipo");
    TextField aforo = new TextField("Aforo");
    ComboBox hotel = new ComboBox("Hotel", hoteles);

    // Constructor por defecto
    public InstalacionForm() {
        
        nombre.addValidator(new BeanValidator(Instalacion.class, "nombre"));
        tipo.addValidator(new BeanValidator(Instalacion.class, "tipo"));
        hotel.addValidator(new BeanValidator(Instalacion.class, "hotel"));
        
        cargarHoteles();
        hotel.setContainerDataSource(hoteles);
        hotel.setItemCaptionPropertyId("nombre");
        
        setSpacing(true);
        addComponents(nombre, tipo, aforo, hotel);
    }

    private void cargarHoteles() {
        hoteles = new BeanItemContainer<>(Hotel.class);
        Controlador c = new ControladorHotel();
        hoteles.addAll(c.listar());
    }
}
