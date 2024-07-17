/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author diya
 */
public class BinaryMaze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Panel panel = new Panel();
        Maze maze = new Maze(panel);
        maze.getMazeFile("Maze1.txt"); // change manually to select file: getMazeFile("Maze2.txt");
        panel.setMaze(maze);
        
        SwingUtilities.invokeLater(() -> {   
            JFrame frame = new JFrame("Maze");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setSize(800, 800);
            frame.setVisible(true);
            
            panel.shortestPathAnimation();
        });
    }
    
}
