package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;



    public class MainApp extends Application {

        @Override
        public void start(Stage stage) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();
            Scene scene = new Scene(root);
            stage.setTitle("WEB BROWSER PROJECT");
            stage.setScene(scene);
            stage.show();

        }

        public static void main(String[] args) {

            launch(args);
        }
    }


