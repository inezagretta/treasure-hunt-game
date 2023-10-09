import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class GamePanel extends JPanel implements Runnable {

    public static final int screenwidth = 1400;
    public static final int screenheight = 800;
    public static final int FPS = 60;
    public static int gamestate = 0;
    static boolean challlengecompleted = false;
    Thread gameThread;
    ActionListener KL = new ActionListener();
    MouseListener ML = new MouseListenerClass();

    public static Player character;

    Menu menu;
    // Initializing Game Panel Class
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        character = new Player(this);
        menu = new Menu();
        this.addKeyListener(KL);
        this.addMouseListener(ML);
        this.setFocusable(true);
        startgamethread();

    }

    public void startgamethread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(true){
            update();
            repaint();
            try{
                Thread.sleep(1000/FPS);
            }
            catch(InterruptedException e){}
        }


    }
    // Updating position of the player on the screen
    public void update() {
        if (gamestate == 1) {
            character.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gamestate == 1) {
            character.draw(g);
        }
        else if(gamestate == 0){
            menu.drawmenu(g);
        }else if(gamestate == 4){
            System.exit(1);
        }
        else{
            menu.drawhelpscreen(g);
        }

    }


    // Implements keyboard actions of all the classes
    class ActionListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            character.keypressed(e);
            char pressed = e.getKeyChar();
            if(pressed == 'M'){
                gamestate = 0;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            character.keyreleased(e);
        }
    }
    // Implements Mouse recognition
    class MouseListenerClass implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            //Start Game
            if(e.getY() >= 180 && e.getY() <= 280) {
                gamestate = 1;
                //Instructions
            } else if(e.getY() >= 300 && e.getY() <= 400){
                gamestate = 3;
                // Exit
            }else if(e.getY() >= 420 && e.getY() <= 520){
                gamestate = 4;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    //Handles the menuscreen logistics log
    public class Menu {

        public Menu(){

        }
        //Displays the main menu
        public void drawmenu(Graphics g){
            Graphics2D g2 =  (Graphics2D)g;
            g2.setFont(new Font("Apple Chancery",Font.BOLD,50));
            g2.setColor(Color.RED);
            g2.drawString("MENU",500,180);
            g2.setColor(Color.black);
            g2.drawRect(470,230,300,50);
            g2.setFont(new Font("Apple Chancery",Font.BOLD,30));
            g2.drawString("START GAME",500,260);
            g2.drawRect(470,350,300,50);
            g2.drawString("INSTRUCTIONS",500,380);
            g2.drawRect(470,470,300,50);
            g2.drawString("EXIT",550,500);


        }
        //Displays the instructions screen
        public void drawhelpscreen(Graphics g){
            Graphics2D g2 =  (Graphics2D)g;
            g2.setFont(new Font("Bradley Hand",Font.BOLD,30));
            g2.setColor(Color.BLACK);
            g2.drawString("* Ensure caps lock is on",100,100);
            g2.drawString("* This is a treasure hunt/quiz game with a twist",100,140);
            g2.drawString("* You have to complete the required tasks on the tiles(questions) to get the treasure",100,180);
            g2.drawString("* The challenge is to get to 1000 points before running out of lives",100,220);
            g2.drawString("* Walk over the tile board to locate tiles that have questions",100,260);
            g2.drawString("* The tile color changes everytime you answer a question :",100,300);
            g2.setColor(Color.GREEN);
            g2.drawString("Green for right;",870,300);
            g2.setColor(Color.RED);
            g2.drawString("Red for wrong;",1100,300);
            g2.setColor(Color.BLACK);
            g2.drawString("* Sometimes it takes a little while to respond, please wait :)",100,340);
            g2.drawString("* You lose a life for every question you get wrong.Lives can be seen on the top right corner of screen",100,380);
            g2.drawString("* You can reattempt red tiles but you don't get the lost life back",100,420);
            g2.drawString("* If all lives are lost before completing the challenge, you lose ",100,460);
            g2.setColor(Color.RED);
            g2.drawString("* Use Arrow Keys to Control Player's movement ",100,500);
            g2.drawString("* Press A,B,C,D,E,F,G,H or I to select categories and 1,2,3 or 4 to choose responses to questions",100,540);
            g2.drawString("* Press L to restart game after winning or losing",100,580);
            g2.setFont(new Font("Bradley Hand",Font.BOLD,40));
            g2.setColor(Color.BLACK);

            g2.drawString("Press M to go back to Main menu screen",100,640);
        }

    }


}


