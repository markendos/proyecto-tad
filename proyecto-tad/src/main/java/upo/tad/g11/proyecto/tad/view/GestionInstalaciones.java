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
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.bson.types.ObjectId;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorInstalacion;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.Instalacion;
import upo.tad.g11.proyecto.tad.view.form.InstalacionForm;

@Theme("mytheme")
@Title("Instalaciones")
public class GestionInstalaciones extends UI {

    Controlador controladorI = new ControladorInstalacion();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Obtenemos la sesion HTTP del usuario actual.
        WrappedSession session = getSession().getSession();

        if (session.getAttribute("usuario") == null) {
            //En caso de no existir una sesión activa, redirigimos al login
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
        item.addItemProperty("nombre", new ObjectProperty("", String.class));
        item.addItemProperty("tipo", new ObjectProperty("", String.class));
        item.addItemProperty("aforo", new ObjectProperty("0", String.class));
        item.addItemProperty("hotel", new ObjectProperty(null, Hotel.class));

        // Instanciamos un nuevo componente que contendra los campos del formulario
        InstalacionForm form = new InstalacionForm();

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
        BeanItemContainer<Instalacion> beans
                = new BeanItemContainer<>(Instalacion.class);

        beans.addNestedContainerProperty("hotel.nombre");

        // Creamos la tabla y le asociamos el contenedor creado enteriormente.
        Grid grid = new Grid(beans);

        // Seleccionamos las columnas a visualizar y renombramos las que sean necesarias
        Object[] VISIBLE_COLUMN_IDS = new String[]{"nombre", "tipo", "aforo", "hotel.nombre",};
        grid.setColumns(VISIBLE_COLUMN_IDS);
        Grid.Column hotelColumn = grid.getColumn("hotel.nombre");
        hotelColumn.setHeaderCaption("Hotel");

        // Establecemos las propiedades de la tabla para obtener el
        // comportamiento deseado.
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setEditorEnabled(true);
        grid.setImmediate(true);
        grid.setColumnReorderingAllowed(true);
        grid.setSizeFull();

        grid.getColumn("hotel.nombre").setEditable(false);

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
        btnEliminar.addClickListener((Button.ClickEvent event) -> {
            for (Object itemId : grid.getSelectedRows()) {
                controladorI.delete(itemId);
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
                controladorI.update(itemId);
            }
        });

        // Mapea los valores de los campos del formulario a una nueva instancia de
        // la entidad CRUD y la anyade al contenedor de beans.
        crearBtn.addClickListener(e
                -> {
            boolean valid = true;
            for (Field field : binder.getFields()) {
                valid &= field.isValid();
            }
            if (valid) {
                ObjectId id = new ObjectId();
                String nombre = (String) binder.getField("nombre").getValue();
                String tipo = (String) binder.getField("tipo").getValue();
                Integer aforo = Integer.parseInt((String) binder.getField("aforo").getValue());
                Hotel hotel = (Hotel) binder.getField("hotel").getValue();

                Instalacion inst = new Instalacion(id, nombre, tipo, aforo, hotel);
                beans.addBean(inst);
                controladorI.add(inst);
            } else {
                Notification.show("Los datos no son válidos", "Revise los campos del fomulario", Notification.Type.ERROR_MESSAGE);
            }
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

        beans.addAll(controladorI.listar());
    }

    @WebServlet(value = {"/instalaciones/*"}, name = "GestionInstalaciones", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = GestionInstalaciones.class)
    public static class GestionInstalacionesServlet extends VaadinServlet {
    }
}
