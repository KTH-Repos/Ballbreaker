import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

/**
 * class that contains the main game functions including drawing the slider, ball and checking for collisions.
 */

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private static final int WIDTH = 700, HEIGHT = 600; //Game area

    private boolean running = false;
    private int score = 0;

    private int ballx = 200;
    private int bally = 150;
    private int ballheight = 20;
    private int ballwidth = 20;

    private int ballvelx = 1;
    private int ballvely = 1;

    private int sliderwidth = 100;
    private int sliderheight = 10;
    private int sliderx = 300;
    private int slidery = 550;
    private int sliderxvel = 20; //speed of slider in x-direction
    private int slidervelx = 0;

    //Keylistener  for listening to key
    //ActionListener for moving the ball

    Timer timer = new Timer(5, this); //Actionlistener listens for timer, when timer ticks, calls actionPerformed()
    int velx = 0;

    public GamePanel() {
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }

    public void paintComponent(Graphics g) {
        //Slider
        g.setColor(Color.GRAY);
        g.fillRect(sliderx, slidery, sliderwidth, sliderheight);

        //Ball
        g.setColor(Color.GREEN);
        g.fillOval(ballx, bally, ballwidth, ballheight);
    }


    //Också införa en check om bollen missar slidern och går utanför
    //Checks för att kolla om bricksen (rektanglarna) kolliderar med bollen. kolla intersect() metoden i swing
    public void actionPerformed(ActionEvent e ) {
        bally += ballvely;
        ballx += ballvelx;
        Rectangle ballEngulf = new Rectangle(ballx, bally, 20, 20);   //encloses the ball
        Rectangle sliderEngulf = new Rectangle(sliderx, slidery, sliderwidth, sliderheight);   //encloses the slider

        //Check if the two engulfers collide with each other
        if(ballEngulf.intersects(sliderEngulf)) {
            ballvely = -ballvely;
        }
        if (bally > HEIGHT - ballheight || bally < 0) { //Varför går den utanför i bottenläget?
            ballvely = -ballvely;
        }
        if (ballx > WIDTH - ballwidth || ballx < 0) {
            ballvelx = -ballvelx;
        }

        //checkcollisions();
        repaint();
    }

    public void right() {
        sliderx += sliderxvel;
    }

    public void left() {
        sliderx += -sliderxvel;
    }

    public void tick() {
        //x +=
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(code);
        if (code == KeyEvent.VK_RIGHT) {
            if (sliderx < WIDTH-sliderwidth)
            right();
        }
        if (code == KeyEvent.VK_LEFT) {
            if (sliderx > 0)
            left();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {
        velx = 0;
    }
}
