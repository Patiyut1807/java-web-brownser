package app;

import java.io.IOException;
import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker.State;
import javafx.geometry.Pos;

public class Webview {

    private BorderPane borderPane = new BorderPane();
    private WebView webView;
    
    public TextField textField = new TextField("");

    private Button loadButton = new Button("refresh");
    private Button backButton = new Button("<");
    private Button forwardButton = new Button(">");
    private Button zoomInButton = new Button("+");
    private Button zoomOutButton = new Button("-");


    private ToolBar toolBar = new ToolBar(backButton,forwardButton,loadButton,textField,zoomInButton,zoomOutButton);
    private VBox vBox = new VBox(toolBar);
    private WebEngine engine;

    private String homePage;
    private double webZoom;
    private WebHistory history;

    public Webview(){

        borderPane.setPrefHeight(1080);
        borderPane.setPrefHeight(1920);

        toolBar.setPrefHeight(34);
        toolBar.setMaxWidth(1920);

        textField.setPrefWidth(500);
        textField.setAlignment(Pos.CENTER);
        textField.setOnAction(e->{try {
            loadPage();
        } catch (IOException e1) {
            e1.printStackTrace();
        }});

        webView = new WebView();
        engine = webView.getEngine();
        homePage = "https://www.google.com";
        webZoom = 1;

        buttonEvent();

        textField.setText(homePage);

        try {
            loadPage();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        borderPane.setTop(vBox);
        borderPane.setCenter(webView);
    }
    public static boolean isValid(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }
          
        catch (Exception e) {
            return false;
        }
    }
    public void loadPage() throws IOException {
        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == State.SCHEDULED) {
                            checkBackForward();
                            textField.setText(engine.getLocation());
                            textField.setAccessibleText(engine.getLocation());
                        }
                    }
                });
        if(isValid(textField.getText())) engine.load(textField.getText());
        else engine.load("https://www.google.com/search?q="+textField.getText());
    }

    public String getUrl(){
        return engine.getLocation();
    }
        public void buttonEvent(){
        zoomInButton.setOnAction(e->{
            zoomIn();
        });

        zoomOutButton.setOnAction(e->{
            zoomOut();
        });
        loadButton.setOnAction(e->{
            refreshPage();
        });
        forwardButton.setOnAction(e->{
            forward();
        });
        backButton.setOnAction(e->{
            back();
        });
    }

    public void zoomIn() {
        webZoom += 0.25;
        webView.setZoom(webZoom);
    }

    public void zoomOut() {
        webZoom -= 0.25;
        webView.setZoom(webZoom);
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
            backButton.setDisable(true);
            backButton.setOpacity(0.5);
        } else{
            backButton.setDisable(false);
            backButton.setOpacity(1);
        }

        if (history.getCurrentIndex() == entries.size()-1) {
            forwardButton.setDisable(true);
            forwardButton.setOpacity(0.5);
        } else{
            forwardButton.setDisable(false);
            forwardButton.setOpacity(1);
        }
    }

    public void refreshPage() {
        engine.reload();
    }

    public BorderPane getBorderPane(){
        return this.borderPane;
    }
}
