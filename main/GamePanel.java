package main;

import javax.swing.JPanel;
import object.SuperObject;
import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGs
    final int originalTileSize = 16;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    int FPS = 60;
    TileManager tileManager = new TileManager(this);
    Thread gameThread;
    KeyHandler KeyH = new KeyHandler();
    public Player player = new Player(this, KeyH);
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public SuperObject obj[] = new SuperObject[10];
    Sound sound = new Sound();
    Sound music = new Sound();
    public UI ui = new UI(this);
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);

    }

    public void setUpGame(){
        assetSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + interval;
            while (gameThread != null) {
            try {
                double sleepTime = nextDrawTime - System.nanoTime();
                if (sleepTime < 0)
                    sleepTime = 0;
                Thread.sleep((long) sleepTime / 1000000);
                nextDrawTime += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 1 update
            update();
            // 2 draw
            repaint();
        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for(int i = 0; i < obj.length;i++) if(obj[i] != null) obj[i].draw(g2, this);
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();

    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
