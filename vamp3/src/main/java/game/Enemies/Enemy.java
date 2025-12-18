package game.Enemies;

import game.IDamageable;
import game.IGameEntity;
import game.Player;
import javafx.scene.Node;

public abstract class Enemy implements IGameEntity, IDamageable {

    protected Node view;
    protected final EnemyStats stats;
    protected int health;

    protected Enemy(EnemyStats stats) {
        this.stats = stats;
        this.health = stats.maxHealth();
    }

    public abstract void moveTowards(Player player);

    public Node getView() {
        return view;
    }
    public int getDamage() {
        return stats.damage();
    }
    @Override
    public void takeDamage(int amount) {
        health -= amount;
    }
    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
