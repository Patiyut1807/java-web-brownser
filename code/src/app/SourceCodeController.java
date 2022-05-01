package app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SourceCodeController {

    @FXML
    TextArea textArea;
    public void initialize() throws IOException {
        URL url = new URL("https://www.google.co.th/?hl=th");

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
    }
}