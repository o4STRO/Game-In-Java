
package basscube.entities;

import basscube.gamestate.GameState;
import static basscube.gamestate.GameState.xOffset;
import static basscube.gamestate.GameState.yOffset;
import basscube.main.GamePanel;
import basscube.objects.Block;
import basscube.physics.Collision;
import basscube.texture.Textures;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class Player {

//VIDAS E VITORIA
    
    boolean mapcomplete = false;
    private int life = 3;

//MOVIMENTO BASEADO EM BOOLEANS    
    private boolean right = false, left = false, jumping = false, falling = false;
    private boolean topCollision = false;

// SALTOS    
    private double x, y;
    private int width, height;
    
//VELOCIDADE DE MOVIMENTO
    private double moveSpeed = 3;
    
    
// PULO E SUA VELOCIDADE
    private double jumpSpeed = 4;
    private double currentJumpSpeed = jumpSpeed;

// QUEDA E SUA VELOCIDADE    
    private double maxFallSpeed = 10;
    private double currentFallSpeed = 1;
    
    
    
    
    public Player(int width,int height){
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;
        this.width = width;
        this.height = height;
     
    }
    
    public void tick(Block[][] b){
                
//FUNCIONALIDADE DOS BLOCOS
    //COLISÃO E FISICA

        int iX = (int)x;
        int iY = (int)y;
        
        for (int i = 0; i < b.length; i++) {
            
            for (int j = 0; j < b[0].length; j++) {
                
            if(b[i][j].getID() == 1){
            
            //RIGHT
            if(Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset + 2), b[i][j]) 
                    || Collision.playerBlock(new Point (iX + width + (int)GameState.xOffset, iY + height + (int)GameState.yOffset - 1), b[i][j])){
                    right = false;
                }
            //LEFT
            if(Collision.playerBlock(new Point (iX + (int)GameState.xOffset - 1,iY + (int)GameState.yOffset + 2), b[i][j])
                    || Collision.playerBlock( new Point(iX + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset - 1), b[i][j])){
                    left = false;    
                }
            //TOP (TOPO)         
            if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), b[i][j])
                    || Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset), b[i][j])){
                    jumping = false;
                    xOffset = xOffset - 1;
                    falling = true;
                }
            //BASE
            if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), b[i][j])
                    || Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset + 1), b[i][j])){
                    y = b[i][j].getY() - height - GameState.yOffset;
                    falling = false;
                    topCollision = true;
                }
            else{
                    if(!topCollision && !jumping){
                        falling = true;
                        }
                    }
                }
            }            
        }
        
            topCollision = false;
//COLISÃO E FISICA FINAL        
    
//RESPAWN e MORTE INICIAL
        Respawn();
        Die();
//RESPAWN e MORTE FINAL            
        
        if(right){
            GameState.xOffset += moveSpeed ;  
//x = x + 4;
    }
        if(left){           
           GameState.xOffset -= moveSpeed;
//x = x - 4;
        }
        if(jumping){
            GameState.yOffset -= currentJumpSpeed;            
            currentJumpSpeed -= .1;
                if(currentJumpSpeed <= 0){
                    currentJumpSpeed = jumpSpeed;
                    jumping = false;
                    falling = true;
                }
        }
        if(falling){
            GameState.yOffset += currentFallSpeed;
            
                if(currentFallSpeed < maxFallSpeed){
                    currentFallSpeed += .1;               
                }
        }
        if(!falling){
            currentFallSpeed = .1;
        }
    }
  
    private boolean setMoviment = true;
    private boolean confirmPosition;
    
    public void draw(Graphics g){
        //g.setColor(Color.BLACK); 
        
        if(right == true){
            setMoviment = true;
            g.drawImage(Textures.players[0],(int)x, (int)y - 1, 32, 32, null);
        }else if(left == true){
            setMoviment = false;
            confirmPosition = false;
            g.drawImage(Textures.players[1],(int)x, (int)y - 1, 32, 32, null);
        }else{
            if(setMoviment == true){
            confirmPosition = true;
            g.drawImage(Textures.players[0],(int)x, (int)y - 1, 32, 32, null);
            }else if(setMoviment == false){
             confirmPosition = false;
            g.drawImage(Textures.players[1],(int)x,(int)y - 1, 32, 32, null);
            }
        }
        
        //g.fillRect((int)x, (int)y, width, height);
    }
    
    public void keyPressed(int k){
        if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT)     right = true;
        if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT)     left = true;
        if(k == KeyEvent.VK_SPACE && !jumping && !falling ) jumping = true;
            
        
    }
    
    public void keyReleased(int k){
        if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT)     right = false;
        if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT)     left = false;
    }

    
    public void Respawn(){
//--------------------TESTE----------------------
    if(GameState.xOffset >= 5817){
        
        JOptionPane.showMessageDialog(null, "PARABÉNS VOCÊ RECUPEROU O ANEL!");
            mapcomplete = true;
            xOffset = -318;
            yOffset = 260;
            right = false;
            left  = false;
            
            
        }                
//--------------------TESTE----------------------
    }
    
    private double HlastPoint;
    private double WlastPoint;
    private double checkPoint;
    private boolean confirmDie = false;
    
    public void Die(){ 
//--------------------TESTE----------------------     
        if(GameState.yOffset > 500 && life == 3){
            
        life = life - 1; 
            JOptionPane.showMessageDialog(null, "SE F**** PIVETÃO");  
            
            confirmDie = true;    
            
                //xOffset = -318;
                //yOffset = 260;
                right = false;
                left  = false;
                    JOptionPane.showMessageDialog(null, "VOCÊ AINDA TEM " + life + " VIDA(S)");
        }else if(GameState.yOffset > 500 && life == 2){
        
        confirmDie = true;
            
        life = life - 1;     
            JOptionPane.showMessageDialog(null, "PERDEU PLAYBLOY!");  
                //xOffset = -318;
                //yOffset = 260;
                right = false;
                left  = false;
                    JOptionPane.showMessageDialog(null, "VOCÊ AINDA TEM " + life + " VIDA(S)");
        }else if(GameState.yOffset > 500 && life == 1){
            
        confirmDie = true;    
            
        life = life - 1;

                JOptionPane.showMessageDialog(null, "VOCÊ PERDEU A ALIANÇA, SEU NOME AGORA É MANOEL!");     
                    //xOffset = -318;
                    //yOffset = 260;
                    right = false;
                    left  = false;
                            }else if(life == 0){ 
                                System.exit(1);
                            }
        
        //---RESPAWN POINT--

                                    if(falling == false){
                                       WlastPoint = yOffset;
                                       HlastPoint = xOffset;
                                            }if(confirmDie == true){
                                                yOffset = WlastPoint; xOffset = HlastPoint;                                                
                                                    confirmDie = false;
                                            }
//--------------------TESTE----------------------    
    }
    
}