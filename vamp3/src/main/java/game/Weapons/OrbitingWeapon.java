package game.Weapons;

import game.Enemies.Enemy;
import game.Enemies.EnemyManager;
import game.IGameEntity;
import game.Player;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class OrbitingWeapon implements IGameEntity {

    protected final Player player;
    protected final Pane gamePane;
    protected final List<Node> projectiles = new ArrayList<>();
    protected final EnemyManager enemyManager;

    protected double radius;
    protected double rotationSpeed;
    protected double angle;

    public OrbitingWeapon(Player player, Pane gamePane, EnemyManager enemyManager) {
        this.player = player;
        this.gamePane = gamePane;
        this.enemyManager = enemyManager;
    }

    protected abstract Node createProjectile();

    protected void spawnProjectiles(int count) {
        gamePane.getChildren().removeIf(projectiles::contains);
        projectiles.clear();

        for (int i = 0; i < count; i++) {
            Node projectile = createProjectile();
            projectiles.add(projectile);
            gamePane.getChildren().add(projectile);
        }
    }

    @Override
    public void update() {
        angle += rotationSpeed;

        int count = projectiles.size();
        for (int i = 0; i < count; i++) {
            double offsetAngle = angle + (2 * Math.PI / count) * i;
            Node projectile = projectiles.get(i);

            double x = player.getX() + Math.cos(offsetAngle) * radius;
            double y = player.getY() + Math.sin(offsetAngle) * radius;

            double width = projectile.getBoundsInLocal().getWidth();
            double height = projectile.getBoundsInLocal().getHeight();

            projectile.relocate(x - width / 2, y - height / 2);
        }

        for (Node projectile : projectiles) {
            for (Enemy enemy : enemyManager.getEnemies()) {
                if (!enemy.isAlive()) continue;

                if (projectile.getBoundsInParent()
                .intersects(enemy.getView().getBoundsInParent())) {

                    enemy.takeDamage(5);
                }
            }
        }
    }
}