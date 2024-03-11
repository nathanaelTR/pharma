package fr.app;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class interactionController {

    @FXML
    private Button logout;

    @FXML
    private TextField user_session;

    @FXML
    private void deconnexion() throws IOException {

        App.setRoot("connexion");

    }

    @FXML
    private void userSession() throws SQLException, IOException {}

}

