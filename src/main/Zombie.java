package main;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.lang.Math;

public class Zombie extends Object{

    private double speed = 1.5;
    private String image = "/images/zombieWithFace.png";
    private float angle;
    private Player p;
    private float dx;
    private float dy;
    private float radiusSum;
    Game game;

    public Zombie(float x, float y, float radius, Player p, Game game) {
        super(x, y, radius);
        this.p = p;
        this.game = game;
    }

    private float getAngle() {
        // calculate angle between zombie and player
        angle = (float)Math.atan2(p.y - this.y, p.x - this.x);
        return angle;
    }

    public boolean playerCollision() {
        dx = p.x - this.x;
        dy = p.y - this.y;
        radiusSum = p.radius + this.radius;
        if (dx * dx + dy * dy < radiusSum * radiusSum) {
            return true;
        }
        return false;
    }

    public void update() {
        // collision detection with bullets
        if (playerCollision()) {
            game.endGame();
        }

        angle = getAngle();
        this.x += speed * Math.cos(angle);
        this.y += speed * Math.sin(angle);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(getZombieImage(), (int)x, (int)y, null);
    }

    public Image getZombieImage() {
        ImageIcon i = new ImageIcon(getClass().getResource(image));
        return i.getImage();
    }
}
