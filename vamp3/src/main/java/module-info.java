module com.example.vamp3 {
    requires javafx.controls;
    requires javafx.fxml;

    exports game;
    opens game to javafx.fxml;
    exports game.Enemies;
    opens game.Enemies to javafx.fxml;
    exports game.Weapons;
    opens game.Weapons to javafx.fxml;
    exports game.Main;
    opens game.Main to javafx.fxml;
}