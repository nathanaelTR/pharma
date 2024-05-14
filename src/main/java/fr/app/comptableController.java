package fr.app;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class comptableController {

    ObservableList<String> fiche_de_frais_visiteur = FXCollections.observableArrayList();

    @FXML
    private ListView<String> list_fiche_de_frais;

    @FXML
    private TextField user_poste;

    @FXML
    private TextField user_search;

    @FXML
    private TextField user_session;

    @FXML
    void deconnexion(ActionEvent event) throws IOException {

        App.setRoot("connexion");
        connexionController.UtilisateurConnecter = 0;

    }

    @FXML
    public void initialize() throws SQLException {
        userSession();
        getAllFicheDeFrais();
        filterVisiteur();
    }

    @FXML
    public void userSession() throws SQLException {

        String sql = "SELECT u.nom, u.prenom, p.posteName, u.id FROM utilisateur u INNER JOIN poste p ON p.id = u.fk_poste"
                + " WHERE u.id = '" + connexionController.getUser() + "'";

        Connect connexion = new Connect();
        Connection con = DriverManager.getConnection(connexion.dbURL, connexion.nomUtilisateur, connexion.mdp);

        Statement sqlRec = con.createStatement();
        ResultSet sqlreq = sqlRec.executeQuery(sql);

        if (sqlreq.next()) {

            user_session.setText(sqlreq.getString("nom") + " " + sqlreq.getString("prenom"));
            user_poste.setText(sqlreq.getString("posteName"));

        }
    }

    @FXML
    void getAllFicheDeFrais() throws SQLException {

        String sql = "SELECT f.id, u.nom, u.prenom, f.id_visiteur, f.date_DeCreation FROM fiche_de_frais f INNER JOIN utilisateur u ON u.id = f.id_visiteur";

        Connect connexion = new Connect();
        Connection con = DriverManager.getConnection(connexion.dbURL, connexion.nomUtilisateur, connexion.mdp);

        Statement res = con.createStatement();
        ResultSet req = res.executeQuery(sql);

        while (req.next()) {
            int idFiche = req.getInt("id");
            String nomVisiteur = req.getString("nom");
            String prenomVisiteur = req.getString("prenom");
            int IdVisiteur = req.getInt("id_visiteur");
            String DateFiche = req.getString("date_DeCreation");

            String sortie = "Fiche ID: " + idFiche + " | Visiteur : " + nomVisiteur + " " + prenomVisiteur + " ["
                    + IdVisiteur + "] | Date : " + DateFiche;
            fiche_de_frais_visiteur.add(sortie);
        }

        list_fiche_de_frais.setItems(fiche_de_frais_visiteur);

    }

    @FXML

    void filterVisiteur() {
      if(user_search.getText() != "") {
        System.out.println(user_search.getText());
      }
    }

    @FXML
    void openFiche() {
        try {
            String selectedItem = list_fiche_de_frais.getSelectionModel().getSelectedItem();
            String[] parts = selectedItem.split("\\|");

            String ficheIdString = parts[0].trim();

            int ficheId = Integer.parseInt(ficheIdString.replace("Fiche ID: ", ""));
            System.out.println(ficheId);
        } catch (Exception e) {}
    }

}
