package co.edu.uniquindio.criterion.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import co.edu.uniquindio.criterion.model.Cliente;
import co.edu.uniquindio.criterion.model.Persona;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Component
public class LoginController {


    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Label labelContrasenia;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private Label txtError;

    @FXML
    private TextField txtNombreDeUsuario;


    @FXML
    void iniciarSesion(ActionEvent event) {

    }

    void initialize(){

    }


}