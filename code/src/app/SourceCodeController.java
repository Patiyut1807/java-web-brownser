package app;


import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SourceCodeController {
    
    @FXML
    TextArea textArea;

    public void initialize() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tab.fxml"));
        Parent root = loader.load();
        TabController tabController = loader.getController();

        InputStream inputStream = tabController.inputStream;
        URLConnection urlConnection = tabController.urlConnection;

        inputStream = urlConnection.getInputStream();

        int input;
        String s = "";

        do {
            input = inputStream.read();
            if (input != -1) {
               s +=(char) input;
            }
            if ((char) input == '>') {
                s += "\n";
            }
        } while (input != -1);

        textArea.setText(s);
    }
}
