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

    public Controller() {
        Background background = new Background(); // on créé la grille (le background)
        this.getChildren().add(background);

        boolean b = grille.nouvelleCase();
        boolean b2 = grille.nouvelleCase();
        System.out.println(this.grille); // affichage de la grille dans la console


        afficherTuiles();


        this.addEventHandler(EventType.ROOT, new GenericHandler()); // ajout d'un gestionnaire d'événements
    }

    public void afficherTuiles() {
        for (Case c : cases) {
            System.out.println(c);
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

            if(event.getEventType() == KEY_PRESSED) {
                KeyEvent keyEvent = (KeyEvent)event;
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
                    for(Tuile tuile : tuiles) {
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
            for(Tuile tuile : tuiles) {
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
