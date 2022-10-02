package main;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.awt.event.MouseEvent;
import java.lang.Math;

public class Controller {

    static LinkedList<Zombie> z = new LinkedList<Zombie>();
    Zombie tempZombie;

    static LinkedList<Bullet> b = new LinkedList<Bullet>();
    Bullet tempBullet;

    private Player p;
    private float angle;

    private int min = 0;
    private int max = 3;
    private int range = max - min + 1;
    private int x = 0;
    private int y = 0;
    private int rand = 0;
    private double dx;
    private double dy;
    private double radiusSum;
    Game game;

    public Controller(Player p, Game game) {
        this.p = p;
        this.game = game;

        for (int i = 0; i < 7; i++) {
        generateSpawnLocation();
        addZombie(new Zombie(x, y, 29, p, game));    // add a zombie or a few...
        }
    }

    public void mousePressed(MouseEvent e) { 
        // create bullet object with velocity towards mouse click
        angle = (float)Math.atan2(e.getY() - (p.y + 18), e.getX() - (p.x + 18));
        addBullet(new Bullet(p.x + 18, p.y + 18, 10, angle));
        
    }

    public void generateSpawnLocation() {
        rand = (int)(Math.random() * range) + min;
        switch(rand) {
        case 0: // top of screen    x = 0 to 640, y = -50 to -150
            x = (int)(Math.random() * 641);
            y = (int)(Math.random() * -99) - 50;
            break;
        case 1: // bottom of screen x = 0 to 640, y = 530 to 630
            x = (int)(Math.random() * 641);
            y = (int)(Math.random() * 101) + 530;
            break;
        case 2: // left of screen   x = -50 to -150, y = 0 to 480
            x = (int)(Math.random() * -99) - 50;
            y = (int)(Math.random() * 481);
            break;
        case 3: // right of screen  x = 690 to 790, y = 0 to 480
            x = (int)(Math.random() * 101) + 690;
            y = (int)(Math.random() * 481);
            break;
        }
    }

    public void draw(Graphics2D g2d) {
        for(int i = 0; i < z.size(); i++){
            tempZombie = z.get(i);
            tempZombie.draw(g2d);
        }
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            tempBullet.draw(g2d);
        }
    }

    public boolean checkCollision() {
            for (int i = 0; i < b.size(); i++) {
                tempBullet = b.get(i);
                dx = tempZombie.x - tempBullet.x;
                dy = tempZombie.y - tempBullet.y;
                radiusSum = tempZombie.radius + tempBullet.radius;
                if (dx * dx + dy * dy <= radiusSum * radiusSum) {
                    b.remove(i);
                    i--;
                    return true;
                }
            }
            return false;
    }

    
    public boolean inBounds() {
        if (tempBullet.x < -10) {
            return false;
        }
        if (tempBullet.y < -10) {
            return false;
        }
        if (tempBullet.x > 588) {
            return false;
        }
        if (tempBullet.y > 405) {
            return false;
        }
        return true;
    }

    public void update() {

        // UPDATE ZOMBIES
        for(int i = 0; i < z.size(); i++){
            tempZombie = z.get(i);

           if(checkCollision() == false){
                tempZombie.update();
           }
           else {
            z.remove(i);
            i--;
           }
        }

        // UPDATE BULLETS
        for(int i = 0; i < b.size(); i++){ 
            tempBullet = b.get(i);

            if(inBounds()){   // get rid of bullets off screen
                tempBullet.update();
            }
            else {
                b.remove(i);
                i--;
            }
        }
    }

    public void addZombie(Zombie zombie) {
        z.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        z.remove(zombie);
    }

    public void addBullet(Bullet bullet) {
        b.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        b.remove(bullet);
    }

    
}
