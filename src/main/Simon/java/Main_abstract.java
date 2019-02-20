package main.Simon.java;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Simon.java.Objects.Arrow;
import main.Simon.java.Objects.Stone;

public class Main_abstract {
    public Main_abstract(Stage stage) {
        /*Target canvas.*/
        Canvas c = new Canvas(Main.width,Main.height);
        //c.setTranslateX(-Main.width*0.5);
        //c.setTranslateY(-Main.height*0.5);
        GraphicsContext gc = c.getGraphicsContext2D();


        drawCircle(gc,Main.width*0.5-250,Main.height*0.5-250,500,Color.RED);
        drawCircle(gc,Main.width*0.5-175,Main.height*0.5-175,350,Color.WHITE);
        drawCircle(gc,Main.width*0.5-100,Main.height*0.5-100,200,Color.BLUE);
        gc.setFill(Color.BLACK);
        gc.strokeLine(Main.width*0.5,0,Main.width*0.5,Main.height);
        gc.strokeLine(0,Main.height*0.5,Main.width,Main.height*0.5);
        drawCircle(gc,Main.width*0.5-35,Main.height*0.5-35,70,Color.WHITE);

        Main.target = c;
        Main.layout.getChildren().add(Main.target);


        Stone stone = new Stone();
        stone.setColor(Color.RED);
        Main.layout.getChildren().add(stone.draw(100,0));
        Stone stoneY = new Stone();
        stoneY.setColor(Color.YELLOW);
        Main.layout.getChildren().add(stoneY.draw(-100,0));

        //Arrow arrow = new Arrow(new double[]{0,-10,100,150},new double[]{20,-5,80,85});
        //Main.layout.getChildren().add(arrow.draw(0,0));

        /*Menu.*/
        MenuBar menu = new MenuBar();
        Menu file = new Menu("File");
        MenuItem fileQuit = new Menu("Quit");
        fileQuit.setGraphic(new ImageView("file:src/main/Simon/resources/quit.png"));
        fileQuit.setOnAction(event -> {
            stage.hide();
        });
        file.getItems().add(fileQuit);
        Menu view = new Menu("View");
        CheckMenuItem viewDist = new CheckMenuItem("Distance");
        viewDist.setOnAction(event -> {
            Main.showLines = !Main.showLines;
        });
        viewDist.setSelected(Main.showLines);
        view.getItems().add(viewDist);
        Menu about = new Menu("About");
        menu.getMenus().add(file);
        menu.getMenus().add(view);
        menu.getMenus().add(about);
        menu.setTranslateY(-Main.height*0.5+10);
        Main.layout.getChildren().add(menu);


    }
    private void drawCircle(GraphicsContext graphicsContext,double x, double y, double radius, Color c) {
        graphicsContext.setFill(c);
        graphicsContext.fillOval(x,y,radius,radius);
    }
}
