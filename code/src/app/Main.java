package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws IOException {

		CustomTabPane tabpane = new CustomTabPane();
        stage.setScene(tabpane.getScene());
        stage.setTitle("Ya-om Ya-dom Ya-mhong");
		Image icon = new Image(getClass().getResource("asset/icons/icon.png").toString());
		stage.getIcons().add(icon);
        stage.setMaximized(true);
		stage.show();

	}
	public static void main(String[] args) {

		launch(args);
	}
}