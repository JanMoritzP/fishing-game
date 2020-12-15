import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import java.awt.event.*;

public class BaitShop extends JFrame implements ActionListener {
    
    private ShopFrame shopFrame;
    private Inventory playerInventory;
    private JButton back = new JButton("Back");

    private JPanel panel = new JPanel();
    private JScrollPane scrollpane = new JScrollPane(panel);
        
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
        if(e.getSource() == hookButton1) {
            playerInventory.useMoney(10);
            playerInventory.addHook(hook1);
        }
        if(e.getSource() == hookButton2) {
            playerInventory.useMoney(20);
            playerInventory.addHook(hook2);            
        }
        if(e.getSource() == hookButton3) {
            playerInventory.useMoney(50);
            playerInventory.addHook(hook3);            
        }
        if(e.getSource() == hookButton4) {
            playerInventory.useMoney(100);
            playerInventory.addHook(hook4);            
        }
        if(e.getSource() == hookButton5) {
            playerInventory.useMoney(500);
            playerInventory.addHook(hook5);            
        }        
        enableDisableComponents();
    }


    public void initComponent() {

        back.setBounds(470, 10, 100, 50);
        add(back);
        back.addActionListener(this);


        hookButton1.setBounds(10, 10, 100, 50);
        hookButton2.setBounds(10, 70, 100, 50);
        hookButton3.setBounds(10, 130, 100, 50);
        hookButton4.setBounds(10, 190, 100, 50);
        hookButton5.setBounds(10, 250, 100, 50);

        add(hookButton1);
        add(hookButton2);
        add(hookButton3);
        add(hookButton4);
        add(hookButton5);
        
        hookButton1.addActionListener(this);
        hookButton2.addActionListener(this);
        hookButton3.addActionListener(this);
        hookButton4.addActionListener(this);
        hookButton5.addActionListener(this);

        enableDisableComponents();
    }

    private void enableDisableComponents() {
        hookButton1.setEnabled(false);
        hookButton2.setEnabled(false);
        hookButton3.setEnabled(false);
        hookButton4.setEnabled(false);
        hookButton5.setEnabled(false);

        if(playerInventory.getMoney() > 10) {
            hookButton1.setEnabled(true);
        }
        if(playerInventory.getMoney() > 20) {
            hookButton2.setEnabled(true);
        }
        if(playerInventory.getMoney() > 50) {
            hookButton3.setEnabled(true);
        }
        if(playerInventory.getMoney() > 100) {
            hookButton4.setEnabled(true);
        }
        if(playerInventory.getMoney() > 500) {
            hookButton5.setEnabled(true);
        }

        //Check for existing hooks!!!
    }
}
