package main.Simon.java;


import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.Simon.java.Objects.Arrow;
import main.Simon.java.Objects.Scenario;
import main.Simon.java.Objects.Stone;

import java.util.ArrayList;
import java.util.List;

import static main.Simon.java.Main_abstract.getCurrentScenario;

public class GUI {

    private List<Circle> tempMa = new ArrayList<>();

    private boolean markerMode = false;

    public Pane newGUI() {
        Pane pane = new Pane();

        pane.setPickOnBounds(false);

        Button newTeam1Stone = new Button("New Red");
        newTeam1Stone.setTranslateX(10);
        newTeam1Stone.setTranslateY(80);

        newTeam1Stone.setOnMouseClicked(event -> {
            Stone stone = new Stone();
            stone.setColor(Color.RED);
            //Main.layout.getChildren().add(stone.draw(0,0));
            //Main_abstract.currentSCENARIO.getPane().getChildren().add(stone.draw(0,0));
            //Main_abstract.currentSCENARIO.addStone(Main_abstract.currentSCENARIO.getTeam1());
            getCurrentScenario().addStone(getCurrentScenario().getTeam1());
        });

        Button newTeam2Stone = new Button("New Yellow");
        newTeam2Stone.setTranslateX(10);
        newTeam2Stone.setTranslateY(110);

        newTeam2Stone.setOnMouseClicked(event -> {
            Stone stone = new Stone();
            stone.setColor(Color.YELLOW);
            //Main.layout.getChildren().add(stone.draw(0,0));
            //Main_abstract.currentSCENARIO.addStone(Main_abstract.currentSCENARIO.getTeam2());
            getCurrentScenario().addStone(getCurrentScenario().getTeam2());
        });

        Button newArrow = new Button("Add Arrow");
        newArrow.setTranslateX(10);
        newArrow.setTranslateY(140);

        newArrow.setOnMouseClicked(event -> {
            /*if (this.tempMa != null)
                for (Circle i:this.tempMa)
                    getCurrentScenario().getPane().getChildren().remove(i);*/
            //this.tempMa = new ArrayList<>();
            //this.markerMode = true;
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
                        System.out.println("Writing ..");
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
                            Arrow arrow = new Arrow(x, y);
                            getCurrentScenario().getPane().getChildren().add(arrow.draw());
                        }
                        tempMa = new ArrayList<>();
                        //getCurrentScenario().getPane().removeEventHandler(MouseEvent.MOUSE_PRESSED,this);
                        getCurrentScenario().getPane().setOnMousePressed(null);

                    }

                }
            });


            /*pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ENTER) {
                        System.out.println("Writing ..");

                        pane.removeEventHandler(KeyEvent.KEY_PRESSED,this::handle);
                    }
                }
            });*/

        });

        addNode(pane,newTeam1Stone);
        addNode(pane,newTeam2Stone);
        addNode(pane,newArrow);

        return pane;
    }
    //private Scenario getCurrentScenario() { return Main_abstract.SCENARIOS.get(Main_abstract.currentSCENARIOIndex); }
    private void addNode(Pane pane,Node node) {
        pane.getChildren().add(node);
    }
}
