import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Brick{

    public boolean brickBody[][];
    public int brickWidth = 52;
    public int brickHeight = 30;
    public int totalBricks;

    private int row;
    private int col;

    public Brick(int row, int col) {
        this.row = row;
        this.col = col;
        brickBody = new boolean[row][col];
        totalBricks = row * col;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                brickBody[i][j] = true;   //The bricks have not been hit by ball, otherwise false if they have been hit.
            }
        }
    }

    public void draw(Graphics g) {
        Random rand = new Random();
        Graphics2D g2D = (Graphics2D) g;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (brickBody[i][j] && i == 0) {
                    g2D.setColor(Color.gray);
                    g2D.fillRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                    g2D.setStroke(new BasicStroke(3));
                    g2D.setColor(Color.black);
                    g2D.drawRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                }
                if(brickBody[i][j] && i == 1){
                    g2D.setColor(Color.red);
                    g2D.fillRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                    g2D.setStroke(new BasicStroke(3));
                    g2D.setColor(Color.black);
                    g2D.drawRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                }
                if(brickBody[i][j] && i == 2){
                    g2D.setColor(Color.green);
                    g2D.fillRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                    g2D.setStroke(new BasicStroke(3));
                    g2D.setColor(Color.black);
                    g2D.drawRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                }
                if(brickBody[i][j] && i == 3){
                    g2D.setColor(Color.yellow);
                    g2D.fillRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                    g2D.setStroke(new BasicStroke(3));
                    g2D.setColor(Color.black);
                    g2D.drawRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                }
                if(brickBody[i][j] && i == 4){
                    g2D.setColor(Color.orange);
                    g2D.fillRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                    g2D.setStroke(new BasicStroke(3));
                    g2D.setColor(Color.black);
                    g2D.drawRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                }
                if(brickBody[i][j] && i == 5){
                    g2D.setColor(Color.blue);
                    g2D.fillRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                    g2D.setStroke(new BasicStroke(3));
                    g2D.setColor(Color.black);
                    g2D.drawRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                }
                if(brickBody[i][j] && i == 6){
                    g2D.setColor(Color.cyan);
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

    public void colorBricks(Graphics g, Color brickColor){

    }

    public int getCol() {
        return brickBody[0].length;
    }

    public int getRow() {
        return brickBody.length;
    }


}
