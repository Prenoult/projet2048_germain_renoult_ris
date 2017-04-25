package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    Controller controller = new Controller();

    @Override
    /**
     * Fonction permettant le lancement du jeu 2048
     */
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("2048"); // titre de la fenêtre
        Group root = new Group(); // création d'un groupe
        Scene scene = new Scene(root, 900, 900, Color.WHITE); // initialisation de la fenêtre (blanche 900*900)
        boolean add = scene.getStylesheets().add("css/style.css"); // ajout du fichier css

        root.getChildren().add(controller);
        controller.getIA(new IA(controller));

        // Réseaux
        Serveur serveur = new Serveur();
        serveur.main(null);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
