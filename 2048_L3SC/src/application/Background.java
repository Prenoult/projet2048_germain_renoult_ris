package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by jerem on 21/01/2017.
 */
public class Background extends Parent {

    GridPane grid = new GridPane();;

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


        // Positionnement de la grille au centre de la fenêtre
        this.setTranslateX(150);
        this.setTranslateY(200);
        this.getChildren().add(fond);
        this.getChildren().add(grid);
        this.setFocusTraversable(true);
    }


}
