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
public class Path {
    
    public List<Node> DFSPath(Node start, Node end)
    {
        List<Node> visitedNodes = new ArrayList<>();
        List<Node> pathNodes = new ArrayList<>();
        dfs(start, end, visitedNodes, pathNodes);
        return pathNodes;
    }

    // finding shortest path using Depth First Search
    private boolean dfs(Node currentNode, Node endNode, List<Node> visited, List<Node> path) {
        visited.add(currentNode);
        path.add(currentNode);
        if (currentNode.equals(endNode)) {
            return true;
        }
        for (Node neighbour : currentNode.getEdgeNodes()) {
            if (!visited.contains(neighbour)) {
                if (dfs(neighbour, endNode, visited, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

}
