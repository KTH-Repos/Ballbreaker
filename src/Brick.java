import javax.swing.*;
import java.awt.*;

public class Brick extends JPanel{

    //TODO: build bricks
    public void drawBrick() {
        repaint();      //Bricks visas inte i panelen?
    }

    public void paintBricks(Graphics2D bricks) {
        super.paintComponent(bricks);

        bricks.setColor(Color.green);
        bricks.fillRect(100,200,20,30);
    }
}
