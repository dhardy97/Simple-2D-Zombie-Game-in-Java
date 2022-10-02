package main;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.lang.Math;

public class Bullet extends Object {

    private String image = "/images/bullet.png"; // 10x10 pixels

    private double speed = 10;
    private float angle;
    

    public Bullet(float x, float y, float radius, float angle) {
        super(x, y, radius);
        this.angle = angle;
        
    }

    public void update() {
        this.x += speed * Math.cos(angle);
        this.y += speed * Math.sin(angle);

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(getBulletImage(), (int)x, (int)y, null);
    }

    public Image getBulletImage() {
        ImageIcon i = new ImageIcon(getClass().getResource(image));
        return i.getImage();
    }
    
}
