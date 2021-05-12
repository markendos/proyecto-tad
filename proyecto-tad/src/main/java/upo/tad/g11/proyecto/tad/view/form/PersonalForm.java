package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.Personal;

public class PersonalForm extends FormLayout {

    BeanItemContainer<Hotel> hoteles;

    // Campos del formulario
    TextField nombre = new TextField("Nombre");
    TextField puesto = new TextField("Puesto");
    TextField salario = new TextField("Salario");
    TextField email = new TextField("Email");
    PasswordField password = new PasswordField("Contraseña");
    ComboBox hotel = new ComboBox("Hotel", hoteles);

    // Constructor por defecto
    public PersonalForm() {
        nombre.addValidator(new BeanValidator(Personal.class, "nombre"));
        puesto.addValidator(new BeanValidator(Personal.class, "puesto"));
        salario.addValidator(new BeanValidator(Personal.class, "salario"));
        email.addValidator(new BeanValidator(Personal.class, "email"));
        password.addValidator(new BeanValidator(Personal.class, "password"));
        hotel.addValidator(new BeanValidator(Personal.class, "hotel"));

        cargarHoteles();
        hotel.setContainerDataSource(hoteles);
        hotel.setItemCaptionPropertyId("nombre");

        setSpacing(true);
        addComponents(nombre, puesto, salario, email, password, hotel);
    }

    private void cargarHoteles() {
        hoteles = new BeanItemContainer<>(Hotel.class);
        Controlador c = new ControladorHotel();
        hoteles.addAll(c.listar());
    }
}
