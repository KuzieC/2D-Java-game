package main;
import javax.swing.JPanel;

import entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGs
    final int originalTileSize = 16;
    final int scale = 2;
    public int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;
    Thread gameThread;
    KeyHandler KeyH = new KeyHandler();
    Player player = new Player(this, KeyH);
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
        double interval = 1000000000/FPS;

        double nextDrawTime = System.nanoTime() + interval;

        while(gameThread != null){
            
            try {
                double sleepTime = nextDrawTime - System.nanoTime();
                if(sleepTime < 0) sleepTime = 0;
                Thread.sleep((long)sleepTime/1000000);
                nextDrawTime += interval;
            } catch (InterruptedException e) {
               
                e.printStackTrace();
            }
            
            //1 update
            update();
            //2 draw
            repaint();
        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void update(){
      player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}

