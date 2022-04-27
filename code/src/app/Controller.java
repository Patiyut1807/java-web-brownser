package app;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class Controller{

    @FXML
    TabPane tabPane;

    @FXML
    Tab addTab;

    @FXML
    Tab sourceCode;

    public void initialize() {
        createNewTab();
    }
    
    public void addTabEvent(){
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if(newTab == sourceCode){
                createSourceCode();
            }
            if (newTab == addTab) {
                 createNewTab();
             }
        });
    }

    public void createNewTab(){
        Tab new_tab = new Tab("new Tab");
        AnchorPane anch;
        try {
            anch = FXMLLoader.load(getClass().getResource("Tab.fxml"));
            new_tab.setContent(anch);
            tabPane.getTabs().add(tabPane.getTabs().size()-2, new_tab);
            tabPane.getSelectionModel().select(new_tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSourceCode(){
        Tab new_tab = new Tab("Source Code");
        AnchorPane anch;
        try {
            anch = FXMLLoader.load(getClass().getResource("SourceCode.fxml"));
            new_tab.setContent(anch);
            tabPane.getTabs().add(tabPane.getTabs().size()-2, new_tab);
            tabPane.getSelectionModel().select(new_tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}