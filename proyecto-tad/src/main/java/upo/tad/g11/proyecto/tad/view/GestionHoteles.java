package upo.tad.g11.proyecto.tad.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
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
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Iterator;
import org.bson.types.ObjectId;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.model.entity.Habitacion;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;
import upo.tad.g11.proyecto.tad.view.form.HabitacionForm;
import upo.tad.g11.proyecto.tad.view.form.HotelForm;

@Theme("mytheme")
@Title("Hoteles")
public class GestionHoteles extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Obtenemos la sesion HTTP del usuario actual.
        WrappedSession session = getSession().getSession();
        ControladorHotel cH = new ControladorHotel();
        if (session.getAttribute("usuario") == null) {     //En caso de no existir una sesión activa, redirigimos al login
            UI.getCurrent().getPage().setLocation("/");
        }

        // Creamos el layout principal de la UI.
        HorizontalLayout layout = new HorizontalLayout();

        /*------------------------FORMULARIO (create)-------------------------*/
        // Layout que servira de contenedor para los componentes del formulario.
        VerticalLayout vLayoutForm = new VerticalLayout();

        /* Creamos un objeto que servira para envolver los valores de los campos
        ** del formulario de nuestra entidad CRUD. Asignamos tambien los valores
        ** por defecto de los campos y el tipo de dato que se espera recibir.
         */
        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("id", new ObjectProperty("", String.class));
        item.addItemProperty("nombre", new ObjectProperty("", String.class));
        item.addItemProperty("ubicacion", new ObjectProperty("", String.class));
        item.addItemProperty("calidad", new ObjectProperty("", String.class));

        // Instanciamos un nuevo componente que contendra los campos del formulario
        HotelForm form = new HotelForm();

        // Realizamos el binding del formulario entre la UI y el modelo asociado.
        FieldGroup binder = new FieldGroup(item);
        binder.bindMemberFields(form);

        // Creamos un boton para enviar el formulario y lo anyadimos a este
        Button crearBtn = new Button("Crear");
        crearBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        form.addComponent(crearBtn);

        vLayoutForm.addComponents(form);

        /*----------------------(END)FORMULARIO (create)-----------------------*/

        /*--------------------TABLA (read, update, delete)--------------------*/
        // Layout que servira de contenedor para la tabla de la entidad.
        VerticalLayout vLayoutTable = new VerticalLayout();

        // Contenedor para almacenar los beans de la entidad CRUD.
        BeanItemContainer<Hotel> beans
                = new BeanItemContainer<>(Hotel.class);
        

        // Creamos la tabla y le asociamos el contenedor creado enteriormente.
        Table table = new Table("Hoteles", beans);

        // Establecemos las propiedades de la tabla para obtener el
        // comportamiento deseado.
        table.setEditable(false);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setColumnReorderingAllowed(true);
        table.setSizeFull();
        table.setPageLength(table.size());
        table.setColumnHeader("id", "ID del hotel");
        table.setColumnHeader("nombre", "Nombre");
        table.setColumnHeader("ubicacion", "Ubicación");
        table.setColumnHeader("calidad", "Calidad");
        table.setVisibleColumns("id", "nombre", "ubicacion", "calidad");

        // Anyadimos los componentes de control para realizar las acciones de
        // editar y eliminar sobre los elementos de la tabla.
        CheckBox editable = new CheckBox("Editar");
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.addStyleName(ValoTheme.BUTTON_DANGER);
        btnEliminar.setEnabled(false);

        //Lsteners para los elementos interactivos de la tabla:
        // Habilita el boton de eliminar al seleccionar una fila de la tabla.
        table.addListener((ItemClickEvent.ItemClickListener) (ItemClickEvent event) -> {
            btnEliminar.setEnabled(true);
        });

        // Elimina el elemento de la fila seleccionada al pulsa el boton de eliminar.
        btnEliminar.addClickListener((Button.ClickEvent event) -> {
            beans.removeItem(table.getValue());
            btnEliminar.setEnabled(false);
        });

        // Activa/desactiva el modo de edicion sobre la tabla.
        editable.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    Boolean checked = (Boolean) event.getProperty().getValue();
                    table.setEditable(checked);
                }
        );

        // Mapea los valores de los campos del formulario a una nueva instancia de
        // la entidad CRUD y la anyade al contenedor de beans.
        crearBtn.addClickListener(e
                -> {
            String id = (String) binder.getField("id").getValue();
            String nombre = (String) binder.getField("nombre").getValue();
            String ubicacion = (String) binder.getField("ubicacion").getValue();
            String calidad = (String) binder.getField("calidad").getValue();
            
            Hotel h = new Hotel(id, nombre, ubicacion, calidad);
            cH.add(h);
            beans.addBean(h);
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
        vLayoutTable.addComponents(cerrarSesionBtn, table, editable, btnEliminar);
        vLayoutTable.setSpacing(true);
        
        Iterator it = cH.listar().iterator();
        while(it.hasNext()){
            beans.addBean((Hotel) it.next());
        }
        
        

        /*------------------(END)TABLA (read, update, delete)------------------*/
        // Anyadimos los componentes que contienen los layouts del formulario y
        // la tabla al layout principal de la UI. Se aplican tambian los estilos.
        layout.addComponents(vLayoutForm, vLayoutTable);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        setContent(layout);
    }
    

    @WebServlet(value = {"/hoteles/*"}, name = "GestionHoteles", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = GestionHoteles.class)
    public static class GestionHotelesServlet extends VaadinServlet {
    }
}
