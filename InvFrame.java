
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InvFrame extends JFrame implements ActionListener, ItemListener
{
    private JPanel panel = new JPanel();
    private JScrollPane scrollpane = new JScrollPane(panel);

    private JButton bait = new JButton("Bait");
    private JButton rod = new JButton("Rods");
    private JButton fish = new JButton("Fish");
    private JButton line = new JButton("Lines");
    private JButton hooks = new JButton("Hooks");
    private JButton back = new JButton("Back");
    private JButton equip = new JButton("Equip");

    private JLabel money = new JLabel();

    private ArrayList<JCheckBox> itemList = new ArrayList<JCheckBox>();

    private MainFrame mf;
    private Inventory playerInventory;

    public InvFrame(MainFrame mf, Inventory playerInventory) 
    {
        this.mf = mf;
        this.playerInventory = playerInventory;

        setTitle("Inventory-menu");
        setSize(600,400);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

   

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == fish) 
        {
            Iterator<JCheckBox> boxIterator = itemList.iterator();
            while(boxIterator.hasNext()) {
                panel.remove(boxIterator.next());
            }
            itemList.clear();
            ArrayList<Fish> playerFish = playerInventory.getFish();
            Iterator<Fish> fishIterator = playerFish.iterator();
            JCheckBox tempBox;
            Fish tempFish;
            while(fishIterator.hasNext()) {
                tempFish = fishIterator.next();
                tempBox = new JCheckBox(String.format(tempFish.getName() + ", Size: %d, Value: %d", tempFish.getSize(), tempFish.getValue()));
                itemList.add(tempBox);
                tempBox.addItemListener(this);
            }
            boxIterator = itemList.iterator();
            while(boxIterator.hasNext()) {
                panel.add(boxIterator.next());
            }
        }
        if(e.getSource() == bait)
        {            
            System.out.println("bait");
        }
        if(e.getSource() == hooks)
        {
            Iterator<JCheckBox> boxIterator = itemList.iterator();
            while(boxIterator.hasNext()) {
                panel.remove(boxIterator.next());
            }
            itemList.clear();
            ArrayList<Hook> playerHook = playerInventory.getHookList();
            Iterator<Hook> hookIterator = playerHook.iterator();
            JCheckBox tempBox;
            Hook tempHook;
            int i = 0;
            while(hookIterator.hasNext()) {
                tempHook = hookIterator.next();
                tempBox = new JCheckBox(String.format(tempHook.getName() + ", Size: %d, Amount: %d", tempHook.getSize(), playerInventory.getHookAmountList().get(i)));
                itemList.add(tempBox);
                tempBox.addItemListener(this);
                i++;
            }
            boxIterator = itemList.iterator();
            while(boxIterator.hasNext()) {
                panel.add(boxIterator.next());
            }
        }
        if(e.getSource() == rod)
        {
            Iterator<JCheckBox> boxIterator = itemList.iterator();
            while(boxIterator.hasNext()) {
                panel.remove(boxIterator.next());
            }
            itemList.clear();
            ArrayList<Rod> playerRod = playerInventory.getRodList();
            Iterator<Rod> rodIterator = playerRod.iterator();
            JCheckBox tempBox;
            Rod tempRod;
            while(rodIterator.hasNext()) {
                tempRod = rodIterator.next();
                tempBox = new JCheckBox(String.format(tempRod.getName() + ", Level: %d", tempRod.getLevel()));
                itemList.add(tempBox);
                tempBox.addItemListener(this);
            }
            boxIterator = itemList.iterator();
            while(boxIterator.hasNext()) {
                panel.add(boxIterator.next());
            }
        }
        if(e.getSource() == line)
        {
            System.out.println("line");
        }
        if(e.getSource() == back)
        {
            this.setVisible(false);
            mf.setVisible(true);
        }        
        panel.revalidate();
        panel.repaint();
        
    }

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == 1) {  //Selected something
            Iterator<JCheckBox> boxIterator = itemList.iterator();
            JCheckBox tempBox;
            int i = 0;
            while(boxIterator.hasNext()) {
                tempBox = boxIterator.next();
                if(e.getSource() != tempBox) {
                    itemList.get(i).setEnabled(false);
                }
                i++;
            }
        }
        else {  //Deselected something
            Iterator<JCheckBox> boxIterator = itemList.iterator();
            while(boxIterator.hasNext()) {
                boxIterator.next().setEnabled(true);
            }
        }
    }

    private void initComponent()
    {
        bait.setBounds(250, 10, 100, 50);
        fish.setBounds(250, 60, 100, 50);
        hooks.setBounds(250, 110, 100, 50);
        rod.setBounds(250, 160, 100, 50);
        line.setBounds(250, 210, 100, 50);
        back.setBounds(250, 260, 100, 50);
        money.setBounds(400, 10, 50, 25);
        equip.setBounds(10, 285, 240, 25);

        scrollpane.setBounds(10, 10, 240, 280);

        add(bait);
        add(fish);
        add(hooks);
        add(rod);
        add(line);
        add(back);
        add(equip);
        add(money);

        money.setText(String.format("%.2f", playerInventory.getMoney()));
        
        fish.addActionListener(this);
        bait.addActionListener(this);
        hooks.addActionListener(this);
        rod.addActionListener(this);
        line.addActionListener(this);
        back.addActionListener(this);
        
        panel.setLayout(new GridLayout(5, 2)); //this might change? not sure how it would look.
        scrollpane.setLayout(new ScrollPaneLayout());
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(scrollpane);
    }

}
