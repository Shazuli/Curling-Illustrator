package main.Simon.java.Windows;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Simon.java.GUI;
import main.Simon.java.Main;
import main.Simon.java.Main_abstract;
import main.Simon.java.Objects.Scenario;
import main.Simon.java.Objects.Scoreboard;
import main.Simon.java.Objects.Team;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.function.Function;

public class NewScenario {
    private static Stage window;
    private static final int maxLenght = 128;
    public static void display() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Scenario");
        window.setResizable(false);
        window.setWidth(260);
        window.setHeight(180);
        window.setAlwaysOnTop(true);

        TextField team1name = new TextField("Team 1");
        team1name.setTooltip(new Tooltip("Team 1"));
        team1name.setMaxWidth(115);
        team1name.setTranslateX(-60);
        team1name.setTranslateY(-30);
        TextField team2name = new TextField("Team 2");
        team2name.setTooltip(new Tooltip("Team 2"));
        team2name.setMaxWidth(115);
        team2name.setTranslateX(60);
        team2name.setTranslateY(-30);


        ColorPicker team1color = new ColorPicker();
        team1color.setValue(Color.RED);
        team1color.setTranslateX(-60);
        team1color.setMaxWidth(115);
        ColorPicker team2color = new ColorPicker();
        team2color.setValue(Color.YELLOW);
        team2color.setTranslateX(60);
        team2color.setMaxWidth(115);

        Text notice = new Text("Error Text");
        notice.setTextAlignment(TextAlignment.CENTER);
        notice.setTranslateY(24);
        notice.setOpacity(0);
        notice.setStroke(Color.RED);





        Button done = new Button("Done");
        done.setTranslateY(50);
        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                apply(team1name,team2name,team1color,team2color,notice);
            }

        });

        StackPane stackPane = new StackPane();

        stackPane.getChildren().addAll(team1name,team2name,done,team1color,team2color,notice);
        Scene scene = new Scene(stackPane);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    apply(team1name,team2name,team1color,team2color,notice);
                } else if (event.getCode() == KeyCode.ESCAPE) {
                    window.hide();
                }
            }
        });
        window.setScene(scene);



        window.showAndWait();

    }
    private static void apply(TextField team1name,TextField team2name, ColorPicker team1color,ColorPicker team2color,Text notice) {
        /*Check verifications*/
        if (team1name.getText().equals(team2name.getText())) {
            showWarning("Can't be same name",notice);
            return;
        }
        if (team1color.getValue().equals(team2color.getValue())) {
            showWarning("Can't be same color",notice);
            return;
        }
        FontRenderContext frc = new FontRenderContext(new AffineTransform(),true,true);
        Font font = new Font(team1name.getFont().getName(),Font.PLAIN, ((int) team1name.getFont().getSize()));
        double nameW1 = font.getStringBounds(team1name.getText(),frc).getWidth();
        double nameW2 = font.getStringBounds(team2name.getText(),frc).getWidth();
        if (nameW1 > maxLenght || nameW2 > maxLenght) {
            showWarning(String.format("Can't be longer than %s/%s metrics",Math.round(Double.max(nameW1,nameW2)),maxLenght),notice);
            return;
        }


        Main.scenarioFrame.getChildren().remove(Main_abstract.getCurrentScenario().getPane());
        Scenario newScenario = new Scenario();
        newScenario.getPane().setMinSize(Main.mainStage.getWidth(),Main.mainStage.getHeight());
        Main.scenarioFrame.setMinSize(Main.mainStage.getWidth(),Main.mainStage.getHeight());
        newScenario.setTeam1(new Team(team1name.getText(),team1color.getValue()));
        newScenario.setTeam2(new Team(team2name.getText(),team2color.getValue()));
        //Main.scenarioFrame.getChildren().add(newScenario.getPane());
        Main_abstract.currentSCENARIO.draw(false);
        newScenario.draw(true);
        Main_abstract.currentSCENARIO = newScenario;
        Main_abstract.SCENARIOS.add(newScenario);
        GUI.comboBox.getItems().add(newScenario);
        GUI.comboBox.getSelectionModel().select(newScenario);

        Scoreboard.update();

        //Main_abstract.currentSCENARIOIndex = Main_abstract.SCENARIOS.size()-1;
        //Main_abstract.currentSCENARIOIndex = Main_abstract.SCENARIOS.indexOf(newScenario);

        Scoreboard.update();
        window.hide();
    }

    private static void showWarning(String msg,Text target) {
        target.setText(msg);
        FadeTransition fd = new FadeTransition(Duration.millis(2500),target);
        fd.setFromValue(1);
        fd.setToValue(0);
        fd.play();
    }
}
