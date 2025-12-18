package game.Weapons;

import game.Enemies.EnemyManager;
import game.Player;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;

public class BibleWeapon extends OrbitingWeapon {
    private int bibleCount = 3;

    public BibleWeapon(Player player, Pane gamePane, EnemyManager enemyManager) {
        super(player, gamePane, enemyManager);

        this.radius = 70;
        this.rotationSpeed = 0.04;

        spawnProjectiles(bibleCount);
    }

    @Override
    protected Node createProjectile() {
        Circle bible = new Circle(12);
        bible.setFill(Color.PURPLE);
        return bible;
    }

    public void increaseBibles() {
        bibleCount++;
        spawnProjectiles(bibleCount);
    }
}
