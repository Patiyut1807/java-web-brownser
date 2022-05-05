package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws IOException {

		CustomTabPane tabpane = new CustomTabPane();

        stage.setScene(tabpane.getScene());
        stage.setTitle("Ya-om Ya-dom Ya-mhong");
		
        stage.setMaximized(true);
		stage.show();

	}

	public static void main(String[] args) {

		launch(args);
	}
}