package main.Simon.java.Objects;

import com.sun.javafx.geom.Path2D;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import main.Simon.java.Main;
import main.Simon.java.Main_abstract;

public class Arrow {
    private double[] x;
    private double[] y;
    private double lenght = 20;
    private double width = 3;
    private Canvas canvas;
    private Path arrow;
    private Color color = Color.BLACK;
    public Arrow(double[] x,double[] y) {
        this.x = x;
        this.y = y;
    }

    public void setLenght(double lenght) { this.lenght = lenght; }
    public void setWidth(double width) { this.width = width; }
    public void setColor(Color color) { this.color = color; }

    public double getLenght() { return this.lenght; }
    public double getWidth() { return this.width; }
    public Color getColor() { return this.color; }

    public Path draw(double xOffset,double yOffset) {
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
        arrow.setTranslateX(xOffset);
        arrow.setTranslateY(yOffset);


        arrow.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    //circle.setMouseTransparent(true);
                    Main.layout.setCursor(Cursor.NONE);
                    event.setDragDetect(true);
                    //System.out.println("Pressed");
                    arrow.toFront();
                    Main_abstract.updateDepth();
                }

            }
        });
        arrow.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //circle.setMouseTransparent(false);
                Main.layout.setCursor(Cursor.DEFAULT);
                event.setDragDetect(false);

                //System.out.println("Released");
            }
        });
        arrow.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    event.setDragDetect(false);
                    arrow.setTranslateX(arrow.getTranslateX() + event.getX());
                    arrow.setTranslateY(arrow.getTranslateY() + event.getY());
                }

            }
        });
        arrow.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                arrow.startFullDrag();

            }
        });
        /*Canvas canvas = new Canvas(XOF*-1+arrMax(this.x)+this.lenght,YOF*-1+arrMax(this.y)+this.lenght);
        canvas.setTranslateX(xOffset);
        canvas.setTranslateY(yOffset);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(this.color);
        gc.setLineWidth(this.width);
        gc.beginPath();
        for (int i=0;i<this.x.length-1;i++) {
            gc.moveTo(XOF*-1+this.x[i],YOF*-1+this.y[i]);
            gc.lineTo(XOF*-1+this.x[i+1],YOF*-1+this.y[i+1]);
            gc.stroke();
        }
        double tox = XOF*-1+this.x[this.x.length-1];
        double toy = YOF*-1+this.y[this.y.length-1];
        gc.moveTo(tox,toy);
        gc.lineTo(tox-this.lenght*Math.cos(angle-Math.PI/6),toy-this.lenght*Math.sin(angle-Math.PI/6));
        gc.stroke();
        gc.moveTo(tox,toy);
        gc.lineTo(tox-this.lenght*Math.cos(angle+Math.PI/6),toy-this.lenght*Math.sin(angle+Math.PI/6));
        gc.stroke();
        gc.closePath();
        canvas.toBack();*/
        /*Main_abstract.updateDepth();


        canvas.setOnMouseClicked(event -> {
            canvas.toBack();
            Main_abstract.updateDepth();
        });*/

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
