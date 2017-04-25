package application;

import modele.Case;
import modele.Grille;
import modele.Parametres;

import java.util.HashSet;

/**
 * Created by jerem on 25/04/2017.
 */
public class IA  implements Parametres {

    private Controller controller;

    private Case caseMax;
    private Grille grille;
    private HashSet<Tuile> tuiles;
    private  HashSet<Case> cases;

    public IA(Controller controller) {
        this.controller = controller;
        this.caseMax = controller.getCaseMax();
        this.grille = controller.getGrille();
        this.tuiles = controller.getTuiles();
        this.cases = controller.getCases();
    }

    public void lancerIA(/*Case caseMax, Grille grille, HashSet<Case> cases, HashSet<Tuile> tuiles*/) {
        Case c1 = caseMax;
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
            controller.afficherTuiles();

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
            controller.afficherTuiles();

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
                            controller.afficherTuiles();
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
                            controller.afficherTuiles();
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
                            controller.afficherTuiles();
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
                            controller.afficherTuiles();
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
                            controller.afficherTuiles();
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
                            controller.afficherTuiles();
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
                            controller.afficherTuiles();
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
                            controller.afficherTuiles();
                        }
                    }
                }
                //
            }
        }
        //while (!grille.partieFinie() && loop) {
            loop = deplacerIa(c1);
            if (loop) {
                grille.nouvelleCase();
                for (Tuile tuile : tuiles) {
                    tuile.setVisible(false);
                }
                tuiles.removeAll(tuiles);
                controller.afficherTuiles();

                if (!loop) grille.gameOver();
            }
            System.out.println("     ");
            System.out.println(grille);
            System.out.println(" ");
            if (grille.getValeurMax() >= OBJECTIF) grille.victory();
        //}
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
}
