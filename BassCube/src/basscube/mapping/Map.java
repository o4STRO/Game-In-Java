
package basscube.mapping;

import basscube.objects.Block;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {
    
    private String path;
    private int width;
    private int height;
    
    private Block[][] blocks;
    
    public Map(String loadPath){
        path = loadPath;        
        loadMap();
    }
    
    public void draw(Graphics g){
        
        for (Block[] block : blocks) {
            for (int j = 0; j < blocks[0].length; j++) {
                block[j].draw(g);
            }
        }
        
        
    }
    
    public void loadMap(){
        
        InputStream is = this.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));


        try {
            //PESQUISAR MELHOR SOBRE
            
            
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());
            
            blocks = new Block[height][width];
            for(int y = 0; y < height; y++) {
                String line = br.readLine();
                
                String[] tokens = line.split("");       
                
                for(int x = 0; x < width; x++) {
                    blocks[y][x] = new Block(x * Block.blockSize, y * Block.blockSize, Integer.parseInt(tokens[x]));
                }
            }
        }catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    
    public Block[][] getBlocks(){
        return blocks;
    }

}


