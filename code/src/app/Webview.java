package app;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker.State;

public class Webview {

    private BorderPane borderPane = new BorderPane();
    private WebView webView;
    
    public TextField textField = new TextField("");

    private Button loadButton = new Button("refresh");
    private Button backButton = new Button("<");
    private Button forwardButton = new Button(">");

    private ToolBar toolBar = new ToolBar(backButton,forwardButton,loadButton,textField);
    private VBox vBox = new VBox(toolBar);
    private WebEngine engine;

    private String homePage;

    public Webview(){

        borderPane.setPrefHeight(1080);
        borderPane.setPrefHeight(1920);

        toolBar.setPrefHeight(34);
        toolBar.setMaxWidth(1920);

        webView = new WebView();
        engine = webView.getEngine();
        homePage = "www.google.com";

        textField.setText(homePage);

        try {
            loadPage();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        borderPane.setTop(vBox);
        borderPane.setCenter(webView);
    }

    public void loadPage() throws IOException {
        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == State.SCHEDULED) {
                            textField.setText(engine.getLocation());
                            textField.setAccessibleText(engine.getLocation());
                        }
                    }
                });
        engine.load("https://"+textField.getText());
    }

    public BorderPane getBorderPane(){
        return this.borderPane;
    }
}

