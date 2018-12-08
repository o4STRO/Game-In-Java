
package basscube.audiopack;

import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class JLayer {
    
    public static void Jmain(String[] args){
        
        String path = "res/audio/manuel.mp3";
        
        File mp3File = new File(path);
        
        MP3Music music = new MP3Music();
        music.touch(mp3File);
        
        music.start();
          
    }
   
    public static class MP3Music extends Thread {
    
        private File mp3;
        
        private Player player;
        
        public void touch(File mp3){
            this.mp3 = mp3;
        } 
    
        
    public void run(){
        try{
            FileInputStream fis = new FileInputStream(mp3);
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            this.player = new Player(bis);
            System.out.println("START MUSIC");
            
            this.player.play();
            System.out.println("FINAL MUSIC");
            
        }   catch (Exception e) {
            System.out.println("Error Music");
            e.printStackTrace();
            }
    }    
        
}
    

}
    
