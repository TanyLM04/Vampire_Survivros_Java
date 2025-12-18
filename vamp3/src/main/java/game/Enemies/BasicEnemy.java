package game.Enemies;

import game.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BasicEnemy extends Enemy {

    private final Player target;

    public BasicEnemy(double x, double y, Player target, EnemyStats stats) {
        super(stats);
        this.target = target;

        Rectangle rect = new Rectangle(25, 25, Color.DARKRED);
        rect.relocate(x, y);
        this.view = rect;
    }

    @Override
    public void update() {
        moveTowards(target);
    }

    @Override
    public void moveTowards(Player player) {
        double enemyCenterX = view.getLayoutX() + view.getBoundsInLocal().getWidth() / 2;
        double enemyCenterY = view.getLayoutY() + view.getBoundsInLocal().getHeight() / 2;

        double playerCenterX = player.getX();
        double playerCenterY = player.getY();

        double dx = playerCenterX - enemyCenterX;
        double dy = playerCenterY - enemyCenterY;

        double length = Math.sqrt(dx * dx + dy * dy);
        if (length == 0) return;

        dx /= length;
        dy /= length;

        view.relocate(
                view.getLayoutX() + dx * stats.speed(),
                view.getLayoutY() + dy * stats.speed()
        );
    }
}
