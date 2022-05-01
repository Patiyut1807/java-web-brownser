package app;

import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
                checkEvent();
            }

            if (e.getCode() == KeyCode.U) {
                System.out.println("U");
                UPressed.set(true);
                checkEvent();
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
                checkEvent();
            }
        });
    }

    public void checkEvent() {
        if (CtrlPressed.get() && UPressed.get()) {

            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            String url = selectedTab.getContent().lookup("#textField").getAccessibleText();
            Tab new_tab = new Tab("Source Code");
            AnchorPane anch = new AnchorPane();
            try {
                anch = FXMLLoader.load(getClass().getResource("SourceCode.fxml"));
                new_tab.setContent(anch);
                tabPane.getTabs().add(tabPane.getTabs().size() - 1, new_tab);
                tabPane.getSelectionModel().select(new_tab);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}