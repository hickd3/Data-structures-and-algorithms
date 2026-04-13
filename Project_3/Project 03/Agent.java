/*
 * File: Agent.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 03
 * 
*/
import java.awt.Graphics;

public class Agent {
    private double x;  // the x position of the agent
    private double y;  // the y position of the agent

    public Agent(double x0, double y0) {
        x = x0;
        y = y0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double newX) {
        x = newX;
    }

    public void setY(double newY) {
        y = newY;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void updateState(Landscape scape){}

    public void draw(Graphics g){}

    public static void main(String[] args) {
        // Create an instance of Agent using an anonymous subclass
        Agent agent = new Agent(10, 5);

        // Demonstrate usage of the class
        System.out.println("getX: " + agent.getX());
        System.out.println("getY: " + agent.getY());
        agent.setX(3);
        System.out.println("set x to 3: " + agent.getX());
        agent.setY(7);
        System.out.println("set y to 7: " + agent.getY());
        System.out.println(agent.toString());
    }
}

