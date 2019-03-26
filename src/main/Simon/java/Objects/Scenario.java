package main.Simon.java.Objects;

import javafx.scene.layout.Pane;
import main.Simon.java.Main;

import java.util.ArrayList;
import java.util.List;

import static main.Simon.java.Main_abstract.getCurrentScenario;

public class Scenario {
    private Team team1;
    private Team team2;
    private Pane pane;
    private int end = 1;
    private List<Stone> stones;

    public Scenario() {
        this.pane = new Pane();
        //this.pane.setMinSize(1920,1080);
        Main.mainStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.pane.setMinWidth(newVal.doubleValue());
        });
        Main.mainStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            this.pane.setMinHeight(newVal.doubleValue());
        });
        //this.pane.setStyle("-fx-background-color: black");
        //this.pane.setPickOnBounds(false);
        //Main.layout.getChildren().add(this.pane);
        //Main.scenarioFrame.getChildren().add(this.pane);
        this.stones = new ArrayList<>();
    }

    public void setTeam1(Team team) { this.team1 = team; }
    public void setTeam2(Team team) { this.team2 = team; }
    public void setEnd(int value) { this.end = value; }
    public void setPane(Pane pane) { this.pane = pane; }

    public Team getTeam1() { return this.team1; }
    public Team getTeam2() { return this.team2; }
    public int getEnd() { return this.end; }
    public Pane getPane() { return this.pane; }
    public Stone[] getStones() { return this.stones.toArray(new Stone[this.stones.size()]); }
    public String toString() { return team1.getName()+" - "+team2.getName(); }

    public void addStone(Team team) {
        Stone stone = new Stone();
        stone.setColor(team.getColor());
        this.stones.add(stone);
        this.pane.getChildren().add(stone.draw(100,200));
        stone.setNumber(team.getNumber());
        team.addNumber(1);
        this.pane.getChildren().add(stone.getLabel());
    }
    public void removeStone(Stone stone) {
        getCurrentScenario().getPane().getChildren().remove(stone.getStoneImage());
        this.stones.remove(stone);
    }
    public void addArrow(Arrow arrow) {
        this.pane.getChildren().add(arrow.draw());
    }

    public void draw(boolean visible) {
        if (visible)
            Main.scenarioFrame.getChildren().add(this.pane);
        else
            Main.scenarioFrame.getChildren().remove(this.pane);
    }
}
