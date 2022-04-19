

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
<<<<<<< Updated upstream
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene.fxml"));
			Parent root = loader.load();
			Controller controller = loader.getController();
			Scene scene = new Scene(root);

			stage.setTitle("Test");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
=======
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/Scene.fxml"));
			System.out.println("hh");
		    Parent root = loader.load();	
		    Controller controller = loader.getController();
		    Scene scene = new Scene(root);		
            
            stage.setTitle("Test");
		    stage.setScene(scene);
		    stage.show();
        } catch (Exception e) {
            e.getStackTrace();
        }
		
	}	
>>>>>>> Stashed changes

	public static void main(String[] args) {

		launch(args);
	}
}
