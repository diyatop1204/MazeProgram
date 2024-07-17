/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diya
 */
public class Node {
    private String vertexName;
    private int x; 
    private int y;
    private List<Node> neighbours;
    
    public Node(String vertexName, int x, int y) {
        this.vertexName = vertexName;
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
    }
    
    public void addEdge(Node node) {
        if (!neighbours.contains(node)) {
            neighbours.add(node);
        }
    }
    
    public String getVertexName() {
        return vertexName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Node> getEdgeNodes() {
        return neighbours;
    }
    
    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setVertexName(String vertexName) {
        this.vertexName = vertexName;
    }

}
