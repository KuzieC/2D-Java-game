package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager{
    GamePanel gp;
    Tile[] tile;
    String mapTileNum[][];
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new String[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap();

    }
    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/map/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0,row = 0;
            while(col<gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    mapTileNum[row][col] = numbers[col];
                    col++;
                    //int num = Integer.parseInt(numbers[col]);
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row ++;
                }
            }
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void draw(Graphics2D g2){

        int col =0,row = 0,x = 0,y = 0;

         while(row < gp.maxScreenRow){
            int tileType = Integer.parseInt(mapTileNum[row][col]);
            System.out.print(tileType);
            g2.drawImage(tile[tileType].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x+=gp.tileSize;
            if(col == gp.maxScreenCol){
                System.out.println();
                col = 0;
                row++;
                y+=gp.tileSize;
                x = 0;
            }
        }
    }

}
