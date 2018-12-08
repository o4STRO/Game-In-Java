
package basscube.gamestate;

import basscube.audiopack.JLayer;
import basscube.entities.Player;
import basscube.mapping.Map;
import basscube.texture.Textures;
import java.awt.Graphics;

public class Level1State extends GameState{
    
    private Player player;
    private Map map;

    public Level1State(GameStateManager gsm){    
        super(gsm);
}

    
    @Override
    public void init() {
        JLayer l = new JLayer();
        JLayer.Jmain(null);
        player = new Player(30,30);
        map = new Map("/maps/map1.map");
        xOffset = -318;//5800;
        yOffset = 300;//200;
 
    }

    @Override
    public void tick() {
        player.tick(map.getBlocks());
    }

    @Override
    public void draw(Graphics g) {
        
        g.drawImage(Textures.citywall[0],((int)GameState.xOffset + 350) * -1, -300, null);
        player.draw(g);    
        map.draw(g);
       

    }

    @Override
    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        player.keyReleased(k);
    }
    
    
    
    
    
}
