import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class HookShop extends JFrame implements ActionListener {
    
    private ShopFrame shopFrame;
    private Inventory playerInventory;
    private JButton back = new JButton("Back");

    private JButton hookButton1 = new JButton("Rusty Hook [10]");
    private JButton hookButton2 = new JButton("Less Rusty Hook [20]");
    private JButton hookButton3 = new JButton("Good Looking Hook [50]");
    private JButton hookButton4 = new JButton("Quite Fancy Hook [100]");
    private JButton hookButton5 = new JButton("Diamond Hook [500]");

    private Hook hook1 = new Hook("Rusty Hook", 2);
    private Hook hook2 = new Hook("Less Rusty Hook", 4);
    private Hook hook3 = new Hook("Good Looking Hook", 5);
    private Hook hook4 = new Hook("Quite Fancy Hook", 7);
    private Hook hook5 = new Hook("Diamond Hook", 10);    
        
    public HookShop(ShopFrame shopFrame, Inventory playerInventory) {
        this.shopFrame = shopFrame;
        this.playerInventory = playerInventory;

        setTitle("Hook-Shop");
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
        
        ArrayList<Hook> playerHooks = playerInventory.getHookList();
        Iterator<Hook> hookIterator = playerHooks.iterator();
        Hook tempHook;
        while(hookIterator.hasNext()) {
            tempHook = hookIterator.next();
            if(tempHook.getName() == "Rusty Hook") {
                hookButton1.setEnabled(false);
            }
            if(tempHook.getName() == "Less Rusty Hook") {
                hookButton2.setEnabled(false);
            }
            if(tempHook.getName() == "Good Looking Hook") {
                hookButton3.setEnabled(false);
            }
            if(tempHook.getName() == "Quite Fancy Hook") {
                hookButton4.setEnabled(false);
            }
            if(tempHook.getName() == "Diamond Hook") {
                hookButton5.setEnabled(false);
            }
        }
    }
}
