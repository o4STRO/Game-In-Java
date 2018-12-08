
package basscube.gamestate;

import basscube.audiopack.JLayer;
import basscube.main.GamePanel;
import basscube.texture.Textures;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{
    
    private String[] options = {"Start","Help","Quit"};
    private int currentSelection = 0;
    
    public MenuState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    public void init(){}
    @Override
    public void tick() {
        
    }
    
    @Override
    public void draw(Graphics g) {
        
        g.drawImage(Textures.menuwall[0],0, 0,null);
        
        //g.setColor(new Color(117, 188, 177));
        //g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
     
        for (int i = 0; i < options.length; i++) {  
            if(i == currentSelection){
                g.setColor(Color.RED);                
            } else {
                g.setColor(Color.BLACK);          
            }
            
            
            g.setFont(new Font("3Dventure", Font.PLAIN, 50));
            g.drawString(options[i], GamePanel.WIDTH / 2 - 350, 440 + i * 60);
            
        }
    }

    @Override
    public void keyPressed(int k) {
        
//MOVIMENTO DO MENU
        
        if (k == KeyEvent.VK_DOWN){
            currentSelection++;
            if(currentSelection >= options.length){
                currentSelection = 0;
            }
        } else if(k == KeyEvent.VK_UP) {
             currentSelection--;
             if(currentSelection < 0) {
                 currentSelection = options.length - 1;
             }
        }
        
//OPÇÕES DO MENU
        
        if(k == KeyEvent.VK_ENTER){
            switch (currentSelection) {
            
            //PLAY
                case 0:
                    gsm.states.push(new Level1State(gsm));
                    break;
            //HELP
                case 1:
                    
                    break;
            //QUIT
                case 2:
                    System.exit(0);
                default:
                    break;
            }
        }
        
    }

    @Override
    public void keyReleased(int k) {
        
    }
    
}
