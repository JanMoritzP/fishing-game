
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InvFrame extends JFrame implements ActionListener
{
    private JPanel panel = new JPanel();
    private JScrollPane scrollpane = new JScrollPane(panel);
    private JButton bait = new JButton("Bait");
    private JButton rod = new JButton("Rods");
    private JButton fish = new JButton("Fish");
    private JButton reel = new JButton("Reels");
    private JButton hooks = new JButton("Hooks");
    private JButton back = new JButton("Back");
    private JButton equip = new JButton("Equip");
    private JLabel money = new JLabel();

    private MainFrame mf;
    private Inventory playerInventory;

    public InvFrame(MainFrame mf, Inventory playerInventory) 
    {
        this.mf = mf;
        this.playerInventory = playerInventory;

        setTitle("Inventory-menu");
        setSize(600,360);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    private void initComponent()
    {
        bait.setBounds(250, 10, 100, 50);
        fish.setBounds(250, 60, 100, 50);
        hooks.setBounds(250, 110, 100, 50);
        rod.setBounds(250, 160, 100, 50);
        reel.setBounds(250, 210, 100, 50);
        back.setBounds(250, 260, 100, 50);
        money.setBounds(400, 10, 50, 25);
        equip.setBounds(10, 285, 240, 25);

        scrollpane.setBounds(10, 10, 240, 280);

        add(bait);
        add(fish);
        add(hooks);
        add(rod);
        add(reel);
        add(back);
        add(equip);
        add(money);

        add(scrollpane);

        fish.addActionListener(this);
        bait.addActionListener(this);
        hooks.addActionListener(this);
        rod.addActionListener(this);
        reel.addActionListener(this);
        back.addActionListener(this);

        panel.setLayout(new GridLayout(5, 2)); //this might change? not sure how it would look.
        scrollpane.setLayout(new ScrollPaneLayout());
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == fish) 
        {
            System.out.println("fishy");
        }
        else if(e.getSource() == bait)
        {
            System.out.println("bait");
        }
        else if(e.getSource() == hooks)
        {
            System.out.println("hooks");
        }
        else if(e.getSource() == rod)
        {
            System.out.println("rod");
        }
        else if(e.getSource() == reel)
        {
            System.out.println("reel");
        }
        else if(e.getSource() == back)
        {
            this.setVisible(false);
            mf.setVisible(true);
        }
    }
}
