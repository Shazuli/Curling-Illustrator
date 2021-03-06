package main.Simon.java.Objects;

import javafx.scene.paint.Color;
import main.Simon.java.Main;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private Color color;
    private int points;
    private int number = 1;
    //private List<List<Stone>> scenarios;

    public Team(String name, Color color) {
        this.name = name;
        this.color = color;
        //this.scenarios = new ArrayList<>();
    }

    public void setName(String name) { this.name = name; }
    public void setColor(Color color) { this.color = color; }
    public void setNumber(int number) { this.number = number; }
    public void addNumber(int number) { this.number = this.number + number; }

    public String getName() { return this.name; }
    public Color getColor() { return this.color; }
    public int getNumber() { return this.number; }

    /*public void addStone(Stone stone) {
        //this.scenarios.set(Main.scenarioIndex,stone);
        stone.setColor(this.color);

        //this.scenarios.get(Main.scenarioIndex).add(stone);
    }*/

    /*public void newScenario() {
        this.scenarios.add(new ArrayList<>());
    }*/
}
