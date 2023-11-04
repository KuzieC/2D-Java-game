package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    String mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new String[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        loadMap("/map/world01.txt");

    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    mapTileNum[row][col] = numbers[col];
                    col++;
                    // int num = Integer.parseInt(numbers[col]);
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int col = 0, row = 0, x = 0, y = 0;

        while (row < gp.maxWorldRow) {
            int worldX = col*gp.tileSize;
            int worldY = row*gp.tileSize;
            int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
            int ScreenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX > gp.player.worldX - gp.player.screenX-3*gp.tileSize && 
                worldX < gp.player.worldX + gp.player.screenX+3*gp.tileSize &&
                worldY > gp.player.worldY - gp.player.screenY-3*gp.tileSize &&
                worldY < gp.player.worldY + gp.player.screenY+3*gp.tileSize){
                     int tileType = Integer.parseInt(mapTileNum[row][col]);
                    g2.drawImage(tile[tileType].image, ScreenX, ScreenY, gp.tileSize, gp.tileSize, null);
          
                }
            col++;
            x += gp.tileSize;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

}
