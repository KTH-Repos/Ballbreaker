import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{

    private static final int WIDTH = 700, HEIGHT = 600;
    private static final String TITLE = "Ball Breaker";
    private static final String WELCOME = "Welcome to BallBreaker";
    public static void main(String[] args) {
        JFrame welcome = new JFrame(WELCOME);
        JButton button = new JButton("Begin");
        button.setText("Click anywhere on the screen to begin");
        button.setPreferredSize(new Dimension(40,40));
        welcome.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        welcome.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome.dispose();
                JFrame frame = new JFrame(TITLE);
                GamePanel panel = new GamePanel();
                frame.add(panel);

                frame.setSize(WIDTH,HEIGHT);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        welcome.setSize(WIDTH,HEIGHT);
        welcome.setVisible(true);
        welcome.setResizable(false);
        welcome.setLocationRelativeTo(null);
        welcome.setVisible(true);

    }
}
