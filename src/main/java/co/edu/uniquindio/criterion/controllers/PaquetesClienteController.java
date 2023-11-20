package co.edu.uniquindio.criterion.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.criterion.model.Cliente;
import co.edu.uniquindio.criterion.model.CompraPaquete;
import co.edu.uniquindio.criterion.model.DetalleCompraPaquete;
import co.edu.uniquindio.criterion.model.MetodoPago;
import co.edu.uniquindio.criterion.model.Paquete;
import co.edu.uniquindio.criterion.repositories.CompraPaqueteRepo;
import co.edu.uniquindio.criterion.repositories.DetalleCompraPaqueteRepo;
import co.edu.uniquindio.criterion.repositories.MetodoPagoRepo;
import co.edu.uniquindio.criterion.repositories.PaqueteRepo;
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
public class PaquetesClienteController implements Initializable {

    @Autowired
    private SceneController sceneController;
    private ObservableList<Paquete> listaPaqueteObservable = FXCollections.observableArrayList();
    private ObservableList<DetalleCompraPaquete> listaPaqueteCompradoObservable = FXCollections.observableArrayList();
    private Cliente clienteLogueado;
    private Paquete paqueteSeleccionado;
    private DetalleCompraPaquete detalleCompraPaqueteSeleccionado;
    private static final String VALIDACION_DATOS = "Validacion de datos";

    @Autowired
    private PaqueteRepo paqueteRepo;
    @Autowired
    private MetodoPagoRepo metodoPagoRepo;
    @Autowired
    private CompraPaqueteRepo compraPaqueteRepo;
    @Autowired
    private DetalleCompraPaqueteRepo detalleCompraPaqueteRepo;
    

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnComprar;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField buscador;

    @FXML
    private TextField buscadorComprados;

    @FXML
    private TableColumn<Paquete, Long> columnaCantidad;

    @FXML
    private TableColumn<DetalleCompraPaquete, Long> columnaCantidadPersonasComprado;

    @FXML
    private TableColumn<Paquete, String> columnaDescripcion;

    @FXML
    private TableColumn<DetalleCompraPaquete, String> columnaDescripcionComprado;

    @FXML
    private TableColumn<Paquete, String> columnaDescuento;

    @FXML
    private TableColumn<DetalleCompraPaquete, String> columnaDescuentoComprado;

    @FXML
    private TableColumn<Paquete, Long> columnaId;

    @FXML
    private TableColumn<DetalleCompraPaquete, String> columnaIdComprado;

    @FXML
    private TableColumn<DetalleCompraPaquete, String> columnaMetodo;

    @FXML
    private TableColumn<Paquete, String> columnaNombre;

    @FXML
    private TableColumn<DetalleCompraPaquete, String> columnaNombreComprado;

    @FXML
    private TableColumn<Paquete, Double> columnaPrecio;

    @FXML
    private TableColumn<Paquete, String> columnaTipo;

    @FXML
    private TableColumn<DetalleCompraPaquete, String> columnaTipoComprado;

    @FXML
    private TableColumn<DetalleCompraPaquete, Double> columnaTotal;

    @FXML
    private ComboBox<String> comboMetodo;

    @FXML
    private TableView<Paquete> tablaPaquete;

    @FXML
    private TableView<DetalleCompraPaquete> tablaPaquetesComprados;

    @FXML
    private TextField txtCantidadPersonas;

    @FXML
    void cancelar(ActionEvent event) {
        
    }

    @FXML
    void comprar(ActionEvent event) {
        comprar();
    }

    private void comprar() {
        if (paqueteSeleccionado == null) {
            mostrarMensaje(VALIDACION_DATOS, "Debe seleccionar un paquete", "Debe seleccionar un paquete",
                    AlertType.ERROR);
            return;
        }
        if (txtCantidadPersonas.getText().isBlank() || txtCantidadPersonas.getText().isEmpty()) {
            mostrarMensaje(VALIDACION_DATOS, "Debe ingresar la cantidad de personas",
                    "Debe ingresar la cantidad de personas", AlertType.ERROR);
            return;
        }
        Integer cantidadPersonas = Integer.parseInt(txtCantidadPersonas.getText());
        if (cantidadPersonas <= 0) {
            mostrarMensaje(VALIDACION_DATOS, "Debe ingresar una cantidad de personas numerica",
                    "Debe ingresar una cantidad de personas numerica", AlertType.ERROR);
            return;
        }
        if (comboMetodo.getSelectionModel().isEmpty()) {
            mostrarMensaje(VALIDACION_DATOS, "Debe seleccionar un metodo de pago", "Debe seleccionar un metodo de pago",
                    AlertType.ERROR);
            return;
        }
        CompraPaquete compraPaquete = new CompraPaquete(paqueteSeleccionado, clienteLogueado,
                metodoPagoRepo.findByNombre(comboMetodo.getSelectionModel().getSelectedItem()), LocalDate.now(),
                "El cliente con cedula" + clienteLogueado.getCedula() + " compro el paquete con id "
                        + paqueteSeleccionado.getId());
        compraPaqueteRepo.save(compraPaquete);
        DetalleCompraPaquete detalleCompraPaquete;
        Long cantidadPersonasLong = Long.valueOf(txtCantidadPersonas.getText());
        Double total = (paqueteSeleccionado.getPrecio() * cantidadPersonasLong);
        if (paqueteSeleccionado.getCantidadPersonas() > cantidadPersonasLong) {
            detalleCompraPaquete = new DetalleCompraPaquete(compraPaquete, cantidadPersonasLong, "Comprado", total,
                    0.0);
        } else {
            detalleCompraPaquete = new DetalleCompraPaquete(compraPaquete, cantidadPersonasLong, "Comprado",
                    (cantidadPersonasLong * paqueteSeleccionado.getPrecio())
                            - (cantidadPersonasLong * paqueteSeleccionado.getPrecio()
                                    * paqueteSeleccionado.getPoliticaDescuento().getPorcentajeDescuento()),
                    paqueteSeleccionado.getPoliticaDescuento().getPorcentajeDescuento());
        }
        detalleCompraPaqueteRepo.save(detalleCompraPaquete);
        refrescarTabla();
        refrescarTablaComprados();
        limpiarCampos();
        mostrarMensaje("Informacion", "Compra exitosa", "Compra exitosa", AlertType.INFORMATION);
    }

    private void limpiarCampos() {
        txtCantidadPersonas.setText("");
        comboMetodo.getSelectionModel().clearSelection();
        paqueteSeleccionado = null;
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
        inicializarTabla();
        inicializarTablaComprados();
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

    private void inicializarTablaComprados() {
        this.columnaIdComprado.setCellValueFactory(a -> new SimpleStringProperty(String.valueOf(a.getValue().getCompraPaquete().getPaquete().getId())));
        this.columnaDescripcionComprado.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getCompraPaquete().getPaquete().getDescripcion()));
        this.columnaNombreComprado.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getCompraPaquete().getPaquete().getNombre()));
        this.columnaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        this.columnaTipoComprado.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getCompraPaquete().getPaquete().getTipoPaquete().getNombre()));
        this.columnaMetodo.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getCompraPaquete().getMetodoPago().getNombre()));
        this.columnaDescuentoComprado.setCellValueFactory(a -> new SimpleStringProperty(String.format("%.0f", a.getValue().getDescuento()*100)));
        this.columnaCantidadPersonasComprado.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        refrescarTablaComprados();

        FilteredList<DetalleCompraPaquete> listaCompradosFiltrados = new FilteredList<>(listaPaqueteCompradoObservable, b -> true);
        buscadorComprados.textProperty()
                .addListener((obs, oldValue, newValue) -> listaCompradosFiltrados.setPredicate(paqueteComprado -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (paqueteComprado.getCompraPaquete().getPaquete().getId().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paqueteComprado.getCompraPaquete().getPaquete().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paqueteComprado.getCompraPaquete().getPaquete().getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paqueteComprado.getTotal().toString().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paqueteComprado.getCompraPaquete().getPaquete().getTipoPaquete().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paqueteComprado.getCompraPaquete().getMetodoPago().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if(String.format("%.0f", paqueteComprado.getDescuento()).contains(lowerCaseFilter)){
                        return true;
                    }else {
                        return paqueteComprado.getCantidad().toString().contains(lowerCaseFilter);
                    }
                }));

        SortedList<DetalleCompraPaquete> listaPaquetesCompradosSorteados = new SortedList<>(listaCompradosFiltrados);

        listaPaquetesCompradosSorteados.comparatorProperty().bind(tablaPaquetesComprados.comparatorProperty());

        tablaPaquetesComprados.setItems(listaPaquetesCompradosSorteados);

        tablaPaquetesComprados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                detalleCompraPaqueteSeleccionado = newSelection;
            }
        });
    }

    private void refrescarTablaComprados() {
        listaPaqueteCompradoObservable.clear();
        listaPaqueteCompradoObservable.addAll(detalleCompraPaqueteRepo.findAllByCompraPaquete_Cliente_Cedula(clienteLogueado.getCedula()));
    }

    private void inicializarTabla() {
        this.columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.columnaTipo.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getTipoPaquete().getNombre()));
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadPersonas"));
        this.columnaDescuento.setCellValueFactory(a -> new SimpleStringProperty(
                String.format("%.0f", a.getValue().getPoliticaDescuento().getPorcentajeDescuento()*100)));

        refrescarTabla();

        FilteredList<Paquete> listaPaquetesFiltrados = new FilteredList<>(listaPaqueteObservable, b -> true);
        buscador.textProperty()
                .addListener((obs, oldValue, newValue) -> listaPaquetesFiltrados.setPredicate(paquete -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (paquete.getId().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paquete.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paquete.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paquete.getTipoPaquete().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paquete.getCantidadPersonas().toString().contains(lowerCaseFilter)) {
                        return true;
                    } else if (paquete.getPoliticaDescuento().getPorcentajeDescuento().toString()
                            .contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return paquete.getPrecio().toString().contains(lowerCaseFilter);
                    }
                }));

        SortedList<Paquete> listaPaquetesSorteados = new SortedList<>(listaPaquetesFiltrados);

        listaPaquetesSorteados.comparatorProperty().bind(tablaPaquete.comparatorProperty());

        tablaPaquete.setItems(listaPaquetesSorteados);

        tablaPaquete.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                paqueteSeleccionado = newSelection;
            }
        });
    }

    private void refrescarTabla() {
        listaPaqueteObservable.clear();
        listaPaqueteObservable.addAll(paqueteRepo.findAll());
    }

}
