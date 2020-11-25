
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

    private JButton shop = new JButton ("Shop");
    private JButton inv = new JButton ("Inventory");
    private JButton fish = new JButton ("Go fish!");
    private JButton bait = new JButton ("Bait");
    private JButton quit = new JButton ("quit");

    private JLabel title = new JLabel ("Welcome to [insert gamename]"); //this hasn't been implemented yet
    
    public MainFrame()
    {
    setTitle("[Insert Gamename]"); //wasn't sure what to cal the game
    setSize(600,300);
    setLocation(new Point(600, 300));
    setLayout(null);
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    initComponent();

    }

    public void actionPerformed(ActionEvent e) //all of the prints are placeholders, we can make them open other frames/panels later
    {
        if(e.getSource() == shop) System.out.println("Shop");
        else if(e.getSource() == inv) System.out.println("Inventory");
        else if(e.getSource() == fish) System.out.println("This button has some fishy business");
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