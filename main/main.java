package main;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setTitle("2D game");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();

    }

}