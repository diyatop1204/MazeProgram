/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author diya
 */
public class Maze extends Thread {
    List<Node> nodes = new ArrayList<>();
    Panel panel;
    Path path = new Path();
    
    public Maze(Panel panel)
    {         
        this.panel = panel;
    }
    
    public void setPanel(Panel panel) {
        this.panel = panel;
    }
    
    @Override 
    public void run() {
        Node startedNode = setStartNode();
        Node exitedNode = getNamedNode("W");
        
        if (startedNode == null) {
            System.out.println("Started node is null");
        }
        if (exitedNode == null) {
            System.out.println("Exited node is null");
        }
        
        panel.setStartingNode(startedNode);
        panel.setExitingNode(exitedNode);
        panel.repaint();
        List<Node> findingPath = path.DFSPath(startedNode, exitedNode);
        if (!findingPath.isEmpty()) {
            panel.setPath(findingPath);
            panel.shortestPathAnimation();
        }
    }
    
    public void addNode(Node node) {
        nodes.add(node);
        System.out.println("Added node: " + node.getVertexName());
    }
    
    public Node setStartNode() {
       for (Node node : nodes) {
            if (node.getVertexName().equals("START")) {
                return node;
            }
        }
        return null;
    }
    
    public Node getNamedNode(String name) {
        for (Node node : nodes) {
            if (node.getVertexName().equals(name)) {
                return node;
            }
        }
        return null;
    }
    
    public List<Node> getListNodes() {
        return nodes;
    }
    
    public void addEdges(Node node, String... edges) {
    System.out.println("Adding edges for node: " + node.getVertexName());
    for (String edge : edges) {
        if (!edge.equals("A")) { 
            Node vertex = this.getNamedNode(edge);
            if (vertex == null) {
                System.out.println("Error: Node " + edge + " not found."); 
            } else {
                node.addEdge(vertex);
                vertex.addEdge(node);
                System.out.println("Edge added between " + node.getVertexName() + " and " + vertex.getVertexName());
                }
            }
        }
    }
    
    
    public Maze getMazeFile(String fileName) {
    try {
        Scanner scanner = new Scanner(new File(fileName));
        String heading = scanner.nextLine();
        String[] data = heading.split(",");
        
        int edgesNo = Integer.parseInt(data[0]);
        int columnsNo = Integer.parseInt(data[1]);
        int rowsNo = Integer.parseInt(data[2]);
        
        List<String[]> edgeData = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] vertexData = line.split(",");
            edgeData.add(vertexData);
            String vertex = vertexData[0];
            int x = Integer.parseInt(vertexData[1]);
            int y = Integer.parseInt(vertexData[2]);
            Node node;
            if (vertex.equals("EXIT")) {
                node = new Node("W", x, y);
            } else {
                node = new Node(vertex, x, y);
            }
            this.addNode(node);
        }

        scanner.close();

        for (String[] vertexData : edgeData) {
            Node node = getNamedNode(vertexData[0]);
            if (node != null) {
                String[] edges = new String[vertexData.length - 3];
                System.arraycopy(vertexData, 3, edges, 0, edges.length);
                System.out.println("Adding edges for node: " + node.getVertexName() + " with edges: " + Arrays.toString(edges));
                this.addEdges(node, edges);
            }
        }

        Node startNode = getNamedNode("START");
        Node exitNode = getNamedNode("W");
        if (startNode == null) {
            System.out.println("Started node is null after reading file");
        }
        if (exitNode == null) {
            System.out.println("Exited node is null after reading file");
        }
        
        List<Node> findingPath = path.DFSPath(startNode, exitNode);
        if (findingPath != null && !findingPath.isEmpty()) {
            panel.setPath(findingPath);
        } else {
            System.out.println("No path found from startNode to exitNode.");
        }
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    return this;
    }
    
    public List<Node> getNodes() {
        return nodes;
    }
}
