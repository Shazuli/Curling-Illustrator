package main.Simon.java;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int width=900,height=800;
    public static final double yOffset = 90;

    public static StackPane layout;

    public static Canvas target;

    //public static int scenarioIndex = 0;

    /*Options*/
    public static boolean showLines = true;

    public static void main(String[] args) {
        System.out.println("Start.");
        launch(args);
        System.out.println("End.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        layout = new StackPane();
        new Main_abstract(primaryStage);
        //Scene scene = new Scene(layout,width,height);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Curling Illustrator");



        primaryStage.setScene(new Scene(layout,width,height));
        primaryStage.show();
    }
}
