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
        Table table = new Table("Tipos de Habitación", beans);

        // Establecemos las propiedades de la tabla para obtener el
        // comportamiento deseado.
        table.setEditable(false);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setColumnReorderingAllowed(true);
        table.setSizeFull();
        table.setPageLength(table.size());
        table.setColumnHeader("nombre", "Nombre");
        table.setColumnHeader("metros", "Nº Metros");
        table.setColumnHeader("terraza", "¿Terraza?");
        table.setColumnHeader("precio", "Precio/noche");
        table.setVisibleColumns("nombre", "metros", "terraza", "precio");

        // Anyadimos los componentes de control para realizar las acciones de
        // editar y eliminar sobre los elementos de la tabla.
        CheckBox editable = new CheckBox("Editar");
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.addStyleName(ValoTheme.BUTTON_DANGER);
        btnEliminar.setEnabled(false);

        //Lsteners para los elementos interactivos de la tabla:
        // Habilita el boton de eliminar al seleccionar una fila de la tabla.
        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    btnEliminar.setEnabled(true);
                }
        );

        // Elimina el elemento de la fila seleccionada al pulsa el boton de eliminar.
        btnEliminar.addClickListener((Button.ClickEvent event) -> {
            Object itemId = table.getValue();
            BeanItem<TipoHabitacion> bean = beans.getItem(itemId);
            TipoHabitacion th = bean.getBean();
            controladorTH.delete(th);
            beans.removeAllItems();
            beans.addAll(controladorTH.listar());
            btnEliminar.setEnabled(false);
        });

        // Activa/desactiva el modo de edicion sobre la tabla.
        editable.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    Boolean checked = (Boolean) event.getProperty().getValue();
                    table.setEditable(checked);
                    if (checked) {
                        editable.setCaption("Guardar");
                    } else {
                        controladorTH.addAll(beans.getItemIds());
                        editable.setCaption("Editar");
                    }
                }
        );

        // Mapea los valores de los campos del formulario a una nueva instancia de
        // la entidad CRUD y la anyade al contenedor de beans.
        crearBtn.addClickListener(e
                -> {
            ObjectId id = new ObjectId();
            String nombre = (String) binder.getField("nombre").getValue();
            Float metros = Float.parseFloat((String) binder.getField("metros").getValue());
            Boolean terraza = (Boolean) binder.getField("terraza").getValue();
            Float precio = Float.parseFloat((String) binder.getField("precio").getValue());

            TipoHabitacion th = new TipoHabitacion(id, nombre, metros, terraza, precio);
            beans.addBean(th);
            controladorTH.add(th);
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

        /*------------------(END)TABLA (read, update, delete)------------------*/
        // Anyadimos los componentes que contienen los layouts del formulario y
        // la tabla al layout principal de la UI. Se aplican tambian los estilos.
        layout.addComponents(vLayoutForm, vLayoutTable);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        setContent(layout);

        beans.addAll(controladorTH.listar());
    }

    @WebServlet(value = {"/tiposHabitacion/*"}, name = "GestionTiposHabitacion", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = GestionTiposHabitacion.class)
    public static class GestionTiposHabitacionServlet extends VaadinServlet {
    }
}
