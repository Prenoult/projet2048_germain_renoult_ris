/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;

/**
 *
 * @author Sylvain
 */
public class Case implements Parametres, Serializable {

    private int x, y, valeur, id;
    private Grille grille;

    /**
     * Constructeur d'une Case
     * @param abs correspond à la coordonnée en abscisse de la case
     * @param ord correspond à la coordonnée en ordonnée de la case
     * @param v correspond à la valeur de la case
     * @param id correspond à l'idée de la case
     */
    public Case(int abs, int ord, int v, int id) {
        this.x = abs;
        this.y = ord;
        this.valeur = v;
        this.id = id;
    }

    public void setGrille(Grille g) {
        this.grille = g;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return this.valeur;
    }


    public void setID(int id) { this.id = id; }

    public int getID() { return this.id; }

    @Override
    /**
     * Fonction permettant de savoir si deux cases sont identiques
     */
    public boolean equals(Object obj) { // la méthode equals est utilisée lors de l'ajout d'une case à un ensemble pour vérifier qu'il n'y a pas de doublons (teste parmi tous les candidats qui ont le même hashcode)
        if (obj instanceof Case) {
            Case c = (Case) obj;
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { // détermine le hashcode
        return this.x * 7 + this.y * 13;
    }

    /**
     * Fonction permettant de savoir si deux cases ont la même valeur
     * @param c case à comparer
     * @return true si les cases ont la même valeur et false dans le cas contraire
     */
    public boolean valeurEgale(Case c) {
        if (c != null) {
            return this.valeur == c.valeur;
        } else {
            return false;
        }
    }

    /**
     * Fonction permettant de savoir si la Case à une voisine direct dans la direction rentrée en param
     * @param direction correspond au "coté" que l'on souhaite verifier
     * @return true si la case à une case voisine direct dans la direction et false dans le cas contraire
     */
    public Case getVoisinDirect(int direction) {
        if (direction == HAUT) {
            for (int i = this.y - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == BAS) {
            for (int i = this.y + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == GAUCHE) {
            for (int i = this.x - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        } else if (direction == DROITE) {
            for (int i = this.x + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.valeur + ")";
    }

}
