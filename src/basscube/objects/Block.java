
package basscube.objects;


import basscube.gamestate.GameState;
import basscube.texture.Textures;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Block extends Rectangle {
    
    private static final long serialVersionUID = 1L;
    
    public static final int blockSize = 64;
    private int id;
  
    public Block(int x, int y, int id){
        setBounds(x, y, blockSize, blockSize);    
        this.id = id;
    }
    
    public void tick(){
       
    }
    
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        if(id == 1){
            g.drawImage(Textures.blocks[id - 1],x - (int)GameState.xOffset, y - (int)GameState.yOffset, 64, 64, null);
            //g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height);
       }
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public int getID(){
        return id;
    }
    
    
}
