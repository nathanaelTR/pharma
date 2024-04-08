package fr.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    
    String dbURL = "jdbc:mysql://localhost:3309/db_app";
    String nomUtilisateur = "root";
    String mdp = "15975359200";

   public void connection() { 
try {
  // Connexion en passant les informations spécifiées précédemment
  Connection conn = DriverManager.getConnection(dbURL, nomUtilisateur, mdp);
  // Si on est bien connecté à la DB
  if (conn != null) {
    // On affiche un petit message sur le terminal
    System.out.println("Connexion réussie !");
  }
} catch (SQLException ex) {
  // Code de traitement d'erreur
  ex.printStackTrace();
}

}

}
