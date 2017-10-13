/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.seb.databas.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * cette classe retourne une connexion à une base de données MySQL
 * elle implémente le pattern Singleton
 * @author formation
 */
public class DatabaseConnection {
  
    private static  Connection instance;
    
   
    //constructeur privé pour éviter de pouvoir instancier la classe depuis l'interieur
    private DatabaseConnection(){
        
    }///fin de constructeur
    
    /**
     * retourn un objet de type Connection 
     * @return 
     */
    public static Connection getInstance() throws SQLException{
        
        try {
        //instanciation d'un objet Propertises qui contient la configuration
        Properties config = new Properties();
        
        
            //ouverture du fichier qui contient les infos
            FileInputStream fis = new FileInputStream("./config/app.properties");
            
            //chargement des données du fichier dans l'objet properties
            config.load(fis);
            fis.close();
            
            //Recuperation des informations de configuration dans des variables
            String dbHost = config.getProperty("db.host", "localhost");
            String dbName = config.getProperty("db.name", "bibliotheque");
            String dbUser = config.getProperty("db.user", "root");
            String dbPass = config.getProperty("db.pass", "");
            //si l'instance est null on instancie une nouvelle connection
     if (instance == null){
         instance = DriverManager.getConnection(
         //"jdbc:mysql://localhost/bibliotheque","root","");
           "jdbc:mysql://"+dbHost+"/"+dbName,dbUser,dbPass);
                 
        }
     
        
        
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        
        return instance;
    }
    
    
}///fin de la class

