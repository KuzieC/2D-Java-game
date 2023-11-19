package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public Rectangle solidArea = new Rectangle(0,0,32,32);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public void draw(Graphics2D g2, GamePanel gp){
        int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
        int ScreenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX > gp.player.worldX - gp.player.screenX-3*gp.tileSize && 
            worldX < gp.player.worldX + gp.player.screenX+3*gp.tileSize &&
            worldY > gp.player.worldY - gp.player.screenY-3*gp.tileSize &&
            worldY < gp.player.worldY + gp.player.screenY+3*gp.tileSize){
                g2.drawImage(image, ScreenX, ScreenY, gp.tileSize, gp.tileSize, null);
      
            }
    }
}