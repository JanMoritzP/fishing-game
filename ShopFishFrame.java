import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ShopFishFrame extends JFrame implements ActionListener {

    private Inventory playerInventory;
    private ShopFrame shopFrame;
    private ShopFishFrame shopFishFrame;
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();
    private JButton back = new JButton("Back");
    private JPanel panel = new JPanel();
    private JScrollPane scrollpane = new JScrollPane(panel);
    private JLabel moneyLabel = new JLabel();

    public ShopFishFrame(Inventory playerInventory, ShopFrame shopFrame) {
        this.playerInventory = playerInventory;
        this.shopFrame = shopFrame;

        setTitle("Sell-Fish-Menu");
        setSize(600, 300);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.setVisible(false);
            shopFrame.setVisible(true);
        }
        else {
            /*
            Use Button.Iterator() to iterator over buttons and crosscheck position!!!!
            */
            Iterator<JButton> buttonIterator = buttonList.iterator();
            int i = 0;
            while(buttonIterator.hasNext()) {
                if((buttonList.get(i).getBounds().x == ((JButton) e.getSource()).getBounds().x) && (buttonList.get(i).getBounds().y == ((JButton) e.getSource()).getBounds().y)){
                    panel.remove(buttonIterator.next());
                    buttonIterator.remove();
                    playerInventory.sellFish(i);
                }
                else {
                    i++;
                    buttonIterator.next();
                }
            }
            moneyLabel.setText(String.format("%s", playerInventory.getMoney()));
            panel.revalidate();
            panel.repaint();            
        }

    }

    public void initComponent() {
        ArrayList<Fish> fishList = playerInventory.getFish();
        Iterator<Fish> fishIterator = fishList.iterator();
        while (fishIterator.hasNext()) {
            buttonList.add(new JButton(fishIterator.next().getName()));
        }

        Iterator<JButton> buttonIterator = buttonList.iterator();
        int i = 0;
        while (buttonIterator.hasNext()) {
            panel.add(buttonList.get(i));
            buttonIterator.next().addActionListener(this);
            i++;
        }

        moneyLabel.setBounds(470, 70, 100, 50);
        add(moneyLabel);
        moneyLabel.setText(String.format("%s", playerInventory.getMoney()));

        back.setBounds(470, 10, 100, 50);
        add(back);
        back.addActionListener(this);

        scrollpane.setBounds(10, 10, 400, 250);
        
        panel.setLayout(new GridLayout(5, 2));
        scrollpane.setLayout(new ScrollPaneLayout());
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollpane);
    }

     
    
}
