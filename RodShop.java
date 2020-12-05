import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.*;

public class RodShop extends JFrame implements ActionListener
{
    
    private ShopFrame shopFrame;
    private Inventory playerInventory;
    
    private JButton rod1 = new JButton("Bad Rod");
    private JButton rod2 = new JButton("Medium Rod");
    private JButton rod3 = new JButton("Decent Rod");
    private JButton rod4 = new JButton("Good Rod");
    private JButton rod5 = new JButton("God Rod");   
    
    
    public RodShop(ShopFrame shopFrame, Inventory playerInventory) {
        this.shopFrame = shopFrame;
        this.playerInventory = playerInventory;
    }


    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == rod1) {
            
        }
        if(e.getSource() == rod2) {
            
        }
        if(e.getSource() == rod3) {
            
        }
        if(e.getSource() == rod4) {
            
        }
        if(e.getSource() == rod5) {
            
        }

    }


    public void initComponent() {
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
        ArrayList<Rod> playerRods = playerInventory.
        
        
        
        
        rod1.setEnabled(false);
        rod2.setEnabled(false);
        rod3.setEnabled(false);
        rod4.setEnabled(false);
        rod5.setEnabled(false);


    }





}
