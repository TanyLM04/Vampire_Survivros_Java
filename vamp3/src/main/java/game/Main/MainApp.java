package game.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/game/game.fxml")
        );
        Scene scene = new Scene(loader.load(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Mini Vampire Survivors");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

