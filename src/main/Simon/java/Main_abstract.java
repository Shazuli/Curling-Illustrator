package main.Simon.java;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Simon.java.Objects.Scenario;
import main.Simon.java.Objects.Scoreboard;
import main.Simon.java.Objects.Team;
import main.Simon.java.Windows.NewScenario;

import java.util.ArrayList;
import java.util.List;

import static main.Simon.java.GUI.comboBox;

public class Main_abstract {
    //private static List<Node> viewList = new ArrayList<>();
    private static List<Node> top = new ArrayList<>();
    private FadeTransition ft;

    private boolean GUIVisible = true;
    private boolean ScoresVisible = true;


    public static List<Scenario> SCENARIOS = new ArrayList<>();
    public static Scenario currentSCENARIO;
    //public static int currentSCENARIOIndex = 0;
    private Pane GUI;
    private Pane SCOREBOARD;

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



        /*Placeholder scenario*/
        Scenario newScenario = new Scenario();

        Team teamRed = new Team("Team Engqvist",Color.RED);
        Team teamYellow = new Team("Team Köhn",Color.YELLOW);
        newScenario.setTeam1(teamRed);
        newScenario.setTeam2(teamYellow);

        newScenario.draw(true);
        currentSCENARIO = newScenario;
        SCENARIOS.add(newScenario);

        //Main.layout.getChildren().add(pane);



        SCOREBOARD = Scoreboard.newScoreboard(newScenario);
        SCOREBOARD.setVisible(ScoresVisible);
        top.add(SCOREBOARD);
        Main.layout.getChildren().add(SCOREBOARD);

        //GUI.setStyle("-fx-background-color: black;");
        /*GUI*/
        GUI = main.Simon.java.GUI.newGUI();
        GUI.setVisible(GUIVisible);
        top.add(GUI);
        Main.layout.getChildren().add(GUI);


        //viewList.add(GUI);


        /*Menu Bar.*/
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu fileNew = new Menu("New");
        Menu fileNewScenario = new Menu("Scenario");
        MenuItem fileNewScenarioBlank = new MenuItem("Blank");
        fileNewScenarioBlank.setOnAction(event -> {
            NewScenario.display();
        });
        MenuItem fileNewScenarioFile = new MenuItem("From File ..");
        fileNewScenarioFile.setOnAction(event -> {

        });
        fileNewScenario.getItems().add(fileNewScenarioBlank);
        fileNewScenario.getItems().add(fileNewScenarioFile);
        fileNew.getItems().add(fileNewScenario);
        MenuItem fileQuit = new MenuItem("Quit");
        //fileQuit.setGraphic(new ImageView("file:src/main/Simon/resources/quit.png"));
        fileQuit.setOnAction(event -> {
            stage.hide();
            //About.display();
        });
        file.getItems().add(fileNew);
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
        CheckMenuItem viewScoreboard = new CheckMenuItem("Scoreboard");
        viewScoreboard.setSelected(ScoresVisible);
        view.getItems().add(viewScoreboard);
        viewScoreboard.setOnAction(event -> {
            ScoresVisible = !ScoresVisible;
            SCOREBOARD.setVisible(ScoresVisible);
        });
        viewGUI.setSelected(GUIVisible);
        view.getItems().add(viewGUI);

        Menu about = new Menu("About");
        //MenuItem aboutDummy = new MenuItem();
        //aboutDummy.setOnAction(event -> {
        //    About.display();
        //});
        //about.getItems().add(aboutDummy);
        //about.addEventHandler(Menu.ON_SHOWN, event -> About.display());
        API.onAction(about);

        /*about.showingProperty().addListener((observableValue,oldV,newV) -> {
            if (newV)
                about.getItems().get(0).fire();

        });*/

        menuBar.getMenus().add(file);
        menuBar.getMenus().add(view);
        menuBar.getMenus().add(about);
        //menuBar.setTranslateY(-Main.height*0.5+10);
        menuBar.setOpacity(0.3);
        ft = new FadeTransition();

        menuBar.setOnMouseExited(event -> {
            ft = new FadeTransition(Duration.millis(800),menuBar);
            ft.setFromValue(1);
            ft.setToValue(0.3);
            ft.play();
        });
        menuBar.setOnMouseEntered(event -> {
            if (ft.getStatus().equals(FadeTransition.Status.RUNNING))
                ft.stop();

            menuBar.setOpacity(1);
        });

        top.add(menuBar);
        Main.layout.getChildren().add(menuBar);
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            menuBar.setTranslateY((-newVal.doubleValue()*0.5+25));
        });

        Main.layout.getChildren().add(Main.scenarioFrame);




        for (Scenario i:SCENARIOS)
            comboBox.getItems().add(i);
        comboBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                //System.out.println(((Scenario) newValue).getTeam1().getName());
                Main.scenarioFrame.getChildren().remove(currentSCENARIO.getPane());
                currentSCENARIO = (Scenario) newValue;
                Main.scenarioFrame.getChildren().add(currentSCENARIO.getPane());
                Scoreboard.update();
            }
        });
        comboBox.getSelectionModel().selectFirst();


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
    public static Scenario getCurrentScenario() { return /*SCENARIOS.get(currentSCENARIOIndex);*/currentSCENARIO; }

    public void addScenario(Scenario scenario) { SCENARIOS.add(scenario); }
}
