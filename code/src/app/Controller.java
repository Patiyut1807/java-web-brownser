package app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    TabPane tabPane;

    @FXML
    Tab addTab;

    public void initialize() {
        // tabController(this.tabPane);
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
        AnchorPane anch;
        try {
            anch = FXMLLoader.load(getClass().getResource("Tab.fxml"));
            new_tab.setContent(anch);
            tabPane.getTabs().add(tabPane.getTabs().size() - 1, new_tab);
            tabPane.getSelectionModel().select(new_tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BooleanProperty CtrlPressed = new SimpleBooleanProperty();
    private BooleanProperty UPressed = new SimpleBooleanProperty();

    public void keyBoardEvent() {

        tabPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.CONTROL) {
                System.out.println("Ctrl");
                CtrlPressed.set(true);
                try {
                    checkEvent();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (e.getCode() == KeyCode.U) {
                System.out.println("U");
                UPressed.set(true);
                try {
                    checkEvent();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        tabPane.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.CONTROL) {
                CtrlPressed.set(false);
            }

            if (e.getCode() == KeyCode.U) {
                UPressed.set(false);
            }
        });

        tabPane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent k) {
                try {
                    checkEvent();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        });
    }

    public void checkEvent() throws IOException {

        if (CtrlPressed.get() && UPressed.get()) {
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefHeight(tabPane.getHeight());
            anchorPane.setPrefWidth(tabPane.getWidth());

            TextArea textArea = new TextArea();
            textArea.setPrefHeight(anchorPane.getHeight());
            textArea.setPrefWidth(anchorPane.getWidth());

            Tab new_tab = new Tab("Source Code");

            URL url = new URL(selectedTab.getContent().lookup("#textField").getAccessibleText());
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

            anchorPane.getChildren().addAll(textArea);

            new_tab.setContent(anchorPane);
            tabPane.getTabs().add(tabPane.getTabs().size() - 1, new_tab);
            tabPane.getSelectionModel().select(new_tab);

        }

    }
}
