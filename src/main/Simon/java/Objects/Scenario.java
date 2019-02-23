package main.Simon.java.Objects;

import javafx.scene.layout.Pane;
import main.Simon.java.Main;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private Team team1;
    private Team team2;
    private Pane pane;
    private int end = 1;
    private List<Stone> stones;

    public Scenario() {
        this.pane = new Pane();
        //this.pane.setPickOnBounds(false);
        Main.layout.getChildren().add(this.pane);
        this.stones = new ArrayList<>();
    }

    public void setTeam1(Team team) { this.team1 = team; }
    public void setTeam2(Team team) { this.team2 = team; }
    public void setEnd(int value) { this.end = value; }

    public Team getTeam1() { return this.team1; }
    public Team getTeam2() { return this.team2; }
    public int getEnd() { return this.end; }
    public Pane getPane() { return this.pane; }

    public void addStone(Team team) {
        Stone stone = new Stone();
        stone.setColor(team.getColor());
        this.stones.add(stone);
        this.pane.getChildren().add(stone.draw(100,200));
    }
    public void addArrow(Arrow arrow) {
        this.pane.getChildren().add(arrow.draw());
    }
}
