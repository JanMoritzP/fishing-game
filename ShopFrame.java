import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShopFrame extends JFrame implements ActionListener
{
    private JButton test = new JButton("test");
    private JButton back = new JButton("back");
    private JButton buyBait = new JButton("Buy Bait");
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
        if(e.getSource() == test) {
            System.out.println("test");
        }
        if(e.getSource() == back) {
            this.setVisible(false);
            mf.setVisible(true);
        }
        if(e.getSource() == buyBait) {
            playerInventory.addBait(new Bait("TestBait", 5));
            playerInventory.useMoney(10);
        }
        if(e.getSource() == sellFish) 
        {
            ShopFishFrame shopFishFrame = new ShopFishFrame(playerInventory, this);
            this.setVisible(false);
            shopFishFrame.setVisible(true);
        }
        if(e.getSource() == sellAllFish) 
        {            
            playerInventory.addMoney(playerInventory.sellAllFish());
        }
        enableDisableComponents();
    }

    public void enableDisableComponents() {
        if(playerInventory.checkForFish() == false) {
            sellAllFish.setEnabled(false);
            sellFish.setEnabled(false);
        } 
    }

    private void initComponent() //all the bounds can change
    {
        test.setBounds(50, 200, 100, 50);
        back.setBounds(150, 200, 100, 50);
        buyBait.setBounds(250, 200, 100, 50);
        sellFish.setBounds(100, 100, 100, 50);
        sellAllFish.setBounds(200, 100, 100, 50);

        add(test);
        add(back);
        add(buyBait);
        add(sellFish);
        add(sellAllFish);

        test.addActionListener(this);
        back.addActionListener(this);
        buyBait.addActionListener(this);
        sellFish.addActionListener(this);
        sellAllFish.addActionListener(this);

        enableDisableComponents();
    }
}
