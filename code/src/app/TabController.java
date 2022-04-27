package app;

import java.io.*;
import java.net.*;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

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
    TabPane tabPane;

    @FXML
    Tab tabWebview;

    @FXML
    Button homeButton;

    private WebEngine engine;

    private WebHistory history;

    private String homePage;

    private double webZoom;

    private URL url;
    private URLConnection urlConnection;
    private InputStream inputStream;

    @Override
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
        url = new URL("http://" + textField.getText());
        engine.load("http://" + textField.getText());

        urlConnection = url.openConnection();
    }

    public void refreshPage() {
        engine.reload();
    }

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

    public void getSourceCode() throws IOException {

        System.out.println("Source Code : " + textField.getText());

        inputStream = urlConnection.getInputStream();

        int input;

        do {
            input = inputStream.read();
            if (input != -1) {
                System.out.print((char) input);
            }
            if ((char) input == '>') {
                System.out.println();
            }
        } while (input != -1);
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

    public void newTabEvent() {
        try {
            Tab new_tab = new Tab("new Tab");
            AnchorPane anch1 = FXMLLoader.load(getClass().getResource("newTabTemplate.fxml"));
            new_tab.setContent(anch1);
            tabPane.getTabs().addAll(new_tab);
            tabPane.getSelectionModel().selectLast();
        } catch (IOException iex) {

        }

    }

    public void homePage() {
        textField.setText("www.google.com");
        try {
            loadPage();
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

}
