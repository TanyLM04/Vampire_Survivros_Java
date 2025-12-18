package game.Main;

import game.Enemies.EnemyManager;
import game.Player;
import game.Weapons.BibleWeapon;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class GameLoop extends AnimationTimer {

    private final Player player;
    private final EnemyManager enemyManager;
    private final BibleWeapon bibleWeapon;

    private long startTime = System.nanoTime();
    private boolean up, down, left, right;

    public GameLoop(Player player, EnemyManager enemyManager, Pane gamePane) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.bibleWeapon = new BibleWeapon(player, gamePane, enemyManager);
    }

    @Override
    public void handle(long now) {

        double elapsedSeconds = (now - startTime) / 1_000_000_000.0;

        if (player.isAlive()) {
            if (up) player.move(0, -1);
            if (down) player.move(0, 1);
            if (left) player.move(-1, 0);
            if (right) player.move(1, 0);

            bibleWeapon.update();
            enemyManager.update(elapsedSeconds, now);
            player.update(now);
        }
    }

    public void setUp(boolean value) { up = value; }
    public void setDown(boolean value) { down = value; }
    public void setLeft(boolean value) { left = value; }
    public void setRight(boolean value) { right = value; }
}

