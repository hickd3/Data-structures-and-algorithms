/*
 * File: AntiSocialAgent.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 03
 * 
*/

import java.awt.Color;
import java.awt.Graphics;

public class AntiSocialAgent extends Agent {
    private boolean moved;
    private int radius;

    public AntiSocialAgent(double x0, double y0, int radius) {
        super(x0, y0);
        this.radius = radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void draw(Graphics g) {
        if (!moved)
            g.setColor(new Color(255, 0, 0)); // Darker shade of red
        else
            g.setColor(new Color(255, 125, 125)); // Lighter shade of red

        int circleSize = 10;
        int circleX = (int) getX() - (circleSize / 2);
        int circleY = (int) getY() - (circleSize / 2);
        g.fillOval(circleX, circleY, circleSize, circleSize);
    }

   
}
