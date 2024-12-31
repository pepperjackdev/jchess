package me.pepperjackdev.chess.desktop;

import javax.swing.*;

public class App
    extends JFrame {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;

    public App(String title, int width, int height) {
        super();
        // loading default configurations
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
    }

    public App(String title) {
        this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App("App");
            app.setVisible(true);
        });
    }
}