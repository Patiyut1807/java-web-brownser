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
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene.fxml"));
			Parent root = loader.load();
			Controller controller = loader.getController();
			Scene scene = new Scene(root);

			stage.setMaximized(true);

			stage.setTitle("Test");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.getStackTrace();
		}
		//textArea
	}
	public static void main(String[] args) {
		launch(args);
	}
}