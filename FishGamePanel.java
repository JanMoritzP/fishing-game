import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.*;

public class FishGamePanel extends JPanel{
    public FishGamePanel() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(50,50,850,850);
        drawFish(g);
    }    

    private void drawFish(Graphics g) {
        
    }
}
