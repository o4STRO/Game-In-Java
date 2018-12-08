
//PAINEL DO LOOP DO JOGO

package basscube.main;

import basscube.gamestate.GameStateManager;
import basscube.texture.Textures;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

    public class GamePanel extends JPanel implements Runnable, KeyListener{      
   
    //Configurações do jogo
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    private Thread thread;
    private boolean isRunning = false;
    
    private int FPS = 60;
    private long targetTime = 1000/ FPS;
 
    private GameStateManager gsm;
    
    public GamePanel(){
   
    //Painel Do Jogo
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    
        addKeyListener(this);
        setFocusable(true);
        
        new Textures();
        
        start();     
    }  
  
    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
        
    }
    
    // Execuções do jogo
    public void run(){
        long start, elapsed, wait;
        
        gsm = new GameStateManager();
        
        while(isRunning){
            start = System.nanoTime();
            
            tick();
            repaint();
            
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            
    //Para se manter funcionando em 60 FPs mesmo que mude o computador.
            if(wait < 0) {
                wait = 5;
            }
            
    //Um bloco “try” é chamado de bloco “protegido” porque, 
    //caso ocorra algum problema com os comandos dentro do bloco, 
    //a execução desviará para os blocos “catch” correspondentes.       
            try{
                Thread.sleep(wait);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void tick(){
        gsm.tick();
       
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        gsm.draw(g);
    }
    
    
    public void keyTyped(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
        
    }

    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }
    
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }
}
