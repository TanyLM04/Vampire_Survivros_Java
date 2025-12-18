package game.Enemies;

import game.Player;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

public class EnemyManager {

    private final List<Enemy> enemies = new ArrayList<>();
    private final Pane gamePane;
    private final Player player;
    private final Random random = new Random();
    private long lastSpawnTime = 0;

    public EnemyManager(Pane gamePane, Player player) {
        this.gamePane = gamePane;
        this.player = player;
    }

    public void spawnEnemy(double elapsedSeconds) {
        double x = random.nextBoolean() ? 0 : 800;
        double y = random.nextDouble() * 600;

        Enemy enemy = EnemyFactory.createBasicEnemy(x, y, player, elapsedSeconds);

        enemies.add(enemy);
        gamePane.getChildren().add(enemy.getView());
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void update(double elapsedSeconds, long now) {

        double spawnInterval = Math.max(
                0.3, 1.5 - elapsedSeconds * 0.02
        );

        if ((now - lastSpawnTime) / 1_000_000_000.0 > spawnInterval) {
            spawnEnemy(elapsedSeconds);
            lastSpawnTime = now;
        }

        Iterator<Enemy> iterator = enemies.iterator();

        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update();

            //  PLAYER COLLISION
            if (enemy.getView().getBoundsInParent()
                .intersects(player.getView().getBoundsInParent())) {

                player.takeDamage(enemy.getDamage());
            }

            // ENEMY DEATH
            if (!enemy.isAlive()) {
                gamePane.getChildren().remove(enemy.getView());
                iterator.remove(); // SAFE removal
            }
        }
    }
}
