
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class MainFrame extends JFrame implements ActionListener
{
    public static void main(String[] args)
    {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
    }  

    private Inventory playerInventory = new Inventory();
    

    private JButton shop = new JButton("Shop");
    private JButton inv = new JButton("Inventory");
    private JButton fish = new JButton("Go fish!");
    private JButton bait = new JButton("Bait");
    private JButton quit = new JButton("quit");

    private JLabel title = new JLabel("Welcome to Fishing Game"); //this hasn't been implemented yet
    
    public MainFrame() 
    {
        setTitle("Fishing-Game");
        setSize(600,300);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ///*
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        playerInventory.addFish(new Fish("trout",5,5));
        //*/
        initComponent();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == shop) 
        {
            ShopFrame shopFrame = new ShopFrame(this, playerInventory);
            this.setVisible(false);
            shopFrame.setVisible(true);

        }
        else if(e.getSource() == inv) System.out.println("Inventory");
        else if(e.getSource() == fish) {
            FishGame fishGame = new FishGame(this, playerInventory);
            this.setVisible(false);
            fishGame.setVisible(true);
        }
        else if(e.getSource() == bait) System.out.println("Ha! baited :p");
        else if(e.getSource() == quit) System.exit(1);
    }
    

    private void initComponent() //all the bounds can change
    {
        shop.setBounds(50, 200, 100, 50);
        inv.setBounds(150, 200, 100, 50);
        fish.setBounds(250, 200, 100, 50);
        bait.setBounds(350, 200, 100, 50);
        quit.setBounds(450, 200, 100, 50);

        add(shop);
        add(inv);
        add(fish);
        add(bait);
        add(quit);

        shop.addActionListener(this);
        inv.addActionListener(this);
        fish.addActionListener(this);
        bait.addActionListener(this);
        quit.addActionListener(this);
    }
}
