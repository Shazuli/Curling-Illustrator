package main.Simon.java.Objects;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import main.Simon.java.Main;
import main.Simon.java.Main_abstract;

public class Stone {
    private Color color;
    private String team;
    private int number;
    private Circle circle;
    private Line line;
    //private Canvas canvas;
    private ContextMenu menu;
    public Stone() {
        this.number = 1;
        this.menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> {
            //Main.layout.getChildren().remove(this.circle);
            Main_abstract.currentSCENARIO.getPane().getChildren().remove(this.circle);
        });
        this.menu.getItems().add(delete);
    }
    public void setColor(Color color) { this.color = color; }
    public void setTeam(String team) { this.team = team; }

    public Color getColor() { return this.color; }
    public String getTeam(){ return this.team; }
    public double getCenterX() { return this.circle.getCenterX(); }
    public double getCenterY() { return this.circle.getCenterY(); }
    //public Canvas getCanvas(){ return this.canvas; }


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
                    circle.setMouseTransparent(true);
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
                circle.setMouseTransparent(false);
                event.setDragDetect(false);
                circle.setOpacity(1);
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
