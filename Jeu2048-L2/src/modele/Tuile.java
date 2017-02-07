package modele;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import test.FXMLDocumentController;

/**
 * Created by Charles on 30/01/2017.
 */
public class Tuile extends Parent {
    private int x,y;
    private Pane pane;

    public Tuile(int x1, int y1, int v){
        //Integer.toString(int i)
        pane=new Pane();
        Label c = new Label(Integer.toString(v)); // label qui fait office de valeur pour la tuile
        this.x=x1;
        this.y=y1;
        pane.getStyleClass().add("pane"); // ajout des styles
        c.getStyleClass().add("tuile");
        FXMLDocumentController.pa.getChildren().add(pane);
        pane.getChildren().add(c);

        pane.setLayoutX(x1*100+50); // taille de la tuile
        pane.setLayoutY(y1*100+150);
        pane.setVisible(true);
        c.setVisible(true);
        System.out.println(this.pane);
    }

    public void setFond(){
       // this.fond = FXMLDocumentController.getFond();
    }
}
