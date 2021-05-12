import javax.swing.*;
import java.awt.*;

public class Brick{

    public boolean brickBody[][];
    public int brickWidth = 52;
    public int brickHeight = 30;

    public Brick(int row, int col) {
        brickBody = new boolean[row][col];
        for(int i = 0; i < brickBody.length; i++){
            for(int j = 0; j < brickBody[0].length; j++){
                brickBody[i][j] = true;   //The bricks have not been hit by ball, otherwise false if they have been hit.
            }
        }
    }


    public void draw(Graphics g) {
        for(int i = 0; i < brickBody.length; i++) {
            for (int j = 0; j < brickBody[0].length; j++) {
                if (brickBody[i][j]) {
                    g.setColor(Color.gray);
                    g.fillRect(j * brickWidth + 90, i * brickHeight + 40, brickWidth, brickHeight);
                }
            }
        }
    }


}
