package upo.tad.g11.proyecto.tad.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.bson.types.ObjectId;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorHabitacion;
import upo.tad.g11.proyecto.tad.model.entity.Habitacion;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;
import upo.tad.g11.proyecto.tad.view.form.HabitacionForm;

@Theme("mytheme")
@Title("Habitaciones")
public class GestionHabitaciones extends UI {

    Controlador controladorH = new ControladorHabitacion();

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
        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("numero", new ObjectProperty("", String.class));
        item.addItemProperty("fumador", new ObjectProperty(false, Boolean.class));
        item.addItemProperty("hotel", new ObjectProperty(null, Hotel.class));
        item.addItemProperty("tipo", new ObjectProperty(null, TipoHabitacion.class));

        // Instanciamos un nuevo componente que contendra los campos del formulario
        HabitacionForm form = new HabitacionForm();

        // Realizamos el binding del formulario entre la UI y el modelo asociado.
        FieldGroup binder = new FieldGroup(item);
        binder.bindMemberFields(form);

        // Creamos un boton para enviar el formulario y lo anyadimos a este
        Button crearBtn = new Button("Crear");
        crearBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        form.addComponent(crearBtn);

        // Boton para volver al menu
        Button menuBtn = new Button("Menú");
        menuBtn.addStyleName(ValoTheme.BUTTON_LINK);

        // Al hacer click, se vuelve al menu principal
        menuBtn.addClickListener(e
                -> {
            UI.getCurrent().getPage().setLocation("/menu");
        }
        );
        vLayoutForm.addComponents(menuBtn, form);

        /*----------------------(END)FORMULARIO (create)-----------------------*/

 /*--------------------TABLA (read, update, delete)--------------------*/
        // Layout que servira de contenedor para la tabla de la entidad.
        VerticalLayout vLayoutTable = new VerticalLayout();

        // Contenedor para almacenar los beans de la entidad CRUD.
        BeanItemContainer<Habitacion> beans
                = new BeanItemContainer<>(Habitacion.class);

        beans.addNestedContainerProperty("hotel.nombre");
        beans.addNestedContainerProperty("tipo.nombre");

        // Creamos la tabla y le asociamos el contenedor creado enteriormente.
        Grid grid = new Grid(beans);

        // Seleccionamos las columnas a visualizar y renombramos las que sean necesarias
        Object[] VISIBLE_COLUMN_IDS = new String[]{"numero", "fumador", "hotel.nombre", "tipo.nombre"};
        grid.setColumns(VISIBLE_COLUMN_IDS);
        Grid.Column hotelColumn = grid.getColumn("hotel.nombre");
        hotelColumn.setHeaderCaption("Hotel");
        Grid.Column tipoColumn = grid.getColumn("tipo.nombre");
        tipoColumn.setHeaderCaption("Tipo");

        // Establecemos las propiedades de la tabla para obtener el
        // comportamiento deseado.
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setEditorEnabled(true);
        grid.setImmediate(true);
        grid.setColumnReorderingAllowed(true);
        grid.setSizeFull();

        // Anyadimos los componentes de control para realizar la accion de
        // eliminar sobre los elementos de la tabla.
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.addStyleName(ValoTheme.BUTTON_DANGER);
        btnEliminar.setEnabled(false);

        //Lsteners para los elementos interactivos de la tabla:
        // Habilita el boton de eliminar al seleccionar una fila de la tabla.
        grid.addSelectionListener(selectionEvent -> {
            if (grid.getSelectedRows().size() > 0) {
                btnEliminar.setEnabled(true);
            } else {
                btnEliminar.setEnabled(false);
            }
        });

        // Elimina el/los elemento/s de la/s fila/s seleccionada/s al pulsa el boton de eliminar.
        btnEliminar.addClickListener(
                (Button.ClickEvent event) -> {
                    for (Object itemId : grid.getSelectedRows()) {
                        controladorH.delete(itemId);
                        beans.removeItem(itemId);
                    }
                    btnEliminar.setEnabled(false);
                });

        grid.getEditorFieldGroup().addCommitHandler(new FieldGroup.CommitHandler() {
            @Override
            public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
            }

            @Override
            public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
                Object itemId = grid.getEditedItemId();
                controladorH.update(itemId);
            }
        });

        // Mapea los valores de los campos del formulario a una nueva instancia de
        // la entidad CRUD y la anyade al contenedor de beans.
        crearBtn.addClickListener(e
                -> {
            ObjectId id = new ObjectId();
            Integer numero = Integer.parseInt((String) binder.getField("numero").getValue());
            Boolean fumador = (Boolean) binder.getField("fumador").getValue();
            TipoHabitacion tipo = (TipoHabitacion) binder.getField("tipo").getValue();
            Hotel hotel = (Hotel) binder.getField("hotel").getValue();

            Habitacion h = new Habitacion(id, numero, fumador, tipo, hotel);
            beans.addBean(h);
            controladorH.add(h);
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
        vLayoutTable.addComponents(cerrarSesionBtn, grid, btnEliminar);
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

    @WebServlet(value = {"/habitaciones/*"}, name = "GestionHabitaciones", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = GestionHabitaciones.class)
    public static class GestionHabitacionesServlet extends VaadinServlet {
    }
}
