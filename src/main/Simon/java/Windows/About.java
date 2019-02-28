package main.Simon.java.Windows;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Simon.java.Main;

public class About {
    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About");
        window.setResizable(false);
        window.setWidth(180);
        window.setHeight(150);
        window.setAlwaysOnTop(true);

        Label author = new Label("By Simon Engelholm");
        author.setTranslateY(-10);
        Label version = new Label("Version: "+ Main.version);
        version.setTranslateY(10);

        StackPane l = new StackPane();
        l.getChildren().add(author);
        l.getChildren().add(version);

        window.setScene(new Scene(l));

        window.showAndWait();

    }
}
