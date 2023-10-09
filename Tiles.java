import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {
    public BufferedImage image;
    Tiles [] tiles;

    static Color tile1 = new Color(51,0,0);
    static Color tile3 = new Color(51,0,0);
    static Color tile5 = new Color(51,0,0);
    static Color  tile7 = new Color(51,0,0);
    static Color  tile9 = new Color(51,0,0);
    static Color   tile11 = new Color(51,0,0);
    static Color   tile13 = new Color(51,0,0);
    static Color  tile15 = new Color(51,0,0);
    static Color tile17 =new Color(51,0,0);
    static Color   tile19 = new Color(51,0,0);
    static Color   tile21 = new Color(51,0,0);
    static Color  tile23 = new Color(51,0,0);
    static Color  tile25 = new Color(51,0,0);
    static Color tile2 = new Color(153,102,0);
    static Color    tile4 = new Color(153,102,0);
    static Color  tile6 = new Color(153,102,0);
    static Color   tile8 = new Color(153,102,0);
    static Color   tile10 = new Color(153,102,0);
    static Color  tile12 = new Color(153,102,0);
    static Color  tile14 = new Color(153,102,0);
    static Color  tile16 = new Color(153,102,0);
    static Color tile18 = new Color(153,102,0);
    static Color tile20 = new Color(153,102,0);
    static Color  tile22 = new Color(153,102,0);
    static Color    tile24 = new Color(153,102,0);
    // Initializing tile class
    public Tiles() {
        tiles = new Tiles[10];
    }

    public void getTileImage(){

        try{
            tiles[0] = new Tiles();
            tiles[0].image = ImageIO.read(new File("src/sprites/plaingrass.png"));

            tiles[1] = new Tiles();
            tiles[1].image = ImageIO.read(new File("src/sprites/appletree.png"));

            tiles[2] = new Tiles();
            tiles[2].image = ImageIO.read(new File("src/sprites/goal.png"));

            tiles[3] = new Tiles();
            tiles[3].image = ImageIO.read(new File("src/sprites/brick.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){

        this.getTileImage();
        for(int i = -460 ; i < 1000; i+=44) {
            for (int j = -468; j < 1000; j += 106) {
                g.drawImage(tiles[0].image, j, i, 1024, 768, null);
            }
        }
        for (int j = -468; j < 80; j += 106) {
            g.drawImage(tiles[1].image, j, -180, 768, 800, null);
        }
        for (int j = -180; j < 1000; j += 60) {
            g.drawImage(tiles[1].image, 140, j, 768, 800, null);
        }
        for (int j = -600; j < 80; j += 106) {
            g.drawImage(tiles[1].image, j, 520, 768, 800, null);
        }
        for (int j = -80; j < 550; j += 60) {
            g.drawImage(tiles[1].image, -600, j, 768, 800, null);
        }
        // Goal
        g.drawImage(tiles[2].image, -300, -140, 800, 800, null);
        //Tileboard
        drawtile(g);

    }
    // Drawing the tileboard
    public void drawtile(Graphics g){
        g.setColor(tile1);
        g.fillRect(160,122,120,120);
        g.setColor(tile2);
        g.fillRect(280,122,120,120);
        g.setColor(tile3);
        g.fillRect(400,122,120,120);
        g.setColor(tile4);
        g.fillRect(520,122,120,120);
        g.setColor(tile5);
        g.fillRect(640,122,120,120);
        g.setColor(tile6);
        g.fillRect(160,242,120,120);
        g.setColor(tile7);
        g.fillRect(280,242,120,120);
        g.setColor(tile8);
        g.fillRect(400,242,120,120);
        g.setColor(tile9);
        g.fillRect(520,242,120,120);
        g.setColor(tile10);
        g.fillRect(640,242,120,120);
        g.setColor(tile11);
        g.fillRect(160,362,120,120);
        g.setColor(tile12);
        g.fillRect(280,362,120,120);
        g.setColor(tile13);
        g.fillRect(400,362,120,120);
        g.setColor(tile14);
        g.fillRect(520,362,120,120);
        g.setColor(tile15);
        g.fillRect(640,362,120,120);
        g.setColor(tile16);
        g.fillRect(160,482,120,120);
        g.setColor(tile17);
        g.fillRect(280,482,120,120);
        g.setColor(tile18);
        g.fillRect(400,482,120,120);
        g.setColor(tile19);
        g.fillRect(520,482,120,120);
        g.setColor(tile20);
        g.fillRect(640,482,120,120);
        g.setColor(tile21);
        g.fillRect(160,602,120,120);
        g.setColor(tile22);
        g.fillRect(280,602,120,120);
        g.setColor(tile23);
        g.fillRect(400,602,120,120);
        g.setColor(tile24);
        g.fillRect(520,602,120,120);
        g.setColor(tile25);
        g.fillRect(640,602,120,120);


    }
}
