package co.edu.uniquindio.criterion.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.criterion.model.Articulo;
import co.edu.uniquindio.criterion.model.Cliente;
import co.edu.uniquindio.criterion.model.CompraArticulo;
import co.edu.uniquindio.criterion.model.MetodoPago;
import co.edu.uniquindio.criterion.repositories.ArticuloRepo;
import co.edu.uniquindio.criterion.repositories.CompraArticuloRepo;
import co.edu.uniquindio.criterion.repositories.MetodoPagoRepo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class ArticulosController implements Initializable{

     @Autowired
    private SceneController sceneController;
    private ObservableList<Articulo> listaArticulosObservable = FXCollections.observableArrayList();
    private ObservableList<CompraArticulo> listaCompradosObservable = FXCollections.observableArrayList();
    private Cliente clienteLogueado;
    private Articulo articuloSeleccionado;
    private static final String VALIDACION_DATOS = "Validacion de datos";
    @Autowired
    private ArticuloRepo articuloRepo;
    @Autowired
    private CompraArticuloRepo compraArticuloRepo;
    @Autowired
    private MetodoPagoRepo metodoPagoRepo;


    @FXML
    private Button btnComprar;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField buscadorArticulos;

    @FXML
    private TextField buscadorComprados;

    @FXML
    private TableColumn<Articulo, String> columnaDescripcionArticulos;

    @FXML
    private TableColumn<CompraArticulo, String> columnaDescripcionComprados;

    @FXML
    private TableColumn<Articulo, Long> columnaIdArticulos;

    @FXML
    private TableColumn<CompraArticulo, Long> columnaIdComprados;

    @FXML
    private TableColumn<Articulo, String> columnaNombreArticulos;

    @FXML
    private TableColumn<CompraArticulo, String> columnaNombreComprados;

    @FXML
    private TableColumn<Articulo, Double> columnaPrecioArticulos;

    @FXML
    private TableColumn<CompraArticulo, Double> columnaPrecioComprados;

    @FXML
    private TableColumn<Articulo, String> columnaTipoArticulos;

    @FXML
    private TableColumn<CompraArticulo, String> columnaTipoComprados;

    @FXML
    private TableView<Articulo> tablaArticulos;

    @FXML
    private TableView<CompraArticulo> tablaComprados;

    @FXML
    private TableColumn<CompraArticulo, LocalDate> columnaFecha;

    @FXML
    private ComboBox<String> comboMetodo;

    @FXML
    void comprar(ActionEvent event) {
        comprar();
    }

    private void comprar() {
        if(articuloSeleccionado == null){
            mostrarMensaje(VALIDACION_DATOS, "Debe seleccionar un articulo", "Debe seleccionar un articulo", AlertType.ERROR);
            return;
        }
        if(comboMetodo.getValue() == null){
            mostrarMensaje(VALIDACION_DATOS, "Debe seleccionar un metodo de pago", "Debe seleccionar un metodo de pago", AlertType.ERROR);
            return;
        }
        CompraArticulo compraArticulo = new CompraArticulo(articuloSeleccionado.getPrecio(), clienteLogueado, articuloSeleccionado, metodoPagoRepo.findByNombre(comboMetodo.getValue()), LocalDate.now());
        compraArticuloRepo.save(compraArticulo);
        mostrarMensaje("Compra exitosa", "Compra exitosa", "Compra exitosa", AlertType.INFORMATION);
        refrescarTablaComprados();
        comboMetodo.setValue(null);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, AlertType information) {
        Alert alert = new Alert(information);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void volver(ActionEvent event) {
        sceneController.cambiarAMenuCliente(event, clienteLogueado);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clienteLogueado = sceneController.getClienteLogueado();
        inicializarArticulos();
        inicializarComprados();
        inicializarComboMetodo();
    }

    private void inicializarComboMetodo() {
        List<String> valoresMetodo = new ArrayList<>();
        for (MetodoPago metodoPago : metodoPagoRepo.findAll()) {
            if (metodoPago.getEstado().equalsIgnoreCase("Activo")) {
                valoresMetodo.add(metodoPago.getNombre());
            }
        }
        ObservableList<String> listaMetodos = FXCollections.observableArrayList(valoresMetodo);
        comboMetodo.getItems().clear();
        comboMetodo.setItems(listaMetodos);
    }

    private void inicializarComprados() {
       
        this.columnaIdComprados.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnaNombreComprados.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getArticulo().getNombre()));
        this.columnaDescripcionComprados.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getArticulo().getDescripcion()));
        this.columnaPrecioComprados.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        this.columnaTipoComprados.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getArticulo().getTipoArticulo().getNombre()));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        refrescarTablaComprados();

        FilteredList<CompraArticulo> listaCompradosFiltrados = new FilteredList<>(listaCompradosObservable, b -> true);
        buscadorComprados.textProperty()
                .addListener((obs, oldValue, newValue) -> listaCompradosFiltrados.setPredicate(articulo -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (articulo.getArticulo().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (articulo.getArticulo().getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (articulo.getArticulo().getTipoArticulo().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (articulo.getPrecioVenta().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (articulo.getFecha().toString().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else {
                        return articulo.getId().toString().toLowerCase().contains(lowerCaseFilter);
                    }
                }));

        SortedList<CompraArticulo> listaCompradosSorteados = new SortedList<>(listaCompradosFiltrados);

        listaCompradosSorteados.comparatorProperty().bind(tablaComprados.comparatorProperty());

        tablaComprados.setItems(listaCompradosSorteados);
        

    }
    private void refrescarTablaComprados() {
        listaCompradosObservable.clear();
        listaCompradosObservable.addAll(compraArticuloRepo.findAllByCliente_Cedula(clienteLogueado.getCedula()));
    }

    private void inicializarArticulos() {
         this.columnaIdArticulos.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnaNombreArticulos.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaDescripcionArticulos.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.columnaPrecioArticulos.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.columnaTipoArticulos.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getTipoArticulo().getNombre()));

        refrescarTablaArticulos();

        FilteredList<Articulo> listaCompradosFiltrados = new FilteredList<>(listaArticulosObservable, b -> true);
        buscadorArticulos.textProperty()
                .addListener((obs, oldValue, newValue) -> listaCompradosFiltrados.setPredicate(articulo -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (articulo.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (articulo.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (articulo.getTipoArticulo().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (articulo.getPrecio().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return articulo.getId().toString().toLowerCase().contains(lowerCaseFilter);
                    }
                }));

        SortedList<Articulo> listaArticulosSorteados = new SortedList<>(listaCompradosFiltrados);

        listaArticulosSorteados.comparatorProperty().bind(tablaArticulos.comparatorProperty());

        tablaArticulos.setItems(listaArticulosSorteados);
        
        //Listener de la tabla para el articuloSeleccionado
        tablaArticulos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                articuloSeleccionado = newSelection;
            }
        });
    }

    private void refrescarTablaArticulos() {
        listaArticulosObservable.clear();
        listaArticulosObservable.addAll(articuloRepo.findAll());
    }
}
