package fr.app;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import java.sql.*;

public class historiqueController {

    ObservableList<String> visiteurFicheDeFrais = FXCollections.observableArrayList();

    @FXML
    private ListView<String> Historique_affichage;

    @FXML
    private DatePicker DateGet;

    @FXML
    void Retour(ActionEvent event) throws IOException {
        App.setRoot("application_visiteur");
    }

    @FXML
    void initialize() throws SQLException {

        String sql = "SELECT f.id, f.id_visiteur, f.date_DeCreation FROM fiche_de_frais f INNER JOIN utilisateur u ON u.id = f.id_visiteur INNER JOIN etat e ON e.id = f.fk_etat"
                +
                " WHERE u.id = '" + connexionController.getUser() + "';";

        Connect connect = new Connect();
        Connection conn = DriverManager.getConnection(connect.dbURL, connect.nomUtilisateur, connect.mdp);

        Statement statementSql = conn.createStatement();
        ResultSet req = statementSql.executeQuery(sql);

        while (req.next()) {
            int IdFiche = req.getInt("id");
            String dateCreation = req.getString("date_DeCreation");

            String sortie = "Fiche ID: " + IdFiche + " Date : " + dateCreation;
            visiteurFicheDeFrais.add(sortie);
        }

        Historique_affichage.setItems(visiteurFicheDeFrais);

    }

    // private void getPicker() {

    // }

    @FXML
    void deconnexion(ActionEvent event) throws IOException {
        App.setRoot("connexion");
        connexionController.UtilisateurConnecter = 0;
    }

}

// sql = "SELECT f.id, f.id_visiteur ,f.date_DeCreation FROM fiche_de_frais f
// INNER JOIN utilisateur u ON u.id = f.id_visiteur INNER JOIN etat e ON e.id =
// f.fk_etat" +
// " WHERE u.id = '" + connexionController.getUser() + "' AND
// MONTH(f.date_DeCreation) = '" + DateGet.getValue().getMonthValue() + "';";