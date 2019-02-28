package main.Simon.java;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import main.Simon.java.Windows.About;

public class API {
    public static void onAction(Menu menu)
    {
        final MenuItem menuItem = new MenuItem();

        menu.getItems().add(menuItem);
        menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
        menu.addEventHandler(Menu.ON_SHOWING, event -> About.display());
    }
}
