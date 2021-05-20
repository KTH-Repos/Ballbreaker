import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import javax.swing.*;

/**
 * class that contains the main game functions including drawing the slider, ball and checking for collisions.
 */

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private static final int WIDTH = 700, HEIGHT = 600; //Game area

    private boolean running = true;
    private int score = 0;
    private int chances = 3;

    private int ballx = 200;
    private int bally = 300;
    private int ballheight = 20;
    private int ballwidth = 20;

    private int ballvelx = 2;
    private int ballvely = 2;

    private int sliderwidth = 100;
    private int sliderheight = 10;
    private int sliderx = 300;
    private int slidery = 550;
    private int slidervelx;

    //Keylistener  for listening to key
    //ActionListener for moving the ball

    Brick brick = new Brick(7, 10);

    Timer timer = new Timer(5, this); //Actionlistener listens for timer, when timer ticks, calls actionPerformed()

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

        if(bally > 570) {     //if you lose the ball
            timer.stop();
            running = false;
            chances--;
            if(chances == 0){    //if you lose the game and have 0 chances
                popUpGameOverWindow();
            }
            else{                //if you still have chances left.
                restartGame(g);
            }
        }

        if(brick.totalBricks == 0) {
            timer.stop();
            running = false;
            popUpGameWinWindow();
        }

        //Score Board
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Your score: " + score, 550, 30);

        //Bricks
        brick.draw(g);
    }


    //Också införa en check om bollen missar slidern och går utanför
    //Checks för att kolla om bricksen (rektanglarna) kolliderar med bollen. kolla intersect() metoden i swing
    public void actionPerformed(ActionEvent e) {
            bally += ballvely;
            ballx += ballvelx;
            sliderx += slidervelx;

            //Check if the two engulfers collide with each other
            Rectangle ballEngulf = new Rectangle(ballx, bally, ballwidth, ballheight);   //encloses the ball
            Rectangle sliderEngulf = new Rectangle(sliderx, slidery, sliderwidth, sliderheight);   //encloses the slider
            //Rectangle brickEngulf = new Rectangle()

            if (ballEngulf.intersects(sliderEngulf)) {
                if (ballx + ballwidth - 2 <= sliderx || ballx + 2 >= sliderx + sliderwidth) {
                    ballvelx = -ballvelx;
                } else {
                    ballvely = -ballvely;
                }
            }

            //Collision detection of ball and bricks
            checkBrickCollision();

            //Checks to keep the ball inside the game border
            if (bally > HEIGHT - ballheight || bally < 0) { //Varför går den utanför i bottenläget?
                ballvely = -ballvely;
            }
            if (ballx > WIDTH - ballwidth || ballx < 0) {
                ballvelx = -ballvelx;
            }

            //check to keep the slider inside the game border, if it is move it back inside
            if (sliderx < 0) {
                slidervelx = 0;
                sliderx = 0;
            }
            if (sliderx > WIDTH - sliderwidth) {
                slidervelx = 0;
                sliderx = WIDTH - sliderwidth;
            }
            repaint();
    }

    public void right() {
        slidervelx = 2;
    }

    public void left() {
        slidervelx = -2;
    }


    public void checkBrickCollision() {
        Rectangle ballEngulf = new Rectangle(ballx, bally, ballwidth, ballheight);
        for(int i = 0; i < brick.brickBody.length; i++){
            for(int j = 0; j < brick.brickBody[0].length; j++){
                if(brick.brickBody[i][j]){
                    Rectangle brickEngulf = new Rectangle(j*brick.brickWidth + 80,i*brick.brickHeight+40,brick.brickWidth,brick.brickHeight);
                    if(ballEngulf.intersects(brickEngulf)){
                        score += 1;
                        brick.setBrickVisible(false,i,j);
                        brick.totalBricks--;
                        if (ballx + ballwidth -2 <= j*brick.brickWidth + 80 || ballx + 2 >= j*brick.brickWidth + 80 + brick.brickWidth) {
                            ballvelx = -ballvelx;
                        }
                        else {
                            ballvely = - ballvely;
                        }
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_RIGHT) {
            right();
        }
        if (code == KeyEvent.VK_LEFT) {
            left();
        }
        if(code == KeyEvent.VK_ESCAPE) {  //to pause the game
            if (running == true) {
                running = false;
                timer.stop();
            }
        }
        if(code == KeyEvent.VK_SPACE) {    //to start the game after pause
            if (running == false) {
                running = true;
                timer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {
        slidervelx = 0;
    }

    public void popUpGameOverWindow() {
        int window = JOptionPane.showConfirmDialog(null, "Game over. Your score is " + score + ". Press Yes to Restart" + "\nPress No to exit", "", JOptionPane.YES_NO_OPTION);
        if(window == JOptionPane.YES_OPTION) {
            running = true;
            bally = 530;
            ballx = 300;
            sliderx = 300;
            score = 0;
            chances = 3;
            brick.totalBricks = brick.getRow() * brick.getCol();
            brick = new Brick(brick.getRow(), brick.getCol());
            repaint();
            timer.start();
        }
        else{
            System.exit(0);
        }
    }

    public void popUpGameWinWindow() {
        int window = JOptionPane.showConfirmDialog(null, "Congrats!!! You won the game Press Yes to Restart" + "\nPress No to exit", "", JOptionPane.YES_NO_OPTION);
        if(window == JOptionPane.YES_OPTION) {
            running = true;
            bally = 530;
            ballx = 300;
            sliderx = 300;
            score = 0;
            chances = 3;
            brick.totalBricks = brick.getRow() * brick.getCol();
            brick = new Brick(brick.getRow(), brick.getCol());
            repaint();
            timer.start();
        }
         else {
            System.exit(0);
        }
    }

    public void restartGame(Graphics g) {
        int window = JOptionPane.showConfirmDialog(null, "You got " + chances + " chances left", "", JOptionPane.PLAIN_MESSAGE);
        if (window == JOptionPane.OK_OPTION) {
            for (int i = 0; i < brick.getRow(); i++) {
                for (int j = 0; j < brick.getCol(); j++) {
                    if (brick.brickBody[i][j]) {
                        brick.draw(g);
                    }
                }
            }
            running = true;
            bally = 530;
            ballx = 300;
            sliderx = 300;
            timer.start();
        }
    }
}
