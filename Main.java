import javax.swing.JFrame;

public class Main {

    public static void main(String[] args){
        JFrame mainframe = new JFrame();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);
        mainframe.getContentPane();
        mainframe.setTitle("Final Project");
        GamePanel panel = new GamePanel();
        mainframe.add(panel);
        mainframe.pack();
        mainframe.setLocationRelativeTo(null);
        mainframe.setVisible(true);

    }
}
