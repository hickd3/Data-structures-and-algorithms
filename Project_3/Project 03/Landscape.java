/*
 * File: Landscape.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 03
 * 
*/
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;

public class Landscape {
    private int width;
    private int height;
    private LinkedList<Agent> agents;

    // Constructor that sets the width, height, and initializes the agent list
    public Landscape(int w, int h) {
        width = w;
        height = h;
        agents = new LinkedList<>();
    }

    // Returns the height of the landscape
    public int getHeight() {
        return height;
    }

    // Returns the width of the landscape
    public int getWidth() {
        return width;
    }

    // Inserts an agent at the beginning of the agent list
    public void addAgent(Agent a) {
        agents.addFirst(a);
    }

    // Returns a string representation of the landscape, indicating the number of agents
    public String toString() {
        return "Landscape with " + agents.size() + " agents";
    }

    // Returns a linked list of agents within a specified radius of a given point
    public LinkedList<Agent> getNeighbors(double x0, double y0, double radius) {
        LinkedList<Agent> neighbors = new LinkedList<>();
        for (Agent agent : agents) {
            double x = agent.getX();
            double y = agent.getY();
            double distance = Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0));
            if (distance <= radius) {
                neighbors.add(agent);
            }
        }
        return neighbors;
    }

    // Calls the draw method of all the agents on the landscape
    public void draw(Graphics g) {
        for (Agent agent : agents) {
            agent.draw(g);
        }
    }

   // Updates the state of each agent in a random order
   public void updateAgents() {
    ArrayList<Agent> shuffledAgents = new ArrayList<>(agents);
    Collections.shuffle(shuffledAgents); // Shuffle the agent list to update in a random order
    for (Agent agent : shuffledAgents) {
        agent.updateState(this);
    }
}
    // main method
    public static void main(String[] args) {
        Landscape landscape = new Landscape(800, 600);

        Agent agent1 = new Agent(100, 200);
        landscape.addAgent(agent1);

        Agent agent2 = new Agent(300, 400);
        landscape.addAgent(agent2);

        Agent agent3 = new Agent(500, 100);
        landscape.addAgent(agent3);

        System.out.println("Width: " + landscape.getWidth());
        System.out.println("Height: " + landscape.getHeight());
        System.out.println("Number of agents: " + landscape.toString());

        double x0 = 200;
        double y0 = 300;
        double radius = 150;
        LinkedList<Agent> neighbors = landscape.getNeighbors(x0, y0, radius);
        System.out.println("Neighbors within radius: " + neighbors.size());

        Graphics g = null; // Replace with an actual Graphics object
        landscape.draw(g);
        landscape.updateAgents();
    }
}
