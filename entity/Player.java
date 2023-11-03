package entity;

import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler KeyH;

    public Player(GamePanel gp,KeyHandler KeyH){
        this.gp = gp;
        this.KeyH = KeyH;

        setDefaultValues();
    }
    public void setDefaultValues(){
         x = 100;
         y = 100;
         speed = 4;
    }
    public void update(){
        if(KeyH.up) y -= speed;
        else if(KeyH.down) y += speed;
        else if (KeyH.left) x -= speed;
        else if(KeyH.right) x += speed;
    }
    public void draw(Graphics2D g2){
  
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
 
    }
}
