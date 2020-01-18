package ru.gb.jtwo.lone.online.circles;

import java.awt.*;
import java.util.ArrayList;

public class Background_Color extends Background {

    //    private ArrayList<Color> color = new ArrayList<>();
//    private int count = 0;
//    boolean flag = true;
    Color color;
    double rgb;
    int vD;


    public Background_Color() {
        rgb = Math.random() * 360;
        color = new Color((int) (Math.sin(rgb) + 1) * 8388607);
        vD = 1;


    }

    @Override
    void update(float deltaTime) {

        rgb += vD * deltaTime / 1000;
        if (rgb >= 360) {
            vD = -1;
            rgb = 360;
        }
        if (rgb <= 0) {
            vD = 1;
            rgb = 0;
        }

        color = new Color((int) ((Math.sin(rgb) + 1) * 8388607));//предельное значение цвета при 255,255,255 16777215
    }                                                            //поэтому берем половину

    @Override
    void render(GameCanvas canvas) {
        canvas.setBackground(color);
    }
}
