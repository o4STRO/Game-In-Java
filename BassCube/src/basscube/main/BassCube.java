
package basscube.main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author ASTRO
 */
public class BassCube {

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Bass Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new GamePanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
        
        
        
    }
    
}

