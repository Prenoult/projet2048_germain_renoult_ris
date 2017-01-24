package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class Main extends Application {

    private Grille grille;
    private Pane p = new Pane();
    int x = 165, y = 215;
    int objectifx = 165, objectify = 215;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("2048");
        Group root = new Group();
        Scene scene = new Scene(root, 900, 900, Color.WHITE);
        boolean add = scene.getStylesheets().add("css/style.css");

        this.grille = new Grille(); // on créé la grille (le background)
        //grille.addEventHandler(EventType.ROOT, new GenericHandler(grille));
        //grille.setFocusTraversable(true); // super important
        root.getChildren().add(grille); // et on l'ajoute au groupe root
        root.addEventHandler(EventType.ROOT, new GenericHandler()); // ajout d'un gestionnaire d'événements


        Label c = new Label("2"); // label qui fait office de valeur pour la tuile

        p.getStyleClass().add("pane"); // ajout des styles
        c.getStyleClass().add("tuile");
        root.getChildren().add(p);
        p.getChildren().add(c);

        p.setLayoutX(165); // taille de la tuile
        p.setLayoutY(215);
        p.setVisible(true);
        c.setVisible(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    private class GenericHandler implements EventHandler<Event> {

        @Override
        public void handle(Event event) {
            if(event.getEventType() == KEY_PRESSED) {
                KeyEvent keyEvent = (KeyEvent)event;
                switch (keyEvent.getText()) {
                    case "z":
                        if (objectify > 215) { // possible uniquement si on est pas sur la ligne la plus en haut
                            objectify -= (int) 147; // on définit la position que devra atteindre la tuile en ordonné (modèle). Le thread se chargera de mettre la vue à jour
                        }
                        break;
                    case "s":
                        if (objectify < 656) { // possible uniquement si on est pas sur la ligne la plus en bas
                            objectify += (int) 147; // on définit la position que devra atteindre la tuile en ordonné (modèle). Le thread se chargera de mettre la vue à jour
                        }
                        break;
                    case "q":
                        if (objectifx > 165) { // possible uniquement si on est pas dans la colonne la plus à droite
                            objectifx -= (int) 147; // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                        }
                        break;
                    case "d":
                        if (objectifx < 606) { // possible uniquement si on est pas dans la colonne la plus à gauche
                            objectifx += (int) 147; // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                        }
                        /* 165 - 312 - 459 - 606 */
                        break;
                }
            }
            Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                @Override
                public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                    while (x != objectifx) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                        if (x < objectifx) {
                            x += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                        } else {
                            x -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                        }
                        // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                p.relocate(x, y); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                p.setVisible(true);
                            }
                        });
                        Thread.sleep(5);
                    } // end while
                    while (y != objectify) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                        if (y < objectify) {
                            y += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                        } else {
                            y -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                        }
                        // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                p.relocate(x, y); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                p.setVisible(true);
                            }
                        });
                        Thread.sleep(5);
                    } // end while
                    return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                } // end call
            };
            Thread th = new Thread(task); // on crée un contrôleur de Thread
            th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
            th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
        }
    }


}
