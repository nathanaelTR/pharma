package fr.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class historiqueController {

       @FXML
    private ListView<?> Historique_affichage;

    @FXML
    void Retour(ActionEvent event) throws IOException {
        App.setRoot("application_visiteur");
    }

    @FXML
    void deconnexion(ActionEvent event) throws IOException {
        App.setRoot("connexion");
        connexionController.UtilisateurConnecter = 0;
    }

}
