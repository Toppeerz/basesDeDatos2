package co.edu.uniquindio.criterion.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.criterion.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

@Component
public class MenuClienteController implements Initializable{

    @Autowired
    private SceneController sceneController;

    @FXML
    private Button btnAbrirVistaArticulos;

    @FXML
    private Button btnAbrirVistaAutomoviles;

    @FXML
    private Button btnAbrirVistaHoteles;

    @FXML
    private Button btnAbrirVistaPaquetes;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Label txtBienvenido;

    @FXML
    void abrirVistaArticulos(ActionEvent event) {
        sceneController.cambiarAVistaArticulos(event);
    }

    @FXML
    void abrirVistaAutomoviles(ActionEvent event) {
        sceneController.cambiarAVistaReservasAutomoviles(event);
    }

    @FXML
    void abrirVistaHoteles(ActionEvent event) {
        sceneController.cambiarAVistaReservasHoteles(event);
    }

    @FXML
    void abrirVistaPaquetes(ActionEvent event) {
        sceneController.cambiarAVistaPaquetes(event);
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        sceneController.cambiarALogin(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cliente clienteLogueado = sceneController.getClienteLogueado();
        txtBienvenido.setText("Bienvenido "+ clienteLogueado.getNombre());
    }

}
