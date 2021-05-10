package upo.tad.g11.proyecto.tad.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Date;
import upo.tad.g11.proyecto.tad.model.entity.Cliente;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;
import upo.tad.g11.proyecto.tad.view.form.ClienteForm;
import upo.tad.g11.proyecto.tad.view.form.ReservaForm;

/**
 *
 * @author Grupo XI
 */
@Theme("mytheme")
@Title("Clientes")
public class GestionClientes extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Obtenemos la sesion HTTP del usuario actual.
        WrappedSession session = getSession().getSession();

        // Creamos el layout principal de la UI.
        HorizontalLayout layout = new HorizontalLayout();

        /*------------------------FORMULARIO (create)-------------------------*/
        // Layout que servira de contenedor para los componentes del formulario.
        VerticalLayout vLayoutForm = new VerticalLayout();

        /* Creamos un objeto que servira para envolver los valores de los campos
        ** del formulario de nuestra entidad CRUD. Asignamos tambien los valores
        ** por defecto de los campos y el tipo de dato que se espera recibir.
         */
        PropertysetItem itemCliente = new PropertysetItem();
        itemCliente.addItemProperty("dni", new ObjectProperty("", String.class));
        itemCliente.addItemProperty("name", new ObjectProperty("", String.class));
        itemCliente.addItemProperty("email", new ObjectProperty("", String.class));
        itemCliente.addItemProperty("telefono", new ObjectProperty("", String.class));

        PropertysetItem itemReserva = new PropertysetItem();
        itemReserva.addItemProperty("fechaLlegada", new ObjectProperty(null, Date.class));
        itemReserva.addItemProperty("fechaSalida", new ObjectProperty(null, Date.class));
        itemReserva.addItemProperty("hotel", new ObjectProperty(null, Hotel.class));
        itemReserva.addItemProperty("tipo", new ObjectProperty(null, TipoHabitacion.class));

        // Instanciamos un nuevo componente que contendra los campos del formulario
        ClienteForm formCliente = new ClienteForm();
        ReservaForm formReserva = new ReservaForm();

        // Realizamos el binding del formulario entre la UI y el modelo asociado.
        FieldGroup binderCliente = new FieldGroup(itemCliente);
        binderCliente.bindMemberFields(formCliente);

        FieldGroup binderReserva = new FieldGroup(itemReserva);
        binderReserva.bindMemberFields(formReserva);

        // Creamos un boton para enviar el formulario y lo anyadimos a este
        Button crearBtn = new Button("Crear");
        crearBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        formCliente.addComponent(crearBtn);

        // Boton para volver al menu
        Button menuBtn = new Button("Menú");
        menuBtn.addStyleName(ValoTheme.BUTTON_LINK);

        // Al hacer click, se vuelve al menu principal
        menuBtn.addClickListener(e
                -> {
            UI.getCurrent().getPage().setLocation("/menu");
        }
        );

        vLayoutForm.addComponents(menuBtn, formCliente);
        vLayoutForm.addComponents(formReserva);

        /*----------------------(END)FORMULARIO (create)-----------------------*/

 /*--------------------TABLA (read, update, delete)--------------------*/
        // Layout que servira de contenedor para la tabla de la entidad.
        VerticalLayout vLayoutTable = new VerticalLayout();

        //layoutBotones.setMargin(true);
        // Contenedor para almacenar los beans de la entidad CRUD.
        BeanItemContainer<Cliente> beans
                = new BeanItemContainer<>(Cliente.class);

        //beans.addNestedContainerProperty("tipo.nombre");
        // Creamos la tabla y le asociamos el contenedor creado enteriormente.
        Table tableCliente = new Table("Clientes", beans);

        // Establecemos las propiedades de la tabla para obtener el
        // comportamiento deseado.
        tableCliente.setEditable(false);
        tableCliente.setSelectable(true);
        tableCliente.setImmediate(true);
        tableCliente.setColumnReorderingAllowed(true);
        tableCliente.setSizeFull();
        tableCliente.setPageLength(tableCliente.size());
        tableCliente.setColumnHeader("dni", "DNI");
        tableCliente.setColumnHeader("name", "Nombre");
        tableCliente.setColumnHeader("email", "Correo Electronico");
        tableCliente.setColumnHeader("telefono", "Telefono");
        tableCliente.setVisibleColumns("dni", "name", "email", "telefono");

        // Anyadimos los componentes de control para realizar las acciones de
        // editar y eliminar sobre los elementos de la tabla.
        CheckBox editable = new CheckBox("Editar");
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.addStyleName(ValoTheme.BUTTON_DANGER);
        btnEliminar.setEnabled(false);

        //Lsteners para los elementos interactivos de la tabla:
        // Habilita el boton de eliminar al seleccionar una fila de la tabla.
        tableCliente.addListener((ItemClickEvent.ItemClickListener) (ItemClickEvent event) -> {

            // Returns the row selected on table
            Object itemId = tableCliente.getValue();
            // get the index of the item selected
            BeanItem<Cliente> bean = beans.getItem(itemId);
            if (bean != null) {
                Cliente c = bean.getBean();
                System.out.println(c);
                System.out.println(c);
                System.out.println(c);
                System.out.println(c);
            }

            btnEliminar.setEnabled(true);
        });

        // Elimina el elemento de la fila seleccionada al pulsa el boton de eliminar.
        btnEliminar.addClickListener((Button.ClickEvent event) -> {
            beans.removeItem(tableCliente.getValue());
            btnEliminar.setEnabled(false);
        });

        tableCliente.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    // Returns the row selected on table
                    Object itemId = tableCliente.getValue();
                    // get the index of the item selected
                    BeanItem<Cliente> bean = beans.getItem(itemId);
                    if (bean != null) {
                        Cliente c = bean.getBean();
                        System.out.println(c);
                        System.out.println(c);
                        System.out.println(c);
                        System.out.println(c);
                    }
                }
        );

        // Activa/desactiva el modo de edicion sobre la tabla.
        editable.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    Boolean checked = (Boolean) event.getProperty().getValue();
                    tableCliente.setEditable(checked);
                }
        );

        // Mapea los valores de los campos del formulario a una nueva instancia de
        // la entidad CRUD y la anyade al contenedor de beans.
        crearBtn.addClickListener(e
                -> {
            String dni = (String) binderCliente.getField("dni").getValue();
            String name = (String) binderCliente.getField("name").getValue();
            String email = (String) binderCliente.getField("email").getValue();
            String telefono = (String) binderCliente.getField("telefono").getValue();

            Cliente c = new Cliente(dni, name, email, telefono);
            beans.addBean(c);
        }
        );

        // Boton para realizar la accion de logout
        Button cerrarSesionBtn = new Button("Cerrar Sesión");
        cerrarSesionBtn.addStyleName(ValoTheme.BUTTON_SMALL);

        // Al hacer click, se invalida la sesion actual y se redirecciona a la
        // pagina de login
        cerrarSesionBtn.addClickListener(e
                -> {
            session.invalidate();
            UI.getCurrent().getPage().setLocation("/");
        }
        );

        // Anyadimos los componetes al layout de la tabla y se aplican los estilos.
        vLayoutTable.addComponents(cerrarSesionBtn, tableCliente, editable, btnEliminar);
        vLayoutTable.setSpacing(true);

        /*------------------(END)TABLA (read, update, delete)------------------*/
        // Anyadimos los componentes que contienen los layouts del formulario y
        // la tabla al layout principal de la UI. Se aplican tambian los estilos.
        layout.addComponents(vLayoutForm, vLayoutTable);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        setContent(layout);
    }

    @WebServlet(value = {"/clientes/*"}, name = "GestionClientes", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = GestionClientes.class)
    public static class GestionHotelesServlet extends VaadinServlet {
    }
}
