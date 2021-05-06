import javax.swing.*;
import java.awt.*;

public class Main{

    private static final int WIDTH = 700, HEIGHT = 600;
    private static final String TITLE = "Ball Breaker";

    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        GamePanel panel = new GamePanel();
        frame.add(panel);

        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Brick brick = new Brick();
        frame.add(brick);
        brick.drawBrick();
    }
}
