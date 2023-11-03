package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler KeyH;

    public Player(GamePanel gp,KeyHandler KeyH){
        this.gp = gp;
        this.KeyH = KeyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
         x = 100;
         y = 100;
         speed = 4;
         direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    public void update(){
        if(KeyH.up) {
            y -= speed;
            direction = "up";
        }
        else if(KeyH.down) {
            y += speed;
            direction = "down";
        }
        else if (KeyH.left) {
            x -= speed;
            direction = "left";
        }
        else if(KeyH.right) {
            x += speed;
            direction = "right";
        }

        if(strideCounter > 10){
            if(strideNum){
                strideNum = false;
            }
            else strideNum = true;
            strideCounter = 0;
        }
        strideCounter++;
    }
    public void draw(Graphics2D g2){
        BufferedImage image = up1;
        switch(direction) {
        case "up":
            if(strideNum) image = up1;
            else image = up2;
            break;
        case "down":
            if(strideNum) image = down1;
            else image = down2;
            break;
        case "left":
            if(strideNum) image = left1;
            else image = left2;
            break;
        case "right":
            if(strideNum) image = right1;
            else image = right2;
            break;
        }
        g2.drawImage(image, x,y,gp.tileSize,gp.tileSize,null );
 
    }
}
