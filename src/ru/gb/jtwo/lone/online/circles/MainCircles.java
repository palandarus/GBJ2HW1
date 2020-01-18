package ru.gb.jtwo.lone.online.circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    Background_Color backgroundColor = new Background_Color();
    Sprite[] sprites = new Sprite[5];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        initApplication();
        GameCanvas canvas = new GameCanvas(this);
        add(canvas, BorderLayout.CENTER);
        setTitle("Circles");
        setVisible(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int button = e.getButton();
//                обработка клика левой клавишей мыши
                if (button == 1) {
                    for (int i = 0; i < sprites.length; i++) {
                        if (sprites[i].isInside(x, y)) {
                            Ball ball = (Ball) sprites[i];
                            ball.setColor();
                        }
                    }
                    Sprite[] newsprites = new Sprite[sprites.length + 1];
                    for (int i = 0; i < sprites.length; i++) {
                        newsprites[i] = sprites[i];
                    }
                    newsprites[sprites.length] = new Ball();
                    newsprites[sprites.length].x = x;
                    newsprites[sprites.length].y = y;
                    sprites = newsprites.clone();
//                    обработка клика правой клавишей
                } else if (button == 3) {
                    if (sprites.length != 0) {

                        Sprite[] newsprites = new Sprite[sprites.length - 1];

                        for (int i = sprites.length-1; i >0; i--) {
                            newsprites[i-1] = sprites[i];
                        }
                        sprites = newsprites.clone();
                    }
                }
            }
        });
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime); // obnovlenie // S = v * t
        render(canvas, g); // otrisovka
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
        backgroundColor.update(deltaTime);
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
        backgroundColor.render(canvas);
    }

}
