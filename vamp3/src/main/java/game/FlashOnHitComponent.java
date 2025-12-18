package game;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class FlashOnHitComponent {

    private static final long FLASH_DURATION = 150_000_000; // 150 MS

    private final Shape view;
    private final Paint normalColor;
    private final Paint hitColor = Color.ORANGE;

    private long lastHitTime = -1;

    public FlashOnHitComponent(Shape view, Paint normalColor) {
        this.view = view;
        this.normalColor = normalColor;
    }

    public void onHit() {
        lastHitTime = System.nanoTime();
        view.setFill(hitColor);
    }

    public void update(long now) {
        if (lastHitTime < 0) return; // no active flash

        if (now - lastHitTime >= FLASH_DURATION) {
            view.setFill(normalColor);
            lastHitTime = -1; // flash finished
        }
    }
}
