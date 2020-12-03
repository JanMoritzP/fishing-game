import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.awt.*;
import java.awt.event.*;

public class ShopFrame extends JFrame implements ActionListener
{
    private JButton buyRods = new JButton("Buy Rods...");
    private JButton buyHooks = new JButton("Buy Hooks...");
    private JButton back = new JButton("back");
    private JButton buyBait = new JButton("Buy Bait...");
    private JButton sellFish = new JButton("Sell Fish...");
    private JButton sellAllFish = new JButton("Sell all Fish");


    private MainFrame mf;
    private Inventory playerInventory;


    public ShopFrame(MainFrame mf, Inventory playerInventory) {
        this.mf = mf;
        this.playerInventory = playerInventory;

        setTitle("Shop-Menu");
        setSize(600,300);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == back) {
            this.setVisible(false);
            mf.setVisible(true);
        }
        if(e.getSource() == buyRods) {
            System.out.println("buyRods");
        }
        if(e.getSource() == buyHooks) {
            System.out.println("buyHooks");
        }
        if(e.getSource() == buyBait) {
            playerInventory.addBait(new Bait("TestBait", 5));
            playerInventory.useMoney(10);
        }
        if(e.getSource() == sellFish) {
            ShopFishFrame shopFishFrame = new ShopFishFrame(playerInventory, this);
            this.setVisible(false);
            shopFishFrame.setVisible(true);
        }
        if(e.getSource() == sellAllFish) {
            playerInventory.addMoney(playerInventory.sellAllFish());
            //sellAllFish.setEnabled(false);
            //sellFish.setEnabled(false);
        }
        enableDisableComponents();
    }

    private void enableDisableComponents() {
        if(playerInventory.checkForFish() == false) {
            sellAllFish.setEnabled(false);
            sellFish.setEnabled(false);
        } 
    }

    private void initComponent() //all the bounds can change
    {
        buyRods.setBounds(50, 200, 100, 50);
        back.setBounds(150, 200, 100, 50);
        buyBait.setBounds(250, 200, 100, 50);
        sellFish.setBounds(100, 100, 100, 50);
        sellAllFish.setBounds(200, 100, 100, 50);
        buyHooks.setBounds(300, 100, 100, 50);

        add(buyRods);
        add(buyHooks);
        add(back);
        add(buyBait);
        add(sellFish);
        add(sellAllFish);

        buyHooks.addActionListener(this);
        buyRods.addActionListener(this);
        back.addActionListener(this);
        buyBait.addActionListener(this);
        sellFish.addActionListener(this);
        sellAllFish.addActionListener(this);

        enableDisableComponents();               
    }
}
