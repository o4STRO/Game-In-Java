
package basscube.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Textures {
    
    public static BufferedImage[] blocks;
    public static BufferedImage[] players;
    public static BufferedImage[] menuwall;
    public static BufferedImage[] citywall;
    public static BufferedImage[] life;
    
    public Textures(){
        blocks = new BufferedImage[1];
        players = new BufferedImage[2];
        menuwall = new BufferedImage[3];
        citywall = new BufferedImage[4];
        life = new BufferedImage[5];
        
        
        try{ 
            menuwall[0] = ImageIO.read(getClass().getResourceAsStream("/menu/menuwall.png"));
            players[0] = ImageIO.read(getClass().getResourceAsStream("/players/player1D.png"));
            players[1] = ImageIO.read(getClass().getResourceAsStream("/players/player1L.png"));
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/blocks/block_iron1.png"));
            citywall[0] = ImageIO.read(getClass().getResourceAsStream("/city/citywall.png"));
        } catch(IOException e){
            System.out.println("Erro em carregar Texturas");
        }
        
        //SISTEMA DE VIDA TEXTURE
        try{
            life[0] = ImageIO.read(getClass().getResourceAsStream("/city/life1.png"));
            
        } catch(IOException e){
            System.out.println("ERROR LOADING TEXTURE LIFE");
        }
        
        
        
        //-------------------------TESTE---------------------------------//
        /**
        try{
            players[0] = ImageIO.read(getClass().getResourceAsStream("/players/player1D.png"));
            players[1] = ImageIO.read(getClass().getResourceAsStream("/players/player1L.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/blocks/block_iron1.png"));    
        } catch(IOException e){
            e.printStackTrace();
        }
        **/

    }
}

    
 
    
