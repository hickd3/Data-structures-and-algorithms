/*
 * File: SocialAgentSimulation.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 03
 * 
*/
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SocialAgentSimulation {
    private static final int WIDTH = 800; // Width of the landscape
    private static final int HEIGHT = 600; // Height of the landscape
    private static final int RADIUS = 30;
    private static final int NUM_AGENTS = 200; // Number of agents
    

    private Landscape landscape;
    private Display display;

    public SocialAgentSimulation(int width, int height, int numAgents) {
        landscape = new Landscape(width, height);
        display = new Display(landscape, width, height);
        generateAgents(numAgents);
    }

    public void runSimulation() throws InterruptedException {
        while (true) {
            updateAgents();
            display.repaint();
            Thread.sleep(100);
        }
    }

    private void generateAgents(int numAgents) {
        Random random = new Random();
        for (int i = 0; i < numAgents; i++) {
            int x = random.nextInt(landscape.getWidth());
            int y = random.nextInt(landscape.getHeight());
            Agent agent = new Agent(x, y);
            landscape.addAgent(agent);
        }
    }

    private void updateAgents() {
        ArrayList<Agent> agents = landscape.getAgents();
        for (Agent agent : agents) {
            int dx = (int) (Math.random() * 3) - 1;
            int dy = (int) (Math.random() * 3) - 1;
            agent.move(dx, dy);

            int x = agent.getX();
            int y = agent.getY();
            int radius = RADIUS;
            x = Math.max(0, Math.min(x, landscape.getWidth() - 1));
            y = Math.max(0, Math.min(y, landscape.getHeight() - 1));
            agent.setPosition(x, y);
        }
    }

    public static void main(String[] args) {
        int width = WIDTH;
        int height = HEIGHT;
        int numAgents = NUM_AGENTS;

        if (args.length >= 2) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }

        if (args.length >= 3) {
            numAgents = Integer.parseInt(args[2]);
        }

        try {
            SocialAgentSimulation simulation = new SocialAgentSimulation(width, height, numAgents);
            simulation.runSimulation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Landscape {
        private int width;
        private int height;
        private ArrayList<Agent> agents;

        public Landscape(int width, int height) {
            this.width = width;
            this.height = height;
            agents = new ArrayList<>();
        }

        public void addAgent(Agent agent) {
            agents.add(agent);
        }

        public ArrayList<Agent> getAgents() {
            return agents;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    private static class Agent {
        private int x;
        private int y;

        public Agent(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void move(int dx, int dy) {
            x += dx;
            y += dy;
        }
    }

    private static class Display extends JPanel {
        private Landscape landscape;
        private int width;
        private int height;

        public Display(Landscape landscape, int width, int height) {
            this.landscape = landscape;
            this.width = width;
            this.height = height;

            JFrame frame = new JFrame("Agent Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);
            frame.getContentPane().add(this);
            frame.setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw agents
            ArrayList<Agent> agents = landscape.getAgents();
            for (Agent agent : agents) {
                int agentX = agent.getX();
                int agentY = agent.getY();
                g.setColor(Color.BLACK);
                g.fillOval(agentX, agentY, 5, 5);
            }
        }
    }
}




