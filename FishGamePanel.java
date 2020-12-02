import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FishGamePanel extends JPanel {

    private ArrayList<Fish> liveFish;
    private ArrayList<int[]> fishPosition;
    private Mutable<Boolean> gameRuns;
    private Inventory playerInventory;

    public FishGamePanel(ArrayList<Fish> liveFish, ArrayList<int[]> fishPosition, Mutable<Boolean> gameRuns,
            Inventory playerInventory) {
        this.liveFish = liveFish;
        this.fishPosition = fishPosition;
        this.gameRuns = gameRuns;
        this.playerInventory = playerInventory;

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Iterator<Fish> fishIterator = liveFish.iterator();
                int i = 0;
                while(fishIterator.hasNext()) {
                    if((e.getX() >= fishPosition.get(i)[0] && e.getX() <= fishPosition.get(i)[0] + 10) && (e.getY() >= fishPosition.get(i)[1] && e.getY() <= fishPosition.get(i)[1] + 10)) {
                        playerInventory.addFish(fishIterator.next());
                        fishIterator.remove();
                        fishPosition.remove(i);
                        i = 0;
                        fishIterator = liveFish.iterator();
                    }
                    else {
                        fishIterator.next();
                        i++;
                    }
                }
            }
        });
        Thread draw = new Thread() {
            @Override
            public void run() {
                while(gameRuns.getVariable()) {
                    repaint();
                    try{sleep(16);}catch(InterruptedException e){e.printStackTrace();}
                }
            }
        };
        draw.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(50,50,850,850);

        Iterator<Fish> fishIterator = liveFish.iterator();
        int i = 0;
        int xPos, yPos;
        String dump;
        while(fishIterator.hasNext()) {
            xPos = fishPosition.get(i)[0];
            yPos = fishPosition.get(i)[1];
            g.fillRect(xPos, yPos, 10, 10);
            dump = fishIterator.next().getName();
            i++;
        }
    }    
}
