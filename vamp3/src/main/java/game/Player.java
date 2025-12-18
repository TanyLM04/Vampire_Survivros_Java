package game;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Player implements IDamageable{

    private Rectangle view;

    private static final int MAX_HP = 100;
    private static final int TOP_SPEED = 3;

    private double speed = TOP_SPEED;
    private int health = MAX_HP;

    private boolean alive = true;

    private long lastHitTime = 0;
    private static final long INVULNERABILITY_TIME = 500_000_000L;
    private final FlashOnHitComponent flash;

    private static final Paint NORMAL_COLOR = Color.BLUE;
    private static final Paint DEAD_COLOR = Color.GRAY;

    public Player(double x, double y) {
        view = new Rectangle(30, 30);
        view.setFill(NORMAL_COLOR);
        view.relocate(x, y);

        flash = new FlashOnHitComponent(view, NORMAL_COLOR);
    }

    public void move(double dx, double dy) {
        if (!alive) return;

        view.relocate(
                view.getLayoutX() + dx * speed,
                view.getLayoutY() + dy * speed
        );
    }

    @Override
    public void takeDamage(int amount) {
        if (!alive) return;

        // INVULNERABILITY FRAMES
        long now = System.nanoTime();
        if (now - lastHitTime < INVULNERABILITY_TIME) return;
        lastHitTime = now;

        // FLASH ON HIT
        health -= amount;
        flash.onHit();

        //DEATH
        if (health <= 0) {
            die();
        }
    }

    private void die() {
        alive = false;
        view.setFill(DEAD_COLOR);
        System.out.println("Player died");
    }

    public Rectangle getView() {
        return view;
    }
    public double getX() {
        return view.getLayoutX() + view.getBoundsInLocal().getWidth() / 2;
    }
    public double getY() {
        return view.getLayoutY() + view.getBoundsInLocal().getHeight() / 2;
    }
    @Override
    public boolean isAlive() {
        return alive;
    }

    public void update(long now) {
        if (!alive) return;

        flash.update(now);
    }
}

