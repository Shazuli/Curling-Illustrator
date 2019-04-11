package main.Simon.java.Objects;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static main.Simon.java.Main_abstract.getCurrentScenario;

public class Scoreboard {
    //private Pane pane;
    private static Text team1T,team2T;
    private static Rectangle team1B,team2B;
    public static Pane newScoreboard(Scenario scenario) {
        Pane pane = new Pane();
        pane.setPickOnBounds(false);
        pane.setMouseTransparent(true);

        //Base
        Rectangle scoreBrdBase = new Rectangle(600,110, Color.GRAY);
        scoreBrdBase.setTranslateX(150);
        scoreBrdBase.setTranslateY(35);
        //Team1 Board
        team1B = new Rectangle(280,50,scenario.getTeam1().getColor());
        team1B.setTranslateX(150+5);
        team1B.setTranslateY(35+10);
        //Team2 Board
        team2B = new Rectangle(280,50,scenario.getTeam2().getColor());
        team2B.setTranslateX(150+600-280-5);
        team2B.setTranslateY(35+10);
        //Team1 Text
        team1T = new Text(scenario.getTeam1().getName());
        team1T.setFont(new Font(28));
        team1T.setTranslateX(150+5+2);
        team1T.setTranslateY(35+10+32);
        //Team2 Text
        team2T = new Text(scenario.getTeam2().getName());
        team2T.setFont(new Font(28));
        team2T.setTranslateX(150+600-280-5+2);
        team2T.setTranslateY(35+10+32);

        pane.getChildren().addAll(scoreBrdBase,team1B,team1T,team2B,team2T);

        return pane;
    }
    public static void update() {
        team1T.setText(getCurrentScenario().getTeam1().getName());
        team1B.setFill(getCurrentScenario().getTeam1().getColor());
        team2T.setText(getCurrentScenario().getTeam2().getName());
        team2B.setFill(getCurrentScenario().getTeam2().getColor());
    }
}
