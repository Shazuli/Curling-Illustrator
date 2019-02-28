package main.Simon.java.Objects;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import static main.Simon.java.Main_abstract.getCurrentScenario;

public class Arrow {
    private double[] x;
    private double[] y;
    private double lenght = 8;
    private double width = 1;
    private Path arrow;
    private ContextMenu menu;
    private Color color = Color.BLACK;
    public Arrow(double[] x,double[] y) {
        this.x = x;
        this.y = y;
        this.menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> {
            //Main.layout.getChildren().remove(this.circle);
            //Main_abstract.currentSCENARIO.getPane().getChildren().remove(this.circle);
            getCurrentScenario().getPane().getChildren().remove(this.arrow);
        });
        this.menu.getItems().add(delete);
    }

    public void setLenght(double lenght) { this.lenght = lenght; }
    public void setWidth(double width) { this.width = width; }
    public void setColor(Color color) { this.color = color; }

    public double getLenght() { return this.lenght; }
    public double getWidth() { return this.width; }
    public Color getColor() { return this.color; }

    public Path draw(/*double xOffset,double yOffset*/) {
        double angle = Math.atan2(this.y[this.y.length-1]-this.y[this.y.length-2],this.x[this.x.length-1]-this.x[this.x.length-2]);
        //double XOF = arrMin(this.x);
        //double YOF = arrMin(this.y);

        Path arrow = new Path();
        arrow.setStroke(this.color);
        arrow.setStrokeWidth(this.width);
        for (int i=0;i<this.x.length-1;i++) {
            MoveTo moveTo = new MoveTo(this.x[i],this.y[i]);
            LineTo lineTo = new LineTo(this.x[i+1],this.y[i+1]);
            arrow.getElements().add(moveTo);
            arrow.getElements().add(lineTo);
        }
        double tox = this.x[this.x.length-1];
        double toy = this.y[this.y.length-1];
        MoveTo moveTo1 = new MoveTo(tox,toy);
        LineTo lineTo1 = new LineTo(tox-this.lenght*Math.cos(angle-Math.PI/6),toy-this.lenght*Math.sin(angle-Math.PI/6));
        MoveTo moveTo2 = new MoveTo(tox,toy);
        LineTo lineTo2 = new LineTo(tox-this.lenght*Math.cos(angle+Math.PI/6),toy-this.lenght*Math.sin(angle+Math.PI/6));
        arrow.getElements().addAll(moveTo1,lineTo1,moveTo2,lineTo2);
        //arrow.setTranslateX(xOffset);
        //arrow.setTranslateY(yOffset);
        //arrow.setTranslateX(arrMin(this.x));
        //arrow.setTranslateY(arrMin(this.y));


        arrow.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    //circle.setMouseTransparent(true);
                    event.setDragDetect(true);
                    //System.out.println("Pressed");
                    arrow.toFront();
                }
                if (event.isSecondaryButtonDown()) {
                    menu.show(arrow,event.getScreenX(),event.getScreenY());
                }

            }
        });
        arrow.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //circle.setMouseTransparent(false);
                event.setDragDetect(false);
                //System.out.println("Released");
            }
        });
        arrow.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    event.setDragDetect(false);
                    arrow.setTranslateX(-x[x.length-1] + (arrow.getTranslateX() + event.getX()));
                    arrow.setTranslateY(-y[y.length-1] + (arrow.getTranslateY() + event.getY()));
                }

            }
        });
        arrow.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                arrow.startFullDrag();

            }
        });

        this.arrow = arrow;
        return arrow;
    }
    private double arrMin(double[] numbers) {
        double lowest = numbers[0];
        for (double i:numbers)
            if (i < lowest)
                lowest = i;
        return lowest;
    }
    private double arrMax(double[] numbers) {
        double highest = numbers[0];
        for (double i:numbers)
            if (i > highest)
                highest = i;
        return highest;
    }
}
