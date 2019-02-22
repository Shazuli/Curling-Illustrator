package main.Simon.java;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Simon.java.Objects.Arrow;
import main.Simon.java.Objects.Scenario;
import main.Simon.java.Objects.Team;

import java.util.ArrayList;
import java.util.List;

public class Main_abstract {
    //private static List<Node> viewList = new ArrayList<>();
    private static List<Node> top = new ArrayList<>();

    private boolean GUIVisible = true;


    public static List<Scenario> SCENARIOS = new ArrayList<>();
    public static int currentSCENARIOIndex = 0;
    private Pane GUI;

    public Main_abstract(Stage stage) {
        /*Target canvas.*/
        Canvas c = new Canvas(Main.width,Main.height);
        //c.setTranslateX(-Main.width*0.5);
        //c.setTranslateY(-Main.height*0.5);
        GraphicsContext gc = c.getGraphicsContext2D();

        /*Target.*/
        drawCircle(gc,Main.width*0.5-250,Main.height*0.5-250,500,Color.RED);
        drawCircle(gc,Main.width*0.5-175,Main.height*0.5-175,350,Color.WHITE);
        drawCircle(gc,Main.width*0.5-100,Main.height*0.5-100,200,Color.BLUE);
        gc.setFill(Color.BLACK);
        gc.strokeLine(Main.width*0.5,0,Main.width*0.5,Main.height);
        gc.strokeLine(0,Main.height*0.5+Main.yOffset,Main.width,Main.height*0.5+Main.yOffset);
        drawCircle(gc,Main.width*0.5-35,Main.height*0.5-35,70,Color.WHITE);

        Main.target = c;
        Main.layout.getChildren().add(Main.target);
        //viewList.add(Main.target);




        //Main.layout.getChildren().add(pane);

        /*GUI*/
        GUI = new GUI().newGUI();
        GUI.setVisible(GUIVisible);
        top.add(GUI);
        //GUI.setStyle("-fx-background-color: black;");

        //viewList.add(GUI);
        Main.layout.getChildren().add(GUI);

        /*Menu Bar.*/
        MenuBar menuBar = new MenuBar();
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
            CheckMenuItem viewGUI = new CheckMenuItem("GUI");
        viewGUI.setOnAction(event -> {
            GUIVisible = !GUIVisible;
            GUI.setVisible(GUIVisible);
        });
        viewGUI.setSelected(GUIVisible);
        view.getItems().add(viewGUI);

        Menu about = new Menu("About");
        menuBar.getMenus().add(file);
        menuBar.getMenus().add(view);
        menuBar.getMenus().add(about);
        menuBar.setTranslateY(-Main.height*0.5+10);
        menuBar.setOpacity(0.3);

        menuBar.setOnMouseExited(event -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1000),menuBar);
            ft.setFromValue(1);
            ft.setToValue(0.3);
            ft.play();
        });
        menuBar.setOnMouseEntered(event -> {
            menuBar.setOpacity(1);
        });

        top.add(menuBar);
        Main.layout.getChildren().add(menuBar);


        Scenario newScenario = new Scenario();

        Team teamRed = new Team("Team Red",Color.RED);
        Team teamYellow = new Team("Team Yellow",Color.YELLOW);
        newScenario.setTeam1(teamRed);
        newScenario.setTeam2(teamYellow);

        SCENARIOS.add(newScenario);

        //viewList.add(gui);


        /*Stone stone = new Stone();
        stone.setColor(Color.RED);
        Main.layout.getChildren().add(stone.draw(100,0));
        Stone stoneY = new Stone();
        stoneY.setColor(Color.YELLOW);
        Main.layout.getChildren().add(stoneY.draw(-100,0));*/

        //Arrow arrow = new Arrow(new double[]{0,-10,100,150},new double[]{20,-5,80,85});
        //Main.layout.getChildren().add(arrow.draw(0,0));









        updateDepth();


    }
    private void drawCircle(GraphicsContext graphicsContext,double x, double y, double radius, Color c) {
        graphicsContext.setFill(c);
        graphicsContext.fillOval(x,y+Main.yOffset,radius,radius);
    }
    public static void updateDepth() {
        /*for (int i=viewList.size()-1;i>=0;i--)
            viewList.get(i).toBack();*/
        for (Node i:top)
            i.toFront();
    }
    public static Scenario getCurrentScenario() { return SCENARIOS.get(currentSCENARIOIndex); }
}
