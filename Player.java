import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    Menu menu;
    static Pair position;
    public int speed;
    public boolean uppressed, downpressed, leftpressed, rightpressed,mousepressed;
    public BufferedImage downA, downB, upA, upB, rightA, rightB, leftA, leftB;
    public String Direction;
    public int spritetracker = 0;

    public int id = 1;
    Tiles tileset;
    QuestionManager quizes;
    GamePanel gp;
    // Initializing the Player class
    public Player(GamePanel gp) {
        this.gp = gp;
        position = new Pair(-100,400);
        speed = 4;
        getPlayerImage();
        Direction = "down";
        menu = new Menu();
        tileset = new Tiles();
        quizes = new QuestionManager(gp,this);
    }

    public void getPlayerImage() {
        try {
            downA = ImageIO.read(new File("src/sprites/downA.png"));
            downB = ImageIO.read(new File("src/sprites/down.png"));
            upA = ImageIO.read(new File("src/sprites/upA.png"));
            upB = ImageIO.read(new File("src/sprites/upB.png"));
            rightA = ImageIO.read(new File("src/sprites/rightA.png"));
            rightB = ImageIO.read(new File("src/sprites/rightB.png"));
            leftA = ImageIO.read(new File("src/sprites/leftA.png"));
            leftB = ImageIO.read(new File("src/sprites/leftB.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void keypressed(KeyEvent e) {
        quizes.keypressed(e);
        int pressed = e.getKeyCode();
        if (pressed == KeyEvent.VK_UP) {
            Direction = "up";
            uppressed = true;
        }
        if (pressed == KeyEvent.VK_DOWN) {
            Direction = "down";
            downpressed = true;

        }
        if (pressed == KeyEvent.VK_RIGHT) {
            Direction = "right";
            rightpressed = true;

        }
        if (pressed == KeyEvent.VK_LEFT) {
            Direction = "left";
            leftpressed = true;
        }
    }

    public void keyreleased(KeyEvent e) {

        int pressed = e.getKeyCode();
        if (pressed == KeyEvent.VK_UP) {
            uppressed = false;
        }
        if (pressed == KeyEvent.VK_DOWN) {
            downpressed = false;
        }
        if (pressed == KeyEvent.VK_RIGHT) {
            rightpressed = false;
        }
        if (pressed == KeyEvent.VK_LEFT) {
            leftpressed = false;

        }

    }

    //Updates the position of the player accordingly
    public void update() {

        if (uppressed == true) {
            position.y -= speed;

        }
        if (downpressed == true) {
            position.y += speed;

        }
        if (leftpressed == true) {
            position.x -= speed;

        }
        if (rightpressed == true) {
            position.x += speed;

        }

        spritetracker++;
        if (spritetracker > 20) {
            if (id == 1) {
                id = 2;
            } else if (id == 2) {
                id = 1;
            }
            spritetracker = 0;
        }

    }

    public double getX(){
        return position.x;
    }
    public double getY(){
        return position.y;
    }
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        BufferedImage image = null;
        switch (Direction) {
            case "up":
                if (id == 1) {
                    image = upA;
                }
                if (id == 2) {
                    image = upB;
                }
                break;
            case "down":
                if (id == 1) {
                    image = downA;
                }
                if (id == 2) {
                    image = downB;
                }
                break;
            case "left":
                if (id == 1) {
                    image = leftA;
                }
                if (id == 2) {
                    image = leftB;
                }
                break;
            case "right":
                if (id == 1) {
                    image = rightA;
                }
                if (id == 2) {
                    image = rightB;
                }
                break;
        }

        tileset.draw(g);
        quizes.displayquestion(g);
        g2.drawImage(image, (int) position.x, (int) position.y, 700, 500, null);


    }
}