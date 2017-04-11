package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by jerem on 21/01/2017.
 */
public class Background extends Parent {

    private GridPane grid = new GridPane();
    private Button button_new = new Button("New Game");
    private Button button_save = new Button("Save");
    private Button button_load = new Button("Load");
    private Button button_ia = new Button("IA");

    /**
     * Fonction permettant de creer le "design" de la grille
     */
    public Background() {
        // Création d'un carré gris qui sert de fond (taille 600 x 600 avec les bords arrondis)
        Rectangle fond = new Rectangle();
        fond.setWidth(600);
        fond.setHeight(600);
        fond.setArcWidth(15);
        fond.setArcHeight(15);
        //fond.setFill(Color.web("#3B3B3B"));
        fond.setFill(Color.web("#bbada0"));

        // Création d'une grille qui regroupe les différentes cases du jeu
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(15, 15, 15, 15));


        // Affichage de toutes les cases vides
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                Rectangle fond_case = new Rectangle(131.25, 131.25, Color.web(/*"#777777"*/"#CDC0B4"));
                fond_case.setArcWidth(7);
                fond_case.setArcHeight(7);
                this.getChildren().add(fond_case);
                grid.add(fond_case, j, i);
            }
        }

        // Logo 2048
        Text text = new Text();
        text.setText("2048");
        text.setX(150);
        text.setY(100);
        text.getStyleClass().add("logo");
        Text text2 = new Text();
        text2.setText("Join the numbers and get to the 2048 tile!");
        text2.setX(150);
        text2.setY(150);
        text2.getStyleClass().add("rules");

        // Positionnement des boutons (new game, save et load)
        button_new.setTranslateX(603);
        button_new.setTranslateY(75);
        button_save.setTranslateX(603);
        button_save.setTranslateY(30);
        button_load.setTranslateX(681.5);
        button_load.setTranslateY(30);
        button_ia.setTranslateX(603);
        button_ia.setTranslateY(120);
        button_new.getStyleClass().add("button");
        button_new.getStyleClass().add("big_button");
        button_save.getStyleClass().add("button");
        button_save.getStyleClass().add("little_button");
        button_load.getStyleClass().add("button");
        button_load.getStyleClass().add("little_button");
        button_ia.getStyleClass().add("button");
        button_ia.getStyleClass().add("big_button");


        // Positionnement de la grille au centre de la fenêtre
        grid.setTranslateX(150);
        grid.setTranslateY(200);
        fond.setTranslateX(150);
        fond.setTranslateY(200);

        this.getChildren().add(fond);
        this.getChildren().add(grid);
        this.getChildren().add(text);
        this.getChildren().add(text2);
        this.getChildren().add(button_new);
        this.getChildren().add(button_save);
        this.getChildren().add(button_load);
        this.getChildren().add(button_ia);
        this.setFocusTraversable(true);
    }


    public Button getButtonNew() { return this.button_new; }
    public Button getButtonSave() { return this.button_save; }
    public Button getButtonLoad() { return this.button_load; }
    public Button getButtonIA() { return this.button_ia; }


}
