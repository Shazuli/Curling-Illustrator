package main.Simon.java.Objects;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import main.Simon.java.Main_abstract;

import static main.Simon.java.Main_abstract.getCurrentScenario;

public class Stone {
    private Color color;
    private String team;
    private int number;
    private Circle circle;
    private Line line;
    private double oldX;
    private double oldY;
    //private Canvas canvas;
    private ContextMenu menu;
    public Stone() {
        this.number = 1;
        this.menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> {
            //Main.layout.getChildren().remove(this.circle);
            //Main_abstract.currentSCENARIO.getPane().getChildren().remove(this.circle);
            getCurrentScenario().getPane().getChildren().remove(this.circle);
            getCurrentScenario().removeStone(this);
        });
        this.menu.getItems().add(delete);
    }
    public void setColor(Color color) { this.color = color; }
    public void setTeam(String team) { this.team = team; }

    public Color getColor() { return this.color; }
    public String getTeam(){ return this.team; }
    public double getCenterX() { return this.circle.getCenterX(); }
    public double getCenterY() { return this.circle.getCenterY(); }
    public double getRadius() { return this.circle.getRadius(); }
    public Circle getStoneImage() { return this.circle; }

    public Circle draw(double xOffset, double yOffset) {
        double radius = 30;

        Circle circle = new Circle();
        circle.setRadius(radius);
        circle.setFill(this.color);
        circle.setStroke(Color.GRAY);

        circle.setStrokeWidth(10);
        circle.setTranslateX(xOffset);
        circle.setTranslateY(yOffset);
        circle.toFront();
        this.circle = circle;


        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    oldX = circle.getTranslateX();
                    oldY = circle.getTranslateY();
                    //circle.setMouseTransparent(true);
                    //Main.layout.setCursor(Cursor.NONE);
                    event.setDragDetect(true);
                    //System.out.println("Pressed");
                    circle.setOpacity(0.7);
                    circle.toFront();
                    Main_abstract.updateDepth();
                }
                if (event.isSecondaryButtonDown()) {
                    menu.show(circle,event.getScreenX(),event.getScreenY());
                }
            }
        });
        circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //circle.setMouseTransparent(false);
                //Main.layout.setCursor(Cursor.DEFAULT);
                event.setDragDetect(false);
                circle.setOpacity(1);

                double newX = circle.getTranslateX();
                double newY = circle.getTranslateY();


                for (Stone stn:getCurrentScenario().getStones()) {
                    if (stn.circle == circle) continue;

                    double x1 = stn.circle.getTranslateX();
                    double y1 = stn.circle.getTranslateY();
                    double r1 = stn.circle.getRadius()+stn.circle.getStrokeWidth();

                    double x2 = newX;
                    double y2 = newY;
                    double r2 = circle.getRadius();

                    double distSq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                    double radSumSq = (r1 + r2) * (r1 + r2);


                    //System.out.println(((y1-y2)/(x2-x1))+"X");
                    if (!(distSq != radSumSq && distSq > radSumSq)) {
                        double angle = Math.atan2(x1-x2,y1-y2);
                        newX = x1 - Math.sin(angle) * (circle.getRadius() + circle.getStrokeWidth() + stn.circle.getRadius() + .01);
                        newY = y1 - Math.cos(angle) * (circle.getRadius() + circle.getStrokeWidth() + stn.circle.getRadius() + .01);
                        //circle.setTranslateX(newX);
                        //circle.setTranslateY(newY);



                    }
                }
                for (Stone stn:getCurrentScenario().getStones()) {
                    if (stn.circle == circle) continue;
                    double x1 = stn.circle.getTranslateX();
                    double y1 = stn.circle.getTranslateY();
                    double r1 = stn.circle.getRadius()+stn.circle.getStrokeWidth();

                    double x2 = newX;
                    double y2 = newY;
                    double r2 = circle.getRadius();

                    double distSq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                    double radSumSq = (r1 + r2) * (r1 + r2);

                    if (!(distSq != radSumSq && distSq > radSumSq)) {
                        newX = oldX;
                        newY = oldY;
                        break;
                    }
                }

                circle.setTranslateX(newX);
                circle.setTranslateY(newY);

                //System.out.println("Released");
            }
        });
        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    event.setDragDetect(false);
                    circle.setTranslateX(circle.getTranslateX() + event.getX());
                    circle.setTranslateY(circle.getTranslateY() + event.getY());
                }

            }
        });
        circle.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle.startFullDrag();

            }
        });
        circle.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {


            }
        });


        return circle;

        /*Canvas canvas = new Canvas(radius,radius);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(this.color);
        gc.fillOval(0,0,radius,radius);

        canvas.setTranslateX(xOffset);
        canvas.setTranslateY(yOffset);
        this.canvas = canvas;
        return canvas;*/
    }
}
