package game.obj;


import java.awt.Rectangle;

import game.DasMeteorit;
import game.Obj;

public class Shot extends Obj {
    
    private long startTime;
    private double vx, vy;
    private static int smallAsteroidHitCount;
    
    public Shot(DasMeteorit game, double x, double y, double angle) {
        super(game);
        this.x = x;
        this.y = y;
        this.angle = angle;
        shape = new Rectangle(-1, -1, 2, 2);
        vx = 5 * Math.cos(angle);
        vy = 5 * Math.sin(angle);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        x += vx;
        y += vy;
        
        x = x < -1 ? game.getWidth() : x;
        x = x > game.getWidth() + 1 ? -1 : x;
        y = y < -1 ? game.getHeight() : y;
        y = y > game.getHeight() + 1 ? -1 : y;

        Asteroid hittedAsteroid = (Asteroid) game.checkCollision(this, Asteroid.class);
        if (hittedAsteroid != null) {
            game.showExplosion(x, y);
            game.addScore(DasMeteorit.ASTEROID_SCORE_TABLE[hittedAsteroid.size]);
            
            // new large asteroid is created every time
            // 9 small asteroids are destroyed
            if (hittedAsteroid.size == 1) {
                smallAsteroidHitCount++;
                if ((smallAsteroidHitCount % 9) == 0) {
                    game.createOneAsteroid();
                }
            }
            
            hittedAsteroid.hit();
        }

        Saucer hittedSaucer = (Saucer) game.checkCollision(this, Saucer.class);
        if (hittedSaucer != null) {
            game.showExplosion(x, y);
            game.addScore(DasMeteorit.SAUCER_SCORE_TABLE[hittedSaucer.size]);
            hittedSaucer.hit();
        }
        
        destroyed = System.currentTimeMillis() - startTime > 3500 || hittedAsteroid != null || hittedSaucer != null;
    }
    
}
