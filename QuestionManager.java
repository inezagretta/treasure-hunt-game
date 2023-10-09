
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class QuestionManager {
    static Trivia1 quiz;
    GamePanel gp;
    Player gamer;
    static int lives = 5;
    public BufferedImage heart;
    public BufferedImage treasurechest;

    public static int tilenumber = 0;
    static boolean tile2checked,tile4checked,tile6checked,tile8checked,tile10checked,tile12checked,
            tile14checked,tile16checked,tile18checked,tile20checked,tile22checked,tile24checked = false;

    static int score = 0;

    // Initializing Question Manager class
    public QuestionManager(GamePanel gp, Player gamer) {
        this.gp = gp;
        this.gamer = gamer;
        quiz = new Trivia1();
        getImages();

    }
    //Gets heart and treasure images from the sprites class
    public void getImages(){
        try {
            heart = ImageIO.read(new File("src/sprites/heart.png"));
            treasurechest = ImageIO.read(new File("src/sprites/treasure.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Displays and updates the score,lives and assigns questions to the tiles as the game runs
    public void displayquestion(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.WHITE);
        g.setFont(new Font("ARIEL", Font.ITALIC, 30));
        g.drawString("SCORE: " + String.valueOf(score), 890, 30);
        g.setFont(new Font("ARIEL", Font.ITALIC, 20));
        g.drawString("LIVES: ", 1150, 30);
        int posx = 950;

        if(lives == 0){
            g.setColor(Color.BLACK);
            g.fillRect(200,300,600,400);
            g.setColor(Color.RED);
            g.setFont(new Font("ARIEL", Font.BOLD, 30));
            g.drawString("GAME OVER!!", 400, 350);
            g.drawString("SCORE : " + String.valueOf(score) , 400, 400);
            g.drawString("YOU LOST ALL YOUR LIVES :(", 300, 450);
            g.drawString("PRESS L TO GO BACK TO MAIN MENU", 220, 550);
        }
        for (int i = 0; i < lives; i++) {
            g2.drawImage(heart, posx, -190, 700, 500, null);
            posx +=30;
        }

        if(score == 1000){
            GamePanel.challlengecompleted = true;
            if( gamer.getX() < -240 && gamer.getY() < -224){
                g2.drawImage(treasurechest, 0, 0, 700, 500, null);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Apple Chancery", Font.BOLD, 30));
                g.drawString("WAY TO GO CHAMP!", 900, 450);
                g.drawString("PRESS L TO G0 TO MAIN MENU", 900, 550);
            }else {
                g.setColor(Color.BLACK);
                g.fillRect(200, 300, 600, 400);
                g.setColor(Color.GREEN);
                g.setFont(new Font("ARIEL", Font.BOLD, 30));
                g.drawString("CHALLENGE COMPLETED!! ", 300, 350);
                g.drawString("SCORE: " + String.valueOf(score), 310, 450);
                g.drawString("WALK THROUGH THE GOAL FOR ", 250, 550);
                g.drawString("YOUR TREASURE!!", 300, 650);
            }
        }else {
            double x = gamer.getX();
            double y = gamer.getY();
            if (x > -64.0 && x < 70.0 && y > -152.0 && y < -48.0) {
                tilenumber = 2;
                assignquestions(g);
            }
            if (x > -184.0 && x < -62.0 && y > -152.0 && y < -48.0) {
                tilenumber = 1;
            }
            if (x > 60.0 && x < 170.0 && y > -152.0 && y < -48.0) {
                tilenumber = 3;

            }
            if (x > 188.0 && x < 304.0 && y > -152.0 && y < -48.0) {
                tilenumber = 4;
                assignquestions(g);
            }
            if (x > 310.0 && x < 404.0 && y > -152.0 && y < -48.0) {
                tilenumber = 5;

            }
            if (x > -64.0 && x < 70.0 && y > -48.0 && y < 83.0) {
                tilenumber = 7;

            }
            if (x > -184.0 && x < -62.0 && y > -48.0 && y < 83.0) {
                tilenumber = 6;
                assignquestions(g);
            }
            if (x > 60.0 && x < 170.0 && y > -48.0 && y < 83.0) {
                tilenumber = 8;
                assignquestions(g);
            }
            if (x > 188.0 && x < 304.0 && y > -48.0 && y < 83.0) {
                tilenumber = 9;

            }
            if (x > 310.0 && x < 404.0 && y > -48.0 && y < 83.0) {
                tilenumber = 10;
                assignquestions(g);
            }
            if (x > -64.0 && x < 70.0 && y > 84.0 && y < 186.0) {
                tilenumber = 12;
                assignquestions(g);
            }
            if (x > -184.0 && x < -62.0 && y > 84.0 && y < 186.0) {
                tilenumber = 11;

            }
            if (x > 60.0 && x < 170.0 && y > 84.0 && y < 186.0) {
                tilenumber = 13;

            }
            if (x > 188.0 && x < 304.0 && y > 84.0 && y < 186.0) {
                tilenumber = 14;
                assignquestions(g);
            }
            if (x > 310.0 && x < 404.0 && y > 84.0 && y < 186.0) {
                tilenumber = 15;

            }
            if (x > -64.0 && x < 70.0 && y > 188.0 && y < 318.0) {
                tilenumber = 17;
            }
            if (x > -184.0 && x < -62.0 && y > 188.0 && y < 318.0) {
                tilenumber = 16;
                assignquestions(g);
            }
            if (x > 60.0 && x < 170.0 && y > 188.0 && y < 318.0) {
                tilenumber = 18;
                assignquestions(g);
            }
            if (x > 188.0 && x < 304.0 && y > 188.0 && y < 318.0) {
                tilenumber = 19;

            }
            if (x > 310.0 && x < 404.0 && y > 188.0 && y < 318.0) {
                tilenumber = 20;
                assignquestions(g);
            }

            if (x > -64.0 && x < 70.0 && y > 320.0 && y < 436.0) {
                tilenumber = 22;
                assignquestions(g);
            }
            if (x > -184.0 && x < -62.0 && y > 320.0 && y < 436.0) {
                tilenumber = 21;

            }
            if (x > 60.0 && x < 170.0 && y > 320.0 && y < 436.0) {
                tilenumber = 23;

            }
            if (x > 188.0 && x < 304.0 && y > 320.0 && y < 436.0) {
                tilenumber = 24;
                assignquestions(g);
            }
            if (x > 310.0 && x < 404.0 && y > 320.0 && y < 436.0) {
                tilenumber = 25;

            }
        }
    }
    //
    public void keypressed(KeyEvent e) {
        char pressed = e.getKeyChar();
        quiz.keyPressed(e);
        if(pressed == 'L'){
            resetGame();
        }
    }
    public void resetGame(){
        QuestionManager.tile2checked = false;
        Tiles.tile2 = new Color(153,102,0);;

        QuestionManager.tile4checked = false;
        Tiles.tile4 = new Color(153,102,0);;

        QuestionManager.tile6checked = false;
        Tiles.tile6 = new Color(153,102,0);;

        QuestionManager.tile8checked = false;
        Tiles.tile8 = new Color(153,102,0);;

        QuestionManager.tile10checked = false;
        Tiles.tile10 = new Color(153,102,0);;

        QuestionManager.tile12checked = false;
        Tiles.tile12 = new Color(153,102,0);;

        QuestionManager.tile14checked = false;
        Tiles.tile14 = new Color(153,102,0);;

        QuestionManager.tile16checked = false;
        Tiles.tile16 =new Color(153,102,0);


        QuestionManager.tile18checked =false;
        Tiles.tile18 = new Color(153,102,0);;

        QuestionManager.tile20checked = false;
        Tiles.tile20 = new Color(153,102,0);;

        QuestionManager.tile22checked = false;
        Tiles.tile22 = new Color(153,102,0);;

        QuestionManager.tile24checked = false;
        Tiles.tile24 = new Color(153,102,0);;


        Player.position =  new Pair(-100,400);
        lives = 5;
        score = 0;
        GamePanel.gamestate = 0;
    }

    public void conqueredtext(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(150, 250, 400, 100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Apple Chancery", Font.BOLD, 30));
        g.drawString("Conquered!!", 250, 300);
    }
    //Displays questions depending on the position of the player on the tiles
    public void assignquestions(Graphics g) {

        switch (tilenumber) {
            case 2:
                if(!tile2checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 4:
                if(!tile4checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 6:
                if(!tile6checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 8:
                if(!tile8checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 10:
                if(!tile10checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 12:
                if(!tile12checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 14:
                if(!tile14checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 16:
                if(!tile16checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 18:
                if(!tile18checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 20:
                if(!tile20checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 22:
                if(!tile22checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
            case 24:
                if(!tile24checked) {
                    quiz.displayTriviaPanel(g);
                }else{
                    conqueredtext(g);
                }
                break;
        }
    }

}
