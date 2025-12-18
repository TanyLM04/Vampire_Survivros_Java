package game.Main;

import game.Enemies.EnemyManager;
import game.Player;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class GameController {

    @FXML
    private Pane gamePane;
    private Player player;
    private GameLoop gameLoop;

    @FXML
    public void initialize() {
        player = new Player(400, 300);
        gamePane.getChildren().add(player.getView());

        EnemyManager enemyManager = new EnemyManager(gamePane, player);

        gamePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                gameLoop = new GameLoop(player, enemyManager, gamePane);
                gameLoop.start();


                gamePane.getScene().setOnKeyPressed(e -> {
                    switch (e.getCode()) {
                        case W -> gameLoop.setUp(true);
                        case S -> gameLoop.setDown(true);
                        case A -> gameLoop.setLeft(true);
                        case D -> gameLoop.setRight(true);
                    }
                });

                gamePane.getScene().setOnKeyReleased(e -> {
                    switch (e.getCode()) {
                        case W -> gameLoop.setUp(false);
                        case S -> gameLoop.setDown(false);
                        case A -> gameLoop.setLeft(false);
                        case D -> gameLoop.setRight(false);
                    }
                });
            }
        });
    }
}

