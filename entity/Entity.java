package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int x, y, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int strideCounter = 0;
    public boolean strideNum = false;
    public Rectangle solidArea;
    public boolean collisonOn = false;
}
