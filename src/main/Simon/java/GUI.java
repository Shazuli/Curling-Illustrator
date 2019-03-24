package main.Simon.java;


import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.Simon.java.Objects.Arrow;

import java.util.ArrayList;
import java.util.List;

import static main.Simon.java.Main_abstract.getCurrentScenario;

public class GUI {

    private static List<Circle> tempMa = new ArrayList<>();
    public static ComboBox comboBox;

    public static Pane newGUI() {
        Pane pane = new Pane();

        pane.setPickOnBounds(false);

        /* newScoreboard */
        //Base
        /*Rectangle scoreBrdBase = new Rectangle(600,110,Color.GRAY);
        scoreBrdBase.setTranslateX(150);
        scoreBrdBase.setTranslateY(35);
         //Team1 Board
        team1B = new Rectangle(280,50,getCurrentScenario().getTeam1().getColor());
        team1B.setTranslateX(150+5);
        team1B.setTranslateY(35+10);
        //Team2 Board
        team2B = new Rectangle(280,50,getCurrentScenario().getTeam2().getColor());
        team2B.setTranslateX(150+600-280-5);
        team2B.setTranslateY(35+10);
        //Team1 Text
        team1T = new Text(getCurrentScenario().getTeam1().getName());
        team1T.setFont(new Font(28));
        team1T.setTranslateX(150+5+2);
        team1T.setTranslateY(35+10+32);
        //Team2 Text
        team2T = new Text(getCurrentScenario().getTeam2().getName());
        team2T.setFont(new Font(28));
        team2T.setTranslateX(150+600-280-5+2);
        team2T.setTranslateY(35+10+32);*/
        Button newTeam1Stone = new Button("New Red");
        newTeam1Stone.setTranslateX(10);
        newTeam1Stone.setTranslateY(80);

        newTeam1Stone.setOnMouseClicked(event -> {
            getCurrentScenario().addStone(getCurrentScenario().getTeam1());
            System.out.println("New "+getCurrentScenario().getTeam1().getName()+" stone.");
        });

        Button newTeam2Stone = new Button("New Yellow");
        newTeam2Stone.setTranslateX(10);
        newTeam2Stone.setTranslateY(110);

        newTeam2Stone.setOnMouseClicked(event -> {
            getCurrentScenario().addStone(getCurrentScenario().getTeam2());
            System.out.println("New "+getCurrentScenario().getTeam2().getName()+" stone.");
        });

        Button newArrow = new Button("Add Arrow");
        newArrow.setTranslateX(10);
        newArrow.setTranslateY(140);
        addArrow(newArrow);


        comboBox = new ComboBox();

        comboBox.setTranslateY(170);
        //addNode(pane,comboBox);





        //addArrow(newArrow);

        //addNode(pane,newTeam1Stone);
        //addNode(pane,newTeam2Stone);
        //addNode(pane,newArrow);
        //pane.getChildren().addAll(scoreBrdBase,team1B,team2B,team1T,team2T);
        pane.getChildren().addAll(newTeam1Stone,newTeam2Stone,newArrow,comboBox);

        return pane;
    }

    private void addNode(Pane pane,Node node) {
        pane.getChildren().add(node);
    }

    private static void addArrow(Node node) {
        node.setOnMouseClicked(event -> {
            getCurrentScenario().getPane().setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.isPrimaryButtonDown()) {
                        Circle circle = new Circle();
                        circle.setTranslateX(event.getX());
                        circle.setTranslateY(event.getY());
                        circle.setFill(Color.RED);
                        circle.setStrokeWidth(2);
                        circle.setStroke(Color.BLUE);
                        circle.setRadius(8);
                        tempMa.add(circle);
                        getCurrentScenario().getPane().getChildren().add(circle);
                    }
                    if (event.isSecondaryButtonDown()) {
                        List<Double> cX = new ArrayList<>();
                        List<Double> cY = new ArrayList<>();
                        for (Circle i : tempMa) {
                            cX.add(i.getTranslateX());
                            cY.add(i.getTranslateY());
                        }
                        double[] x = new double[cX.size()];
                        double[] y = new double[cY.size()];
                        for (int i = 0; i < cX.size(); i++) {
                            x[i] = cX.get(i);
                            y[i] = cY.get(i);
                        }
                        for (Circle i : tempMa)
                            getCurrentScenario().getPane().getChildren().remove(i);
                        if (tempMa.size() > 1) {
                            Arrow arrow = new Arrow(x,y);
                            getCurrentScenario().addArrow(arrow);
                        }
                        tempMa = new ArrayList<>();
                        getCurrentScenario().getPane().setOnMousePressed(null);

                    }

                }
            });
        });

    }
}
