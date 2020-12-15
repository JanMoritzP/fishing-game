import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Point;
import java.awt.event.*;
import java.util.Iterator;
import java.util.ArrayList;

public class RodShop extends JFrame implements ActionListener
{
    
    private ShopFrame shopFrame;
    private Inventory playerInventory;
    
    private JButton rod1 = new JButton("Bad Rod");
    private JButton rod2 = new JButton("Medium Rod");
    private JButton rod3 = new JButton("Decent Rod");
    private JButton rod4 = new JButton("Good Rod");
    private JButton rod5 = new JButton("God Rod");   

    private JButton back = new JButton("Back");
    
    
    public RodShop(ShopFrame shopFrame, Inventory playerInventory) {
        this.shopFrame = shopFrame;
        this.playerInventory = playerInventory;

        setTitle("Rod-Shop");
        setSize(600,400);
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

        if(e.getSource() == rod1) {
            playerInventory.useMoney(10);
            playerInventory.addRod(new Rod("Bad Rod", 1));
        }
        if(e.getSource() == rod2) {
            playerInventory.useMoney(20);
            playerInventory.addRod(new Rod("Medium Rod", 1));            
        }
        if(e.getSource() == rod3) {
            playerInventory.useMoney(50);
            playerInventory.addRod(new Rod("Decent Rod", 1));            
        }
        if(e.getSource() == rod4) {
            playerInventory.useMoney(100);
            playerInventory.addRod(new Rod("Good Rod", 1));            
        }
        if(e.getSource() == rod5) {
            playerInventory.useMoney(500);
            playerInventory.addRod(new Rod("God Rod", 1));            
        }
        enableDisableComponent();

    }


    public void initComponent() {

        back.setBounds(400, 10, 100, 50);
        add(back);
        back.addActionListener(this);

        rod1.setBounds(10, 10, 100, 50);
        rod2.setBounds(10, 70, 100, 50);
        rod3.setBounds(10, 130, 100, 50);
        rod4.setBounds(10, 190, 100, 50);
        rod5.setBounds(10, 250, 100, 50);
        
        add(rod1);
        add(rod2);
        add(rod3);
        add(rod4);
        add(rod5);        
        
        rod1.addActionListener(this);
        rod2.addActionListener(this);
        rod3.addActionListener(this);
        rod4.addActionListener(this);
        rod5.addActionListener(this);
    }
    
    private void enableDisableComponent() {
        ArrayList<Rod> playerRods = playerInventory.getRodList();
        Iterator<Rod> rodIterator = playerRods.iterator();
        Rod tempRod;
        while(rodIterator.hasNext()) {
            tempRod = rodIterator.next();
            if(tempRod.getName() == "Bad Rod") {
                rod1.setEnabled(false);
            }
            if(tempRod.getName() == "Medium Rod") {
                rod2.setEnabled(false);
            }
            if(tempRod.getName() == "Decent Rod") {
                rod3.setEnabled(false);
            }
            if(tempRod.getName() == "Good Rod") {
                rod4.setEnabled(false);
            }
            if(tempRod.getName() == "God Rod") {
                rod5.setEnabled(false);
            }
        }
        if(playerInventory.getMoney() < 500) {
            rod5.setEnabled(false);
        }
        if(playerInventory.getMoney() < 100) {
            rod4.setEnabled(false);
        }
        if(playerInventory.getMoney() < 50) {
            rod3.setEnabled(false);
        }
        if(playerInventory.getMoney() < 20) {
            rod2.setEnabled(false);
        }
        if(playerInventory.getMoney() < 10) {
            rod1.setEnabled(false);
        }
    }





}
