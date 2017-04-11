package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import modele.Case;
import modele.Grille;
import modele.Parametres;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static modele.Parametres.*;
import static modele.Parametres.OBJECTIF;

public class Controller extends Parent implements Parametres {

    private Grille grille = new Grille();

    private HashSet<Tuile> tuiles = new HashSet<>();
    private HashSet<Case> cases = grille.getGrille();

    private boolean animationFini = false;

    /**
     * Fonction permettant d'appeler d'autre fonction selon l'action de l'utilisateur
     * Cette fonction permet de creer la grille puis de faire une nouvelle partie, de sauvegarder la partie, de jouer en multijoueur ou de faire appelle à l'IA selon le bouton cliqué
     */
    public Controller() {
        Background background = new Background(); // on créé la grille (le background)
        this.getChildren().add(background);
        background.getButtonNew().setOnAction(actionEvent -> newGame());
        background.getButtonSave().setOnAction(actionEvent -> save());
        background.getButtonLoad().setOnAction(actionEvent -> load());
       //background.getButtonIA().setOnAction(actionEvent -> IA());
        background.getButtonIA().setOnAction(actionEvent -> multijoueur());

        // Si la partie à été sauvegardée on la charge
        File fichierSauvegarde = new File("grille.ser");
        /*if(fichierSauvegarde.exists()) { // si le fichier de sauvegarde existe
            charger(); // on le charge
            cases = grille.getGrille(); // et on met à jour la liste des cases
        } else {*/ // sinon on initialise une nouvelle grille avec deux cases aléatoires
            boolean b = grille.nouvelleCase();
            boolean b2 = grille.nouvelleCase();
        /*}*/

        System.out.println(this.grille); // affichage de la grille dans la console

        afficherTuiles();

        this.addEventHandler(EventType.ROOT, new GenericHandler()); // ajout d'un gestionnaire d'événements
        //IA();
    }

    /**
     * Fonction permettant d'afficher les tuiles (cases) sur la grille visible
     * Pour cela la fonction recupere toutes les cases et leurs coordonnées et les affiches à ces coordonnées
     */
    public void afficherTuiles() {
        for (Case c : cases) {
            System.out.println(c);
            System.out.println(c.getID());
            Tuile tuile = new Tuile(c.getValeur(), c.getID());
            tuile.convertirPositionCase(c);
            tuile.location(tuile.getx(), tuile.gety());
            tuile.setVisible(true);
            tuiles.add(tuile);
            this.getChildren().add(tuile);
        }
    }

    /**
     * methode permettant d'afficher une tuile (case)
     * @param c correspond à la case que l'on souhaite afficher
     */
    public void afficherTuile(Case c) {
        Tuile tuile = new Tuile(c.getValeur(), c.getID());
        tuile.convertirPositionCase(c);
        tuile.location(tuile.getx(), tuile.gety());
        tuile.setVisible(true);
        tuiles.add(tuile);
        this.getChildren().add(tuile);
    }

    /**
     * Fonction permettant de créer une nouvelle partie
     * Pour cela on créer une nouvelle grille, on vide la liste de cases et l'on ajoute deux nouvelles cases à cette grille
     */
    public void newGame() {
        grille = new Grille();
        cases = grille.getGrille();
        boolean b = grille.nouvelleCase();
        boolean b2 = grille.nouvelleCase();
        for(Tuile tuile : tuiles) {
            tuile.setVisible(false);
        }
        tuiles.removeAll(tuiles);
        afficherTuiles();
    }

    /**
     * Fonction permettant de faire la sauvegarde d'une partie
     */
    public void save() {
        // try-with-resources ferme automatiquement les ressources -> pas besoin de .close()
        try  (FileOutputStream fichier = new FileOutputStream("grille.ser");
              ObjectOutputStream oos = new ObjectOutputStream(fichier)) {
            oos.writeObject(grille);
            oos.flush();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction permettant de charger une partie
     */
    public void load() {
        try (FileInputStream fichierIn = new FileInputStream("grille.ser");
             ObjectInputStream ois = new ObjectInputStream(fichierIn)) {
            grille = (Grille) ois.readObject();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        // On supprime toutes les cases qui apparaissent actuellement à l'écran
        for(Tuile tuile : tuiles) {
            tuile.setVisible(false);
        }
        tuiles.removeAll(tuiles);
        // On met à jour la grille et on l'affiche
        cases = grille.getGrille();
        afficherTuiles();
    }

    /**
     * Fonction permettant de jouer en multijoueur
     * Pour celà on se connecte au serveur
     * Puis on configure les entrées
     * Après celà on recupere les informations que le serveur à renvoyé
     * Puis ...
     */
    public void multijoueur() {
        int score = grille.getScore();
        System.out.println("step 1 : on se connecte au serveur");
        try(Socket socket = new Socket(HOST, PORT))  {
            System.out.println("step 2 : on configure les entrées");
            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(reader);
            System.out.println("step 3 : on lit ce que le serveur répond");
            //String line = in.readLine();

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            //out.write(Integer.toString(score)/*line*/);
            //out.flush();
            System.out.println("step 4");

            // Création d'une seconde fenêtre
            Stage stage = new Stage();
            stage.setTitle("2048"); // titre de la fenêtre
            Group root = new Group(); // création d'un groupe
            Scene scene = new Scene(root, 900, 900, Color.WHITE); // initialisation de la fenêtre (blanche 900*900)
            boolean add = scene.getStylesheets().add("css/style.css"); // ajout du fichier css

            root.getChildren().add(new Controller());

            stage.setScene(scene);
            stage.show();

            if(!grille.partieFinie()) {
                out.write(Integer.toString(score)/*line*/);
                out.flush();
            }

        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction permettant de faire appelle à l'IAc
     */
    public void IA() {
        while(!grille.partieFinie()) {
            int direction = HAUT;
            if(!grille.lanceurDeplacerCases(direction)) {
                Grille gr1 = new Grille(grille);
                Grille gr2 = new Grille(grille);
                if(gr1.lanceurDeplacerCases(DROITE) && gr2.lanceurDeplacerCases(GAUCHE)) {
                    //Grille g1 = new Grille(grille);
                    //g1.lanceurDeplacerCases(GAUCHE);
                    int nbCasesVides1 = 16 - gr1.getGrille().size();

                    //Grille g2 = new Grille(grille);
                    //g2.lanceurDeplacerCases(DROITE);
                    int nbCasesVides2 = 16 - gr2.getGrille().size();

                    if(nbCasesVides1 >= nbCasesVides2) {
                        direction = GAUCHE;
                    } else {
                        direction = DROITE;
                    }
                    gr1 = new Grille(grille);
                } else if(gr1.lanceurDeplacerCases(GAUCHE)) {
                    direction = GAUCHE;
                    gr1 = new Grille(grille);
                } else if(gr1.lanceurDeplacerCases(DROITE)) {
                    direction = DROITE;
                    gr1 = new Grille(grille);
                } else {
                    direction = BAS;
                }
            }
            boolean b2 = grille.lanceurDeplacerCases(direction);
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
        grille.gameOver();
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

                /* Test de déplacement fluide */


                //for(Case ca : cases) {
               /* ArrayList id = new ArrayList();
                    for(Tuile tuile : tuiles) {
                        for(Case ca :cases) {
                            //tuile.modifierObjectif(ca);
                            if (tuile.getID() == ca.getID()) {
                                System.out.println("ID = " + ca.getID() + ". Le x de tuile est : " + tuile.getx() + "et celui de la case est : " + ca.getX());
                                id.add(ca.getID());
                                switch(ca.getX()) {
                                    case 0:
                                        tuile.setObjectifx(165);
                                        break;
                                    case 1:
                                        tuile.setObjectifx(312);
                                        break;
                                    case 2:
                                        tuile.setObjectifx(459);
                                        break;
                                    case 3:
                                        tuile.setObjectifx(606);
                                        break;
                                }
                                switch(ca.getY()) {
                                    case 0:
                                        tuile.setObjectify(215);
                                        break;
                                    case 1:
                                        tuile.setObjectify(362);
                                        break;
                                    case 2:
                                        tuile.setObjectify(509);
                                        break;
                                    case 3:
                                        tuile.setObjectify(656);
                                        break;
                                }
                            } else {
                                switch (direction) {
                                    case HAUT:
                                        tuile.setObjectify(215);
                                        break;
                                    case BAS:
                                        tuile.setObjectify(656);
                                        break;
                                    case GAUCHE:
                                        tuile.setObjectifx(165);
                                        break;
                                    case DROITE:
                                        tuile.setObjectifx(606);
                                        break;
                                }
                                //tuile.setObjectifx();
                            }

                        switch (keyEvent.getText()) {
                            case "z":
                                if (tuile.getObjectify() > 215) { // possible uniquement si on est pas sur la ligne la plus en haut
                                    /*boolean autorise = true;
                                    for(application.Case t : tuiles) {
                                        if(tuile.getx() == t.getx() && t.gety() == tuile.gety() - 147) {
                                            System.out.println("OIHJOIDHNUIJGDSHJNSUIHSIH");
                                            //tuile.setObjectify(tuile.getObjectify() - 147);
                                            autorise = false;
                                        }
                                    }
                                    if(autorise) {
                                        //objectify = c.getObjectify();*/
                                        //tuile.setObjectify(tuile.getObjectify() - 147); // on définit la position que devra atteindre la tuile en ordonné (modèle). Le thread se chargera de mettre la vue à jour
                                        //c.setObjectify(objectify);
                                        /*direction = HAUT;
                                    //}
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
                                        //tuile.setObjectify(tuile.getObjectify() + 147); // on définit la position que devra atteindre la tuile en ordonné (modèle). Le thread se chargera de mettre la vue à jour
                                        //c.setObjectify(objectify);
                                        direction = BAS;
                                    //}
                                }
                                break;
                            case "q":
                                if (tuile.getObjectifx() > 165) { // possible uniquement si on est pas dans la colonne la plus à droite
                                    //objectifx = c.getObjectifx();
                                    //tuile.setObjectifx(tuile.getObjectifx() - 147); // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                                    //c.setObjectifx(objectifx);
                                    direction = GAUCHE;
                                }
                                break;
                            case "d":
                                if (tuile.getObjectifx() < 606) { // possible uniquement si on est pas dans la colonne la plus à gauche
                                    //objectifx = c.getObjectifx();
                                    //tuile.setObjectifx(tuile.getObjectifx() + 147); // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                                    //c.setObjectifx(objectifx);
                                    direction = DROITE;
                                }*/
                                /* 165 - 312 - 459 - 606 */
                               /* break;
                        }
                    }
                   // }
                }*/

                if (b2) {
                    boolean b = grille.nouvelleCase();
                    for(Tuile tuile : tuiles) {
                        tuile.setVisible(false);
                    }
                    tuiles.removeAll(tuiles);

                    /*for(Case ca : cases) {
                        if(!id.contains(ca.getID())) {
                            if(animationFini) {
                                afficherTuile(ca);
                                animationFini = false;
                            }
                        }
                    }
                    for(Tuile tuile : tuiles) {
                        if(!id.contains(tuile.getID()))
                            tuile.setVisible(false);
                    }*/
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
            animationFini = true;

        }

    }


}