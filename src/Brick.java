import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Brick{

    public boolean brickBody[][];
    public int brickWidth = 52;
    public int brickHeight = 30;
    public int totalBricks;

    public Brick(int row, int col) {
        brickBody = new boolean[row][col];
        totalBricks = row * col;
        for(int i = 0; i < brickBody.length; i++){
            for(int j = 0; j < brickBody[0].length; j++){
                brickBody[i][j] = true;   //The bricks have not been hit by ball, otherwise false if they have been hit.
            }
        }
    }


    public void draw(Graphics g) {
        Random rand = new Random();
        Graphics2D g2D = (Graphics2D) g;
        for(int i = 0; i < brickBody.length; i++) {
            for (int j = 0; j < brickBody[0].length; j++) {
                if (brickBody[i][j]) {
                    g2D.setColor(Color.gray);
                    g2D.fillRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                    g2D.setStroke(new BasicStroke(3));
                    g2D.setColor(Color.black);
                    g2D.drawRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickVisible(boolean visibility, int row, int col) {
        brickBody[row][col] = visibility;
    }

    public int getCol() {
        return brickBody[0].length;
    }

    public int getRow() {
        return brickBody.length;
    }


}
