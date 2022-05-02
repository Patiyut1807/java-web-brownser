package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws IOException {

		Tabpane tabpane = new Tabpane();

        stage.setScene(tabpane.getScene());
        stage.setTitle("Ya-om Ya-dom Ya-mhong");
		
        stage.setMaximized(true);
		stage.show();

	}

	public static void main(String[] args) {

		launch(args);
	}
}