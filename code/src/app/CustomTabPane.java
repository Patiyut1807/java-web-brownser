package app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;

public class CustomTabPane {

    private Scene scene;
    private TabPane tabPane;

    public CustomTabPane() {

        tabPane = new TabPane();

        if (tabPane.getTabs().size() == 2)
            tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        else
            tabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);

        keyBoardEvent();
        createNewTabButton();
        createNewTab();
        tabPaneEvent();

        scene = new Scene(tabPane);
    }

    private void tabPaneEvent() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {

            if (tabPane.getTabs().size() == 2) {
                tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
            } else {
                tabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
            }

            if (newTab.getId() == "+") {
                createNewTab();
            }
        });
        
    }

    private void createNewTab() {

        Tab new_tab = new Tab();

        CustomTab customTab = new CustomTab();
        new_tab.setContent(customTab.getBorderPane());
        customTab.setCurrentTab(new_tab);
        tabPane.getTabs().add(tabPane.getTabs().size() - 1, new_tab);
        tabPane.getSelectionModel().select(new_tab);
    }

    public void createSourceCodeTab() throws IOException {
        {
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

            BorderPane borderPane = new BorderPane();
            borderPane.setPrefHeight(1080);
            borderPane.setPrefWidth(1920);

            TextArea textArea = new TextArea();
            borderPane.setCenter(textArea);

            Tab new_tab = new Tab("Source Code " + selectedTab.getText());

            URL url = new URL(selectedTab.getContent().lookup("TextField").getAccessibleText());
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            int input;
            String s = "";

            do {
                input = inputStream.read();
                if (input != -1) {
                    s += (char) input;
                }
                if ((char) input == '>') {
                    s += "\n";
                }
            } while (input != -1);

            textArea.setText(s);

            new_tab.setContent(borderPane);
            tabPane.getTabs().add(tabPane.getTabs().size() - 1, new_tab);
            tabPane.getSelectionModel().select(new_tab);

        }
    }

    private void createNewTabButton() {
        Tab new_tab = new Tab("+");
        new_tab.setId("+");
        tabPane.getTabs().add(new_tab);
    }

    public void keyBoardEvent() {
        tabPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F12) {
                try {
                    createSourceCodeTab();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public double getHeight() {
        return this.tabPane.getHeight();
    }

    public double getWidth() {
        return this.tabPane.getWidth();
    }

    public Scene getScene() {
        return this.scene;
    }

}
