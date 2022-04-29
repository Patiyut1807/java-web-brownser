package app;

import java.io.*;
import java.net.*;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker.State;

public class TabController implements Initializable {

    @FXML
    private WebView webView;

    @FXML
    TextField textField;

    @FXML
    Button loadButton;

    @FXML
    Button backButton;

    @FXML
    Button forwardButton;

    @FXML
    Button sourceCodeButton;

    @FXML
    Tab tabWebview;

    private WebEngine engine;

    private WebHistory history;

    private String homePage;

    private double webZoom;

    // TabController(TabPane tabPane){
    //     this.tabPane = tabPane;
    // }

    public void initialize(URL arg0, ResourceBundle arg1) {

        engine = webView.getEngine();
        homePage = "www.google.com";
        webZoom = 1;

        textField.setText(homePage);
        try {
            loadPage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPage() throws IOException {
        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == State.SCHEDULED) {
                            textField.setText(engine.getLocation());
                        }
                    }
                });
        engine.load("https://"+textField.getText());
    }

    public void refreshPage() {
        engine.reload();
    }

    // public void addSourceCodeTab() {

    // }

    public void zoomIn() {
        webZoom += 0.25;
        webView.setZoom(webZoom);
    }

    public void zoomOut() {
        webZoom -= 0.25;
        webView.setZoom(webZoom);
    }

    public void displayHostory() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        for (WebHistory.Entry entry : entries) {
            // System.out.println(entry);
            System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
        }
    }

    public void back() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        if (history.getCurrentIndex() == 0)
            return;
        history.go(-1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void forward() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        if (entries.size() - 1 == history.getCurrentIndex())
            return;
        history.go(1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    private void checkBackForward() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        if (history.getCurrentIndex() == 0) {
            backButton.setOpacity(0.5);
        } else
            backButton.setOpacity(1);

        if (history.getCurrentIndex() == entries.size() - 1) {
            forwardButton.setOpacity(0.5);
        } else
            forwardButton.setOpacity(1);
    }

}