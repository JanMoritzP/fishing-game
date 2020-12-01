import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ShopFishFrame extends JFrame implements ActionListener {

    private Inventory playerInventory;
    private ShopFrame shopFrame;
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();
    private JButton back = new JButton("Back");



    public ShopFishFrame(Inventory playerInventory, ShopFrame shopFrame) {
        this.playerInventory = playerInventory;
        this.shopFrame = shopFrame;

        setTitle("Sell-Fish-Menu");
        setSize(600,300);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            this.setVisible(false);
            shopFrame.setVisible(true);
        }

    }


    public void initComponent() {
        ArrayList<Fish> fishList = playerInventory.getFish();
        Iterator<Fish> fishIterator = fishList.iterator();
        while(fishIterator.hasNext()) {
            buttonList.add(new JButton(fishIterator.next().getName()));
        }
        
        Iterator<JButton> buttonIterator = buttonList.iterator();
        //JButton tempButton;
        int i = 0;
        while(buttonIterator.hasNext()) {
            //setBounds, add, addactionlistener
            buttonList.get(i).setBounds(10,10 + i*50,100,50);
            add(buttonList.get(i));
            buttonIterator.next().addActionListener(this);

            i++;
        }

        back.setBounds(110, 10, 100, 50);
        add(back);
        back.addActionListener(this);


    }

     
    
}
