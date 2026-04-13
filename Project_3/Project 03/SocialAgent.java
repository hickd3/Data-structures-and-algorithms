/*
 * File: SocialAgent.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 03
 * 
*/
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class SocialAgent extends Agent {
    private boolean moved;
    private int radius;

    public SocialAgent(double x0, double y0, int radius) {
        super(x0, y0); // Call the superclass constructor
        this.radius = radius; // Set the radius field
        this.moved = false; // Initialize the moved field to false
    }

    public void setRadius(int radius) {
        this.radius = radius; // Set the radius field to the given value
    }

    public int getRadius() {
        return radius; // Return the radius value
    }

    public void draw(Graphics g) {
        if (!moved)
            g.setColor(new Color(0, 0, 255)); // Dark blue if the agent didn't move
        else
            g.setColor(new Color(125, 125, 255)); // Light blue if the agent moved

        int size = 5;
        int x = (int) getX() - size / 2;
        int y = (int) getY() - size / 2;
        g.fillOval(x, y, size, size); // Draw a filled oval at the agent's location
    }

    public void updateState(Landscape scape) {
        moved = false; // Reset moved to false
        Random random = new Random();
        int neighbors = scape.getNeighbors(this.getX(), this.getY(), this.getRadius()).size();

        if (neighbors < 4) {
            double newX = getX() + random.nextDouble() * 20 - 10; // Generate a random value between -10 and 10
            double newY = getY() + random.nextDouble() * 20 - 10; // Generate a random value between -10 and 10

            // Check if the new coordinates are within the landscape boundaries
            newX = Math.max(Math.min(newX, scape.getWidth()), 0);
            newY = Math.max(Math.min(newY, scape.getHeight()), 0);

            setX(newX); // Update the x coordinate
            setY(newY); // Update the y coordinate

            moved = true; // Set moved to true
        }
    }

    // Additional methods and main for testing
    public static void main(String[] args) {
        SocialAgent social = new SocialAgent(5, 10, 5);
        System.out.println("getX: " + social.getX());
        System.out.println("getY: " + social.getY());
        social.setX(7);
        System.out.println("set x to 7: " + social.getX());
        social.setY(3);
        System.out.println("set y to 3: " + social.getY());
        System.out.println(social.toString());
        System.out.println("radius: " + social.getRadius());
        social.setRadius(4);
        System.out.println("new radius: " + social.getRadius());
        social.setRadius(5);
        System.out.println("radius: " + social.getRadius());

        Landscape scape = new Landscape(100, 50);
        SocialAgent agent1 = new SocialAgent(5, 5, 25);
        scape.addAgent(agent1);
        agent1.updateState(scape);

        SocialAgent agent2 = new SocialAgent(10, 10, 25);
        scape.addAgent(agent2);
        SocialAgent agent3 = new SocialAgent(15, 15, 25);
        scape.addAgent(agent3);
        SocialAgent agent4 = new SocialAgent(20, 20, 20);
        scape.addAgent(agent4);

        System.out.println("neighbors within a radius of 10 (should be 1): " + scape.getNeighbors(5, 5, 10));
        System.out.println("neighbors within a radius of 15 (should be 2): " + scape.getNeighbors(5, 5, 15));
        System.out.println("neighbors within a radius of 25 (should be 3): " + scape.getNeighbors(5, 5, 25));
    }
}
