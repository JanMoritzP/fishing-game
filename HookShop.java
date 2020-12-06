import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.event.*;

public class HookShop extends JFrame implements ActionListener {
    
    private ShopFrame shopFrame;
    private Inventory playerInventory;
    private JButton back = new JButton("Back");

    private JButton hook1 = new JButton("Rusty Hook [10]");
    private JButton hook2 = new JButton("Less Rusty Hook [20]");
    private JButton hook3 = new JButton("Good Looking Hook [50]");
    private JButton hook4 = new JButton("Quite Fancy Hook [100]");
    private JButton hook5 = new JButton("Diamond Hook [500]");
        
    public HookShop(ShopFrame shopFrame, Inventory playerInventory) {
        this.shopFrame = shopFrame;
        this.playerInventory = playerInventory;

        setTitle("Hook-Menu");
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
            shopFrame.enableDisableComponents();
        }
        if(e.getSource() == hook1) {
            playerInventory.useMoney(10);
        }
        if(e.getSource() == hook2) {
            playerInventory.useMoney(20);            
        }
        if(e.getSource() == hook3) {
            playerInventory.useMoney(50);            
        }
        if(e.getSource() == hook4) {
            playerInventory.useMoney(100);            
        }
        if(e.getSource() == hook5) {
            playerInventory.useMoney(500);            
        }        
        enableDisableComponents();
    }


    public void initComponent() {

        back.setBounds(470, 10, 100, 50);
        add(back);
        back.addActionListener(this);


        hook1.setBounds(10, 10, 100, 50);
        hook2.setBounds(10, 70, 100, 50);
        hook3.setBounds(10, 130, 100, 50);
        hook4.setBounds(10, 190, 100, 50);
        hook5.setBounds(10, 250, 100, 50);

        add(hook1);
        add(hook2);
        add(hook3);
        add(hook4);
        add(hook5);
        
        hook1.addActionListener(this);
        hook2.addActionListener(this);
        hook3.addActionListener(this);
        hook4.addActionListener(this);
        hook5.addActionListener(this);

        enableDisableComponents();
    }

    private void enableDisableComponents() {
        hook1.setEnabled(false);
        hook2.setEnabled(false);
        hook3.setEnabled(false);
        hook4.setEnabled(false);
        hook5.setEnabled(false);

        if(playerInventory.getMoney() > 10) {
            hook1.setEnabled(true);
        }
        if(playerInventory.getMoney() > 20) {
            hook2.setEnabled(true);
        }
        if(playerInventory.getMoney() > 50) {
            hook3.setEnabled(true);
        }
        if(playerInventory.getMoney() > 100) {
            hook4.setEnabled(true);
        }
        if(playerInventory.getMoney() > 500) {
            hook5.setEnabled(true);
        }

        //Check for existing hooks!!!
    }
}
