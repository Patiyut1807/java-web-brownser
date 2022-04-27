package app;

import java.io.IOException;
import java.net.*;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    TabPane tabPane;

    @FXML
    Tab addTab;

    public void initialize() {
        createNewTab();
    }

    public void addTabEvent() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (newTab == addTab) {
                createNewTab();
            }
        });
    }

    public void createNewTab() {
        Tab new_tab = new Tab("new Tab");
        AnchorPane anch1;
        try {
            anch1 = FXMLLoader.load(getClass().getResource("Tab.fxml"));
            new_tab.setContent(anch1);
            tabPane.getTabs().add(tabPane.getTabs().size() - 1, new_tab);
            tabPane.getSelectionModel().select(new_tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
