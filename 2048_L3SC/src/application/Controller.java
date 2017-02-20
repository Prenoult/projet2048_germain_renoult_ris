package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import modele.Case;
import modele.Grille;

import java.util.HashSet;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static modele.Parametres.*;
import static modele.Parametres.OBJECTIF;

public class Controller extends Parent {

    private Grille grille = new Grille();

    private HashSet<Tuile> tuiles = new HashSet<>();
    private HashSet<Case> cases = grille.getGrille();
    private Case c1;

    public Controller() {
        Background background = new Background(); // on créé la grille (le background)
        this.getChildren().add(background);

        boolean b = grille.nouvelleCase();
        boolean b2 = grille.nouvelleCase();
        System.out.println(this.grille); // affichage de la grille dans la console


        afficherTuiles();

        lancerIA();
        //this.addEventHandler(EventType.ROOT, new GenericHandler()); // ajout d'un gestionnaire d'événements
    }

    public void lancerIA() {
        this.c1 = null;
        Case c2 = null;
        int i = 0;
        // on récuperre les deux cases
        for (Case c : cases) {
            if (i == 0) {
                c1 = c;
            } else {
                c2 = c;
            }
            i++;
        }

        // on met la classe avec la plus grande valeur en c1
        if (c1.getValeur() < c2.getValeur()) {
            Case tmp = c2;
            c2 = c1;
            c1 = tmp;
        }

        boolean b1 = initIa(0, c1, c2);
        System.out.println(grille);
        if (b1) {
            grille.nouvelleCase();
            for (Tuile tuile : tuiles) {
                tuile.setVisible(false);
            }
            tuiles.removeAll(tuiles);
            afficherTuiles();

            if (!b1) grille.gameOver();
            if (grille.getValeurMax() >= OBJECTIF) grille.victory();
            // try{
            //   Thread.sleep(5000);
            //}catch(InterruptedException e){}
        }
        System.out.println("     ");
        System.out.println("     ");
        System.out.println(grille);
        boolean b2 = initIa(1, c1, c2);


        if (b2) {
            grille.nouvelleCase();
            for (Tuile tuile : tuiles) {
                tuile.setVisible(false);
            }
            tuiles.removeAll(tuiles);
            afficherTuiles();

            if (b2 == false) grille.gameOver();
        }
        System.out.println("     ");
        System.out.println("     ");
        System.out.println(grille);
        if (grille.getValeurMax() >= OBJECTIF) grille.victory();
        boolean loop = true;
        for (Case c : cases) {
            if (c != c1 && c.getValeur() > c1.getValeur()) {
                if ((c.getY() == 0 && c.getX() == 0)|| (c.getY() == 0 && c.getX() == 3) || (c.getY() == 3 && c.getX() == 0) || (c.getY() == 3 && c.getX() == 3) ) {
                    c1 = c;
                }

                if(c.getX()==0){
                    if (c1.getVoisinDirect(HAUT) == null) {
                        boolean b = grille.lanceurDeplacerCases(HAUT);
                        c1=c;
                        if (b) {
                            grille.nouvelleCase();
                            for (Tuile tuile : tuiles) {
                                tuile.setVisible(false);
                            }
                            tuiles.removeAll(tuiles);
                            afficherTuiles();
                        }
                    }
                    if (c1.getVoisinDirect(BAS) == null) {
                        boolean b = grille.lanceurDeplacerCases(BAS);
                        c1=c;
                        if (b) {
                            grille.nouvelleCase();
                            for (Tuile tuile : tuiles) {
                                tuile.setVisible(false);
                            }
                            tuiles.removeAll(tuiles);
                            afficherTuiles();
                        }
                    }
                }

                if(c.getX()==3){
                    if (c1.getVoisinDirect(HAUT) == null) {
                        boolean b = grille.lanceurDeplacerCases(HAUT);
                        c1=c;
                        if (b) {
                            grille.nouvelleCase();
                            for (Tuile tuile : tuiles) {
                                tuile.setVisible(false);
                            }
                            tuiles.removeAll(tuiles);
                            afficherTuiles();
                        }
                    }
                    if (c1.getVoisinDirect(BAS) == null) {
                        boolean b = grille.lanceurDeplacerCases(BAS);
                        c1=c;
                        if (b) {
                            grille.nouvelleCase();
                            for (Tuile tuile : tuiles) {
                                tuile.setVisible(false);
                            }
                            tuiles.removeAll(tuiles);
                            afficherTuiles();
                        }
                    }
                }

                if(c.getY()==0){
                    if (c1.getVoisinDirect(DROITE) == null) {
                        boolean b = grille.lanceurDeplacerCases(DROITE);
                        c1=c;
                        if (b) {
                            grille.nouvelleCase();
                            for (Tuile tuile : tuiles) {
                                tuile.setVisible(false);
                            }
                            tuiles.removeAll(tuiles);
                            afficherTuiles();
                        }
                    }
                    if (c1.getVoisinDirect(GAUCHE) == null) {
                        boolean b = grille.lanceurDeplacerCases(GAUCHE);
                        c1=c;
                        if (b) {
                            grille.nouvelleCase();
                            for (Tuile tuile : tuiles) {
                                tuile.setVisible(false);
                            }
                            tuiles.removeAll(tuiles);
                            afficherTuiles();
                        }
                    }
                }

                if(c.getY()==3){
                    if (c1.getVoisinDirect(DROITE) == null) {
                        boolean b = grille.lanceurDeplacerCases(DROITE);
                        c1=c;
                        if (b) {
                            grille.nouvelleCase();
                            for (Tuile tuile : tuiles) {
                                tuile.setVisible(false);
                            }
                            tuiles.removeAll(tuiles);
                            afficherTuiles();
                        }
                    }
                    if (c1.getVoisinDirect(GAUCHE) == null) {
                        boolean b = grille.lanceurDeplacerCases(GAUCHE);
                        c1=c;
                        if (b) {
                            grille.nouvelleCase();
                            for (Tuile tuile : tuiles) {
                                tuile.setVisible(false);
                            }
                            tuiles.removeAll(tuiles);
                            afficherTuiles();
                        }
                    }
                }
                //
            }
        }
        while (!grille.partieFinie() && loop) {
            loop = deplacerIa(c1);
            if (loop) {
                grille.nouvelleCase();
                for (Tuile tuile : tuiles) {
                    tuile.setVisible(false);
                }
                tuiles.removeAll(tuiles);
                afficherTuiles();

                if (!loop) grille.gameOver();
            }
            System.out.println("     ");
            System.out.println(grille);
            System.out.println(" ");
            if (grille.getValeurMax() >= OBJECTIF) grille.victory();
        }
        //System.out.println("       " + c1 + loop);

    }

    private Boolean deplacerIa(Case c1) {
        //System.out.println(c1);
        Case tmp;
        int direction = 0;
        //identifier le coin de c1
        //coin 0 : haut gauche; coin 1 : haut droite; coin 2 : bas gauche coin 3 : bas droite
        int coin = -1;
        if (c1.getX() == 0 && c1.getY() == 0) {
            coin = 0;
        }
        if (c1.getX() == 3 && c1.getY() == 0) {
            coin = 1;
        }
        if (c1.getX() == 0 && c1.getY() == 3) {
            coin = 2;
        }
        if (c1.getX() == 3 && c1.getY() == 3) {
            coin = 3;
        }


        if (coin != -1) {
            //Si la case est dans un coin

            //Voir si on peut fusionner c1
            if (coin == 0) {
                tmp = c1.getVoisinDirect(DROITE);
                if (c1.valeurEgale(tmp)) {
                    direction = GAUCHE;
                } else {
                    tmp = c1.getVoisinDirect(BAS);
                    if (c1.valeurEgale(tmp)) {
                        direction = HAUT;
                    }
                }
            }

            if (coin == 1) {
                tmp = c1.getVoisinDirect(GAUCHE);
                if (c1.valeurEgale(tmp)) {
                    direction = DROITE;
                } else {
                    tmp = c1.getVoisinDirect(BAS);
                    if (c1.valeurEgale(tmp)) {
                        direction = HAUT;
                    }
                }
            }

            if (coin == 2) {
                tmp = c1.getVoisinDirect(DROITE);
                if (c1.valeurEgale(tmp)) {
                    direction = GAUCHE;
                } else {
                    tmp = c1.getVoisinDirect(HAUT);
                    if (c1.valeurEgale(tmp)) {
                        direction = BAS;
                    }
                }
            }

            if (coin == 3) {
                tmp = c1.getVoisinDirect(GAUCHE);
                if (c1.valeurEgale(tmp)) {
                    direction = DROITE;
                } else {
                    tmp = c1.getVoisinDirect(HAUT);
                    if (c1.valeurEgale(tmp)) {
                        direction = BAS;
                    }
                }
            }

            if (direction != 0) {
                boolean b = grille.lanceurDeplacerCases(direction);
                //SORT DE LA FONCTION
                System.out.println("sort");
                return b;
            } else {
                //Voir si on fusionner des cases de la ligne de c1
                for (Case c : cases) {
                    if (c.getX() != c1.getX() && c.getY() == c1.getY()) {
                        if (c1.getY() == 0) {
                            tmp = c.getVoisinDirect(BAS);
                            if (c.valeurEgale(tmp)) {
                                direction = HAUT;
                            } else {
                                if (c1.getX() == 0) {
                                    tmp = c.getVoisinDirect(GAUCHE);
                                    if (c.valeurEgale(tmp)) {
                                        direction = GAUCHE;
                                        boolean b = grille.lanceurDeplacerCases(direction);
                                        //SORT DE LA FONCTION
                                        return b;
                                    } else {
                                        tmp = c.getVoisinDirect(DROITE);
                                        if (c.valeurEgale(tmp)) {
                                            direction = GAUCHE;
                                            boolean b = grille.lanceurDeplacerCases(direction);
                                            //SORT DE LA FONCTION
                                            return b;
                                        }
                                    }
                                } else {
                                    tmp = c.getVoisinDirect(GAUCHE);
                                    if (c.valeurEgale(tmp)) {
                                        direction = DROITE;
                                        boolean b;
                                        b = grille.lanceurDeplacerCases(direction);
                                        //SORT DE LA FONCTION
                                        return b;
                                    } else {
                                        tmp = c.getVoisinDirect(DROITE);
                                        if (c.valeurEgale(tmp)) {
                                            direction = 0;
                                            direction = DROITE;
                                            boolean b = grille.lanceurDeplacerCases(direction);
                                            //SORT DE LA FONCTION
                                            return b;
                                        }
                                    }
                                }
                            }
                        } else {
                            tmp = c.getVoisinDirect(HAUT);
                            if (c.valeurEgale(tmp)) {
                                direction = BAS;
                            } else {
                                if (c1.getX() == 0) {
                                    tmp = c.getVoisinDirect(GAUCHE);
                                    if (c.valeurEgale(tmp)) {
                                        direction = 0;
                                        direction = GAUCHE;
                                        boolean b = grille.lanceurDeplacerCases(direction);
                                        //SORT DE LA FONCTION
                                        return b;
                                    } else {
                                        tmp = c.getVoisinDirect(DROITE);
                                        if (c.valeurEgale(tmp)) {
                                            direction = GAUCHE;
                                            boolean b = grille.lanceurDeplacerCases(direction);
                                            //SORT DE LA FONCTION
                                            return b;
                                        }
                                    }
                                } else {
                                    tmp = c.getVoisinDirect(GAUCHE);
                                    if (c.valeurEgale(tmp)) {
                                        direction = DROITE;
                                        boolean b = grille.lanceurDeplacerCases(direction);
                                        //SORT DE LA FONCTION
                                        return b;
                                    } else {
                                        tmp = c.getVoisinDirect(DROITE);
                                        if (c.valeurEgale(tmp)) {
                                            direction = 0;
                                            direction = DROITE;
                                            boolean b = grille.lanceurDeplacerCases(direction);
                                            //SORT DE LA FONCTION
                                            return b;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //Voir si on fusionner des cases de la colonne de c1
                for (Case c : cases) {
                    if (c.getX() == c1.getX() && c.getY() != c1.getY()) {
                        if (c1.getX() == 0) {
                            //gauche
                            tmp = c.getVoisinDirect(DROITE);
                            if (c.valeurEgale(tmp)) {
                                direction = GAUCHE;
                                boolean b = grille.lanceurDeplacerCases(direction);
                                //SORT DE LA FONCTION
                                return b;
                            } else {
                                //SUITE
                                if (c1.getY() == 0) {
                                    tmp = c.getVoisinDirect(HAUT);
                                    if (c.valeurEgale(tmp)) {
                                        direction = HAUT;
                                        boolean b = grille.lanceurDeplacerCases(direction);
                                        //SORT DE LA FONCTION
                                        return b;
                                    } else {
                                        tmp = c.getVoisinDirect(BAS);
                                        if (c.valeurEgale(tmp)) {
                                            direction = HAUT;
                                            return grille.lanceurDeplacerCases(direction);
                                        }
                                    }
                                } else {
                                    tmp = c.getVoisinDirect(HAUT);
                                    if (c.valeurEgale(tmp)) {
                                        direction = BAS;
                                        boolean b;
                                        b = grille.lanceurDeplacerCases(direction);
                                        //SORT DE LA FONCTION
                                        return b;
                                    } else {
                                        tmp = c.getVoisinDirect(BAS);
                                        if (c.valeurEgale(tmp)) {
                                            direction = BAS;
                                            return grille.lanceurDeplacerCases(direction);
                                        }
                                    }
                                }
                            }
                        } else {
                            //droite
                            tmp = c.getVoisinDirect(GAUCHE);
                            if (c.valeurEgale(tmp)) {
                                direction = DROITE;
                                boolean b = grille.lanceurDeplacerCases(direction);
                                //SORT DE LA FONCTION
                                return b;
                            } else {
                                //SUITE
                                if (c1.getY() == 0) {
                                    tmp = c.getVoisinDirect(HAUT);
                                    if (c.valeurEgale(tmp)) {
                                        direction = HAUT;
                                        boolean b;
                                        b = grille.lanceurDeplacerCases(direction);
                                        //SORT DE LA FONCTION
                                        return b;
                                    } else {
                                        tmp = c.getVoisinDirect(BAS);
                                        if (c.valeurEgale(tmp)) {
                                            direction = HAUT;
                                            boolean b = grille.lanceurDeplacerCases(direction);
                                            return b;
                                        }
                                    }
                                } else {
                                    tmp = c.getVoisinDirect(HAUT);
                                    if (c.valeurEgale(tmp)) {
                                        direction = BAS;
                                        boolean b;
                                        return grille.lanceurDeplacerCases(direction);
                                        //SORT DE LA FONCTION

                                    } else {
                                        tmp = c.getVoisinDirect(BAS);
                                        if (c.valeurEgale(tmp)) {
                                            direction = BAS;
                                            return grille.lanceurDeplacerCases(direction);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

            }
            //Voir si on peut se deplacer sans bouger c1
            //CAS 0
            if (coin == 0) {
                boolean b = grille.lanceurDeplacerCases(GAUCHE);
                if (b) {
                    return b;
                } else {
                    b = grille.lanceurDeplacerCases(HAUT);
                    if (b) return b;
                }
            }
            //CAS 1
            if (coin == 1) {
                boolean b = grille.lanceurDeplacerCases(DROITE);
                if (b) {
                    return b;
                } else {
                    b = false;
                    b = grille.lanceurDeplacerCases(HAUT);
                    if (b) return b;
                }
            }

            //CAS 2
            if (coin == 2) {
                boolean b;
                b = grille.lanceurDeplacerCases(GAUCHE);
                if (b) {
                    return b;
                } else {
                    b = grille.lanceurDeplacerCases(BAS);
                    if (b) return b;
                }
            }

            //CAS 3
            if (coin == 3) {
                boolean b = grille.lanceurDeplacerCases(DROITE);
                if (b) {
                    return b;
                } else {
                    b = grille.lanceurDeplacerCases(BAS);
                    if (b) return b;
                }
            }

            //Obligation de deplacer c1
            if (coin == 0) {
                int j = 0;
                for (Case c : cases) {
                    if (c.getX() != c1.getX() && c.getY() == c1.getY()) {
                        j++;
                    }
                    if (j == 4) {
                        boolean b = grille.lanceurDeplacerCases(DROITE);
                        if (b) return b;
                    } else {
                        boolean b = grille.lanceurDeplacerCases(BAS);
                        if (b) return b;
                    }
                }
            }
            //CAS 1
            if (coin == 1) {
                int j = 0;
                for (Case c : cases) {
                    if (c.getX() != c1.getX() && c.getY() == c1.getY()) {
                        j++;
                    }
                    if (j == 4) {
                        boolean b;
                        b = grille.lanceurDeplacerCases(GAUCHE);
                        if (b) return b;
                    } else {
                        boolean b = grille.lanceurDeplacerCases(BAS);
                        if (b) return b;
                    }
                }
            }

            //CAS 2
            if (coin == 2) {
                int j = 0;
                for (Case c : cases) {
                    if (c.getX() != c1.getX() && c.getY() == c1.getY()) {
                        j++;
                    }
                    if (j == 4) {
                        boolean b = grille.lanceurDeplacerCases(DROITE);
                        if (b) return b;
                    } else {
                        boolean b;
                        b = grille.lanceurDeplacerCases(HAUT);
                        if (b) return b;
                    }
                }
            }

            //CAS 3
            if (coin == 3) {
                int j = 0;
                for (Case c : cases) {
                    if (c.getX() != c1.getX() && c.getY() == c1.getY()) {
                        j++;
                    }
                    if (j == 4) {
                        boolean b;
                        b = grille.lanceurDeplacerCases(GAUCHE);
                        if (b) return b;
                    } else {
                        boolean b;
                        b = grille.lanceurDeplacerCases(HAUT);
                        if (b) return b;
                    }
                }
            }


        } else {

            // Dans le cas ou la case n'est pas dans un coin


            //Cas ou la case principale est sur une arrete
            if (c1.getX() == 0) {
                if (c1.getVoisinDirect(HAUT) == null) {
                    boolean b = grille.lanceurDeplacerCases(HAUT);
                    if (b) return b;
                }
                if (c1.getVoisinDirect(BAS) == null) {
                    boolean b = grille.lanceurDeplacerCases(BAS);
                    if (b) return b;
                }
                //

                boolean b = grille.lanceurDeplacerCases(GAUCHE);
                if (b) {
                    return b;
                }

                if (c1.getY() < 2) {
                    b = grille.lanceurDeplacerCases(HAUT);
                    if (b) return b;
                } else {
                    b = grille.lanceurDeplacerCases(BAS);
                    if (b) return b;
                }

            }

            if (c1.getX() == 3) {
                if (c1.getVoisinDirect(HAUT) == null) {
                    boolean b = grille.lanceurDeplacerCases(HAUT);
                    if (b) return b;
                }
                if (c1.getVoisinDirect(BAS) == null) {
                    boolean b = grille.lanceurDeplacerCases(BAS);
                    if (b) return b;
                }
                //
                boolean b;
                b = grille.lanceurDeplacerCases(DROITE);
                if (b) {
                    return b;
                }

                if (c1.getY() < 2) {
                    b = grille.lanceurDeplacerCases(HAUT);
                    if (b) return b;
                } else {
                    b = grille.lanceurDeplacerCases(BAS);
                    if (b) return b;
                }

            }

            if (c1.getY() == 0) {
                if (c1.getVoisinDirect(DROITE) == null) {
                    boolean b = grille.lanceurDeplacerCases(DROITE);
                    if (b) return b;
                }
                if (c1.getVoisinDirect(GAUCHE) == null) {
                    boolean b = grille.lanceurDeplacerCases(GAUCHE);
                    if (b) return b;
                }
                //
                boolean b = grille.lanceurDeplacerCases(HAUT);
                if (b) {
                    return b;
                }

                if (c1.getX() < 2) {
                    b = grille.lanceurDeplacerCases(GAUCHE);
                    if (b) return b;
                } else {
                    b = grille.lanceurDeplacerCases(DROITE);
                    if (b) return b;
                }

            }

            if (c1.getY() == 3) {
                if (c1.getVoisinDirect(DROITE) == null) {
                    boolean b = grille.lanceurDeplacerCases(DROITE);
                    if (b) return b;
                }
                if (c1.getVoisinDirect(GAUCHE) == null) {
                    boolean b = grille.lanceurDeplacerCases(GAUCHE);
                    if (b) return b;
                }
                boolean b;
                b = grille.lanceurDeplacerCases(BAS);
                if (b) {
                    return b;
                }

                if (c1.getX() < 2) {
                    b = grille.lanceurDeplacerCases(GAUCHE);
                    if (b) return b;
                } else {
                    b = grille.lanceurDeplacerCases(DROITE);
                    if (b) return b;
                }

                //

            }

            //fusionne la meilleure case possible
            int d=0;
            Case cmax = new Case(-1,-1,-1,-1);
            for (Case c : cases){
                if(c.getValeur()>cmax.getValeur()){
                    if (c.getVoisinDirect(DROITE).valeurEgale(c)){
                        d=DROITE;
                        cmax=c;
                    }
                    if (c.getVoisinDirect(GAUCHE).valeurEgale(c)){
                        d=GAUCHE;
                        cmax=c;
                    }
                    if (c.getVoisinDirect(HAUT).valeurEgale(c)){
                        d=HAUT;
                        cmax=c;
                    }
                    if (c.getVoisinDirect(BAS).valeurEgale(c)){
                        d=BAS;
                        cmax=c;
                    }
                }
            }
            if(d!=0){
                boolean b = grille.lanceurDeplacerCases(d);
                return b;
            }


        }

        return false;
    }

    /**
     * @param i  permet de savoir si on doit depalcer sur l'axe droite/gauche (i=0) ou l'axe haut/bas (i=1)
     * @param c1 Case Une, case avec la valeur la plus grande
     * @param c2 Case Deux, case avec la valeur la plus petite
     * @return un boolean permettant de savoir si oui ou non il y a eu le deplacement
     */
    public boolean initIa(int i, Case c1, Case c2) {
        int direction = 1;
        if (i == 0) {
            if (c1.getX() > c2.getX()) {
                //mouvement à droite
                direction = DROITE;
                boolean b2 = grille.lanceurDeplacerCases(direction);
                return b2;
                //System.out.println("    droite  "+b2);
            } else {
                //mouvement à gauche
                direction = GAUCHE;
                boolean b2 = grille.lanceurDeplacerCases(direction);
                return b2;
                //System.out.println("    Gauche  "+b2);

            }
        } else {
            if (c1.getY() > c2.getY()) {
                //mouvement en Bas
                direction = BAS;
                boolean b2 = grille.lanceurDeplacerCases(direction);
                return b2;
                //System.out.println("    Bas  "+b2);
            } else {
                //mouvement en Haut
                direction = HAUT;
                boolean b2 = grille.lanceurDeplacerCases(direction);
                return b2;
                //System.out.println("    Haut  "+b2);

            }
        }

    }

    public void afficherTuiles() {
        for (Case c : cases) {
            //System.out.println(c);
            Tuile tuile = new Tuile(c.getValeur(), c.getID());
            tuile.convertirPositionCase(c);
            tuile.location(tuile.getx(), tuile.gety());
            tuile.setVisible(true);
            tuiles.add(tuile);
            this.getChildren().add(tuile);
        }
    }


    private class GenericHandler implements EventHandler<Event> {

        private int direction = 0;

        @Override
        public void handle(Event event) {
            if (grille.partieFinie()) // si la partie est finie
                grille.gameOver(); // game over, on quitte l'application

            if (event.getEventType() == KEY_PRESSED) {
                KeyEvent keyEvent = (KeyEvent) event;
                switch (keyEvent.getText()) {
                    case "z":
                        direction = HAUT;
                        break;
                    case "s":
                        direction = BAS;
                        break;
                    case "q":
                        direction = GAUCHE;
                        break;
                    case "d":
                        direction = DROITE;
                        break;
                }

                boolean b2 = grille.lanceurDeplacerCases(direction);

                /*for(Case ca : cases) {
                    for(Tuile tuile : tuiles) {
                        //tuile.modifierObjectif(ca);
                        //if(tuile.getID() == ca.getID()) {


                        /*switch (keyEvent.getText()) {
                            case "z":
                                if (tuile.getObjectify() > 215) { // possible uniquement si on est pas sur la ligne la plus en haut
                                    boolean autorise = true;
                                    for(application.Case t : tuiles) {
                                        if(tuile.getx() == t.getx() && t.gety() == tuile.gety() - 147) {
                                            System.out.println("OIHJOIDHNUIJGDSHJNSUIHSIH");
                                            //tuile.setObjectify(tuile.getObjectify() - 147);
                                            autorise = false;
                                        }
                                    }
                                    if(autorise) {
                                        //objectify = c.getObjectify();
                                        tuile.setObjectify(tuile.getObjectify() - 147); // on définit la position que devra atteindre la tuile en ordonné (modèle). Le thread se chargera de mettre la vue à jour
                                        //c.setObjectify(objectify);
                                        direction = HAUT;
                                    }
                                }
                                break;
                            case "s":
                                if (tuile.getObjectify() < 656) { // possible uniquement si on est pas sur la ligne la plus en bas
                                    //boolean autorise = true;
                                    //for(application.Case t : tuiles) {
                                        //if(tuile.getx() == t.getx() && t.gety() == tuile.gety() + 147) {
                                        //   autorise = false;
                                        //}
                                    //}
                                    //if(autorise) {
                                        //objectify = c.getObjectify();
                                        tuile.setObjectify(tuile.getObjectify() + 147); // on définit la position que devra atteindre la tuile en ordonné (modèle). Le thread se chargera de mettre la vue à jour
                                        //c.setObjectify(objectify);
                                        direction = BAS;
                                    //}
                                }
                                break;
                            case "q":
                                if (tuile.getObjectifx() > 165) { // possible uniquement si on est pas dans la colonne la plus à droite
                                    //objectifx = c.getObjectifx();
                                    tuile.setObjectifx(tuile.getObjectifx() - 147); // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                                    //c.setObjectifx(objectifx);
                                    direction = GAUCHE;
                                }
                                break;
                            case "d":
                                if (tuile.getObjectifx() < 606) { // possible uniquement si on est pas dans la colonne la plus à gauche
                                    //objectifx = c.getObjectifx();
                                    tuile.setObjectifx(tuile.getObjectifx() + 147); // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                                    //c.setObjectifx(objectifx);
                                    direction = DROITE;
                                }
                                /* 165 - 312 - 459 - 606 */
                                /*break;
                        }
                        }
                    }
                }*/

                if (b2) {
                    boolean b = grille.nouvelleCase();
                    for (Tuile tuile : tuiles) {
                        tuile.setVisible(false);
                    }
                    tuiles.removeAll(tuiles);
                    afficherTuiles();

                    if (!b) grille.gameOver();
                }
                System.out.println(grille);
                if (grille.getValeurMax() >= OBJECTIF) grille.victory();
                direction = 0;
            }
            /* Verifier que la grille n'est pas pleine. Si c'est le cas -> game over */
            for (Tuile tuile : tuiles) {
                Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                    @Override
                    public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                        while (tuile.getx() != tuile.getObjectifx()) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                            if (tuile.getx() < tuile.getObjectifx()) {
                                //x += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                tuile.setx(tuile.getx() + 1);
                            } else {
                                //x -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                                tuile.setx(tuile.getx() - 1);
                            }
                            // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                            Platform.runLater(new Runnable() { // classe anonyme
                                @Override
                                public void run() {
                                    //javaFX operations should go here
                                    tuile.relocate(tuile.getx(), tuile.gety()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                    tuile.setVisible(true);
                                }
                            });
                            Thread.sleep(5);
                        } // end while
                        while (tuile.gety() != tuile.getObjectify()) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                            if (tuile.gety() < tuile.getObjectify()) {
                                //y += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                                tuile.sety(tuile.gety() + 1);
                            } else {
                                //y -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                                tuile.sety(tuile.gety() - 1);
                            }
                            // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                            Platform.runLater(new Runnable() { // classe anonyme
                                @Override
                                public void run() {
                                    //javaFX operations should go here
                                    tuile.relocate(tuile.getx(), tuile.gety()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                    tuile.setVisible(true);
                                }
                            });
                            Thread.sleep(5);
                        } // end while
                        return null;
                    }
                    //return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                }; // end call
                Thread th = new Thread(task); // on crée un contrôleur de Thread
                th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
                th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
            }

        }

    }


}
