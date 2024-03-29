package fr.app;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VisiteurController {

    @FXML
    private TextField Dateinput;

    @FXML
    private Button Envoyer;

    @FXML
    private Label KilométrageTotal;

    @FXML
    private TextField Kilometrageinput;

    @FXML
    private TextField Libelléinput;

    @FXML
    private TextField Matriculeinput;

    @FXML
    private Button Historique;

    @FXML
    private TextField Moisinput;

    @FXML
    private TextField Montantinput;

    @FXML
    private TextField Nuitinput;

    @FXML
    private TextField Repasmidiinput;

    @FXML
    private Label NuitTotal;

    @FXML
    private Label RepasTotal;

    @FXML
    private Button logout;

    @FXML
    private TextField user_poste;

    @FXML
    private TextField user_session;

    // Permet de calculer quantité multiplier par un Chiffre/nombre
    private double calcFicheFrai(int qt, double operation) {
        return qt * operation;
    }

    @FXML
    void EnvoyerFiche(ActionEvent event) throws DateTimeParseException, SQLException {


        Connect connexion = new Connect();
        Connection con = DriverManager.getConnection(connexion.dbURL, connexion.nomUtilisateur, connexion.mdp);

    // Fiche de Frais 
        int qt_nuite = Integer.parseInt(Nuitinput.getText());
        int qt_repasMidi = Integer.parseInt(Repasmidiinput.getText());
        int qt_kilometrage = Integer.parseInt(Kilometrageinput.getText());
        String DateCreation = Moisinput.getText();

        double NuiteOperation = 80.0;
        double RepasMidiOperation = 29.0;
        double TarifVehicule = 0.1;

        String Total_Nuite = String.valueOf(calcFicheFrai(qt_nuite, NuiteOperation));
        String Total_repasMidi = String.valueOf(calcFicheFrai(qt_repasMidi, RepasMidiOperation));
        String Total_kilometrage = String.valueOf(calcFicheFrai(qt_kilometrage, TarifVehicule));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate DateFiche = LocalDate.parse(DateCreation, formatter);

        Date DateTest = Date.valueOf(DateFiche);

        System.out.println(DateTest);

        NuitTotal.setText(Total_Nuite);
        RepasTotal.setText(Total_repasMidi);
        KilométrageTotal.setText(Total_kilometrage);

        String sql = "INSERT INTO fiche_de_frais(id_visiteur, qt_nuité, qt_repasMidi, qt_kilometrage, qt_total_kilo_kilometrage, qt_total_repasMidi, qt_total_nuité, date_DeCreation, fk_etat) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prepaState = con.prepareStatement(sql);

        prepaState.setInt(1, 2);
        prepaState.setInt(2, qt_nuite);
        prepaState.setInt(3, qt_repasMidi);
        prepaState.setInt(4, qt_kilometrage);
        prepaState.setDouble(5, qt_kilometrage * TarifVehicule);
        prepaState.setDouble(6, qt_repasMidi * RepasMidiOperation);
        prepaState.setDouble(7, qt_nuite * NuiteOperation);
        prepaState.setDate(8, DateTest);
        prepaState.setInt(9, 5);
        prepaState.executeUpdate();

    // Autre Frais
    String Dtinput = Dateinput.getText();
    String LInput = Libelléinput.getText();
    String MInput = Montantinput.getText();

    if(Dtinput.length() > 0 || LInput.length() > 0 || MInput.length() > 0) {

        Boolean DateVerificator = true;
        
        try {
            LocalDate.parse(Dtinput, formatter);
            // LocalDate parsedDate = 
            DateVerificator = true;
        }catch(DateTimeParseException e) {
            DateVerificator = false;
        }

        if(DateVerificator) {
            System.out.println("Je suis une bonne date");
        }else {
            System.out.println("Je ne suis pas une bonne date");
        }
    }
    }

    @FXML
    void deconnexion(ActionEvent event) throws IOException {

        App.setRoot("connexion");
        connexionController.UtilisateurConnecter = 0;

    }

    @FXML
    void Historique() throws IOException {
        App.setRoot("application_visiteur_historique");
    }

    @FXML
    public void initialize() throws SQLException {
        userSession();
    }

    @FXML
    public void userSession() throws SQLException {

        String sql = "SELECT u.nom, u.prenom, p.posteName, u.id FROM utilisateur u INNER JOIN poste p ON p.id = u.fk_poste"
        +" WHERE u.id = '" + connexionController.getUser() + "'";
        
        Connect connexion = new Connect();
        Connection con = DriverManager.getConnection(connexion.dbURL, connexion.nomUtilisateur, connexion.mdp);

        Statement sqlRec = con.createStatement();
        ResultSet sqlreq = sqlRec.executeQuery(sql);

        if(sqlreq.next()) {

        user_session.setText(sqlreq.getString("nom") + " " + sqlreq.getString("prenom"));
        user_poste.setText(sqlreq.getString("posteName"));
        Matriculeinput.setText(sqlreq.getString("id"));

        }
    }

}
