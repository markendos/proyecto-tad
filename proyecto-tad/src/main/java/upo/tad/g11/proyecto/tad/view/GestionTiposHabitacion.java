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
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.bson.types.ObjectId;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorTipoHabitacion;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;
import upo.tad.g11.proyecto.tad.view.form.TiposHabitacionForm;

@Theme("mytheme")
@Title("Tipos Habitacion")
public class GestionTiposHabitacion extends UI {

    Controlador controladorTH = new ControladorTipoHabitacion();

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
        item.addItemProperty("metros", new ObjectProperty("0", String.class));
        item.addItemProperty("terraza", new ObjectProperty(false, Boolean.class));
        item.addItemProperty("precio", new ObjectProperty("0", String.class));

        // Instanciamos un nuevo componente que contendra los campos del formulario
        TiposHabitacionForm form = new TiposHabitacionForm();

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

        vLayoutForm.addComponents(form);

        /*----------------------(END)FORMULARIO (create)-----------------------*/

 /*--------------------TABLA (read, update, delete)--------------------*/
        // Layout que servira de contenedor para la tabla de la entidad.
        VerticalLayout vLayoutTable = new VerticalLayout();

        // Contenedor para almacenar los beans de la entidad CRUD.
        BeanItemContainer<TipoHabitacion> beans
                = new BeanItemContainer<>(TipoHabitacion.class);

        // Creamos la tabla y le asociamos el contenedor creado enteriormente.
        Grid grid = new Grid(beans);

        // Seleccionamos las columnas a visualizar y renombramos las que sean necesarias
        Object[] VISIBLE_COLUMN_IDS = new String[]{"nombre", "metros", "terraza", "precio"};
        grid.setColumns(VISIBLE_COLUMN_IDS);

        Grid.Column precioColumn = grid.getColumn("precio");
        precioColumn.setHeaderCaption("Precio/noche");

        // Establecemos las propiedades de la tabla para obtener el
        // comportamiento deseado.
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.setEditorEnabled(true);
        grid.setImmediate(true);
        grid.setColumnReorderingAllowed(true);
        grid.setWidth("100%");
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(10);

        // Anyadimos los componentes de control para realizar la accion de
        // eliminar sobre los elementos de la tabla.
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.addStyleName(ValoTheme.BUTTON_DANGER);
        btnEliminar.setEnabled(false);

        //Listeners para los elementos interactivos de la tabla:
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
                        controladorTH.delete(itemId);
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
                controladorTH.update(itemId);
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
                Float metros = Float.parseFloat((String) binder.getField("metros").getValue());
                Boolean terraza = (Boolean) binder.getField("terraza").getValue();
                Float precio = Float.parseFloat((String) binder.getField("precio").getValue());

                TipoHabitacion th = new TipoHabitacion(id, nombre, metros, terraza, precio);
                beans.addBean(th);
                controladorTH.add(th);
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
        layout.setWidth("100%");

        setContent(layout);

        beans.addAll(controladorTH.listar());
    }

    @WebServlet(value = {"/tiposHabitacion/*"}, name = "GestionTiposHabitacion", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = GestionTiposHabitacion.class)
    public static class GestionTiposHabitacionServlet extends VaadinServlet {
    }
}
