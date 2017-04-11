package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by jerem on 28/01/2017.
 */
public class Tuile extends Parent {

    private Pane p = new Pane();
    private Label c = new Label();
    private int x = 165, y = 215;
    private int objectifx = 165, objectify = 215;
    private int valeur;
    private int id;

    /**
     * Constructeur de la Tuil
     * Une tuile est la partie graphique/visible de la case
     * @param v correspond à la valeur de la Tuile
     * @param id correspond à l'id de la Tuile
     * Cette fonction permet de creer une nouvelle Tuile, qui aura comme valeur le param v
     */
    public Tuile(int v, int id) {
        this.id = id;
        this.valeur = v;
        c.setText(Integer.toString(this.valeur));
        p.getStyleClass().add("pane"); // ajout des styles
        p.getStyleClass().add("tuile" + this.valeur);
        c.getStyleClass().add("tuile");
        if(this.valeur == 2 || this.valeur == 4)
            c.getStyleClass().add("color_tuile2_4");
        p.getChildren().add(c);
        p.setLayoutX(this.x);
        p.setLayoutY(this.y);
        //p.setVisible(true);
        //c.setVisible(true);
        this.getChildren().add(p);
    }

    /**
     * Cette fonction permet d'afficher et de sauvagarder la position de la tuile aux coordonnées rentrées
     * @param x coordonnée en X
     * @param y coordonnée en Y
     */
    public void location(int x, int y) {
        p.setLayoutX(x); // taille de la tuile
        p.setLayoutY(y);
        p.setVisible(true);
        c.setVisible(true);
    }

    /**
     *Fonction permettant de convertir les positions de la tuile sur la grille (4*4) à la postion de la grille graphique (en pixel)
     * @param c
     */
    public void convertirPositionCase(modele.Case c) {
        switch(c.getX()) {
            case 0:
                objectifx = 165;
                x = 165;
                break;
            case 1:
                objectifx = 312;
                x = 312;
                break;
            case 2:
                objectifx = 459;
                x = 459;
                break;
            case 3:
                objectifx = 606;
                x = 606;
                break;
        }

        switch(c.getY()) {
            case 0:
                objectify = 215;
                y = 215;
                break;
            case 1:
                objectify = 362;
                y = 362;
                break;
            case 2:
                objectify = 509;
                y = 509;
                break;
            case 3:
                objectify = 656;
                y = 656;
                break;
        }
    }

    /**
     * Fonction permettant de modifier la valeur d'une tuile
     * @param v nouvelle valeur de la tuile
     */
    public void setValeur(int v) {
        this.valeur = v;
        this.c.setText(Integer.toString(this.valeur));
    }


    public void setx(int x) { this.x = x; }
    public int getx() {return this.x; }

    public void sety(int y) { this.y = y; }
    public int gety() {return this.y; }

    public void setObjectifx(int x) { this.objectifx = x; }
    public int getObjectifx() {return this.objectifx; }

    public void setObjectify(int y) { this.objectify = y; }
    public int getObjectify() {return this.objectify; }

    public void setID(int id) { this.id = id; }
    public int getID() {return this.id; }

}
