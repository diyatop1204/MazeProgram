/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author diya
 */
public class Panel extends JPanel {
      
    Maze maze;
    List<Node> path;
    List<Node> currentPath;
    Node startingNode;
    Node exitingNode;
    Timer timer;
    int currentStep;
    boolean animationCompleted;
    
    final int scale = 100;
    
    public Panel()
    {
        this.path = new ArrayList<>();
        this.currentPath = new ArrayList<>();
        this.currentStep = 0;
        this.animationCompleted = false;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        this.startingNode = maze.getNamedNode("START");
        this.exitingNode = maze.getNamedNode("W");
    }
    
    public void shortestPathAnimation() {
        if (maze == null || startingNode == null || exitingNode == null) {
            System.out.println("Maze or Starting/ Ending Node is null. Animation cannot be started.");
            return;
        }
        
        Path findPath = new Path();
        List<Node> completePath = findPath.DFSPath(startingNode, exitingNode);
        Queue<Node> queue = new LinkedList<>(completePath);
        
        currentPath.clear();
        currentStep = 0;
        animationCompleted = false;
        
        timer = new Timer(500, e -> {
            if (!queue.isEmpty()) {
                Node currentNode = queue.poll();
                currentPath.add(currentNode);
                currentStep++;
                repaint();
                  
                if (queue.isEmpty()) {
                    timer.stop();
                    animationCompleted = true;
                    path = new ArrayList<>(currentPath);
                    System.out.println("Animation completed successfully.");
                }
            }
        });
        timer.start();
        System.out.println("Animation started....");
    }
   
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (maze != null) {
            int nodeSize = 20; // setting size of node so it is not too small
            
            List<Node> nodesList = new ArrayList<>(maze.getNodes());
            // draw edges
            g2.setStroke(new BasicStroke(2));
            for (int i = 0; i < nodesList.size(); i++) {
                Node node = nodesList.get(i);
                if (node != null) {
                    int nodeX = node.getX() * scale + 35;
                    int nodeY = node.getY() * scale + 35;
                    
                    List<Node> neighbours = node.getEdgeNodes();
                    for (int j = 0; j < neighbours.size(); j++) {
                    Node neighbour = neighbours.get(j);
                        if (neighbour != null) {
                            int neighbourX = neighbour.getX() * scale + 35;
                            int neighbourY = neighbour.getY() * scale + 35;
                            
                            g.setColor(Color.BLACK);
                            g.drawLine(nodeX, nodeY, neighbourX, neighbourY);
                            //System.out.println("Node: " + node.getVertexName() + "Line drawn from [" + nodeX + ',' + nodeY +"] to ["+ neighbourX + ',' + neighbourY+"]");
                        }
                        else {
                            System.out.println("Could not find neighbours");
                        }
                    }
                }
            }
            
            // draw nodes 
            for (Node node : this.maze.getNodes()) {
                if (node != null) {
                    int nodeX = node.getX() * scale + 25;
                    int nodeY = node.getY() * scale + 25;
     
                    g.setColor(Color.PINK);
                    g.fillOval(nodeX, nodeY, nodeSize, nodeSize);

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Calibri", Font.BOLD, 20));
                    if (node.getVertexName().equals("W")) {
                        g.drawString("EXIT", nodeX + nodeSize, nodeY + nodeSize + 25);
                    }
                    else {
                        g.drawString(node.getVertexName(), nodeX + nodeSize, nodeY + nodeSize + 25);
                    }
                }
            }
            repaint();
            
            // drawing current path - from start to finish
            if (!currentPath.isEmpty()) {
                g.setColor(Color.BLUE);
                for (int i = 0; i < currentPath.size() - 1; i++) {
                    Node current = currentPath.get(i);
                    Node next = currentPath.get(i + 1);
                    int currentX = current.getX() * scale + 25 + nodeSize / 2;
                    int currentY = current.getY() * scale + 25 + nodeSize / 2;
                    int nextX = next.getX() * scale + 25 + nodeSize / 2;
                    int nextY = next.getY() * scale + 25 + nodeSize / 2;
                    g.drawLine(currentX, currentY, nextX, nextY);
                }
            }
            
            repaint();
            
            // drawing final path
            if (animationCompleted) {
                if (!path.isEmpty()) {
                g.setColor(Color.GREEN);
                for (int i = 0; i < path.size() - 1; i++) {
                    Node current = path.get(i);
                    Node next = path.get(i + 1);
                    int currentX = current.getX() * scale + 25 + nodeSize / 2;
                    int currentY = current.getY() * scale + 25 + nodeSize / 2;
                    int nextX = next.getX() * scale + 25 + nodeSize / 2;
                    int nextY = next.getY() * scale + 25 + nodeSize / 2;
                    g.drawLine(currentX, currentY, nextX, nextY);
                    }
                }
            }   
        }
        else {
            System.out.println("Maze could not be created.");
        }
    }

    public void setPath(List<Node> path) {
        this.path = path;
        repaint();
    }
    
    public void setStartingNode(Node startingNode) {
        this.startingNode = startingNode;
    }

    public void setExitingNode(Node exitingNode) {
        this.exitingNode = exitingNode;
    }
}
