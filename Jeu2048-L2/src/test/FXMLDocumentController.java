/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modele.Case;
import modele.Grille;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;

/**
 *
 * @author castagno
 */
public class FXMLDocumentController implements Initializable {
    /*
     * Variables globales correspondant à des objets définis dans la vue (fichier .fxml)
     * Ces variables sont ajoutées à la main et portent le même nom que les fx:id dans Scene Builder
     */
    @FXML
    private Label score; // value will be injected by the FXMLLoader
    @FXML
    private GridPane grille;
    @FXML
    public Pane fond;// panneau recouvrant toute la fenêtre
    public static Pane pa;
    public static GridPane g;

    // variables globales non définies dans la vue (fichier .fxml)
    private int x = 24, y = 191;
    private int objectifx = 24, objectify = 191;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.pa=this.fond;
        this.g=this.grille;
        System.out.println("le contrôleur initialise la vue");
        grille.getStyleClass().add("gridpane");
    }







}
