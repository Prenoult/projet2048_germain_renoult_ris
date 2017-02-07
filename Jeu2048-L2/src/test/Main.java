/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Case;
import modele.Grille;


/**
 *
 * @author castagno
 */
public class Main extends Application {
    //public static Parent root;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("css/styles.css");
        
        stage.setScene(scene);
        stage.show();

        FXMLDocumentController.g.getStyleClass().add("gridPane");
        Grille g = new Grille();
        Boolean b = g.nouvelleCase();
    }





    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
