package main.Simon.java;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Simon.java.Objects.Stone;

public class GUI {
    public Pane newGUI() {
        Pane pane = new Pane();

        Button newTeam1Stone = new Button("New Red");
        newTeam1Stone.setTranslateX(10);
        newTeam1Stone.setTranslateY(80);

        newTeam1Stone.setOnMouseClicked(event -> {
            Stone stone = new Stone();
            stone.setColor(Color.RED);
            Main.layout.getChildren().add(stone.draw(0,0));
        });

        Button newTeam2Stone = new Button("New Yellow");
        newTeam2Stone.setTranslateX(10);
        newTeam2Stone.setTranslateY(110);

        newTeam2Stone.setOnMouseClicked(event -> {
            Stone stone = new Stone();
            stone.setColor(Color.YELLOW);
            Main.layout.getChildren().add(stone.draw(0,0));
        });

        addNode(pane,newTeam1Stone);
        addNode(pane,newTeam2Stone);

        return pane;
    }
    private void addNode(Pane pane,Node node) {
        pane.getChildren().add(node);
    }
}
