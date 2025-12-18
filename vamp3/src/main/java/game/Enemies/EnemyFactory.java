package game.Enemies;

import game.Player;

public class EnemyFactory {
    public static Enemy createBasicEnemy(double x, double y, Player target, double elapsedSeconds) {

        int baseHealth = 30;
        double baseSpeed = 1.2;
        int baseDamage = 10;

        int scaledHealth = (int) (baseHealth * (1 + elapsedSeconds * 0.2));
        double scaledSpeed = baseSpeed + elapsedSeconds * 0.02;
        int scaledDamage = baseDamage + (int)(elapsedSeconds * 0.05);

        EnemyStats stats = new EnemyStats(
                scaledHealth,
                scaledSpeed,
                scaledDamage
        );

        return new BasicEnemy(x, y, target, stats);
    }
}
