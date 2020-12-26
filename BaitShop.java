import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BaitShop extends JFrame implements ActionListener {
    
    private ShopFrame shopFrame;
    private Inventory playerInventory;
    private JButton back = new JButton("Back");

    private JPanel panel = new JPanel();
    private JScrollPane scrollpane = new JScrollPane(panel);

    private ArrayList<JButton> buttonList = new ArrayList<JButton>();
        
    public BaitShop(ShopFrame shopFrame, Inventory playerInventory) {
        this.shopFrame = shopFrame;
        this.playerInventory = playerInventory;

        setTitle("Bait-Shop");
        setSize(600,400);
        setLocation(new Point(600, 400));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            this.setVisible(false);
            shopFrame.setVisible(true);
            shopFrame.enableDisableComponents();
        }      
        enableDisableComponents();
    }


    public void initComponent() {

        back.setBounds(470, 10, 100, 50);
        add(back);
        back.addActionListener(this);

        enableDisableComponents();
    }

    private void enableDisableComponents() {
    
    }
}
