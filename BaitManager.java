import java.awt.Point;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.WindowConstants;

public class BaitManager extends JFrame implements ActionListener {
    public static void main(String[] args) {
        BaitManager baitManager = new BaitManager();
        baitManager.setVisible(true);
    }

    private JPanel panel1 = new JPanel();
    private JScrollPane scrollPane1 = new JScrollPane(panel1);

    private JPanel panel2 = new JPanel();
    private JScrollPane scrollPane2 = new JScrollPane(panel2);

    private JButton quit = new JButton("Quit");
    private JButton back = new JButton("Back");

    private String current = null;
    private Mutable<String> status = new Mutable<String>("area");

    private JLabel label1 = new JLabel("Likeliness");
    private JLabel label2 = new JLabel("");
    
    private JTextField text1 = new JTextField();

    private FishParser fishParser = new FishParser();
    private BaitParser baitParser = new BaitParser();
    private BaitManagerParser baitManagerParser = new BaitManagerParser();

    private ArrayList<JButton> buttonList1 = new ArrayList<JButton>();
    private ArrayList<JButton> buttonList2 = new ArrayList<JButton>();

    {
        setTitle("Bait-Manager");
        setSize(650, 400);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();

        baitManagerParser.initializeFish();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quit) {
            System.exit(1);
        }
        else if(e.getSource() == back) {
            status.setVariable("fish");
            current = null;
        }
        else if(status.getVariable() == "fish") {
            current = ((JButton) e.getSource()).getText();
            status.setVariable("bait");
        }
        else {
            if(buttonList1.contains(e.getSource())) { 
                int index = buttonList1.indexOf(e.getSource());
                baitManagerParser.removeObject(index, current);
            }
            else { //Right Panel
                baitManagerParser.addObject(((JButton) e.getSource()).getText(), Double.valueOf(text1.getText()), current);
            }
        }
        loadScrollpane(status.getVariable());
    }

    public void initComponent() {

        quit.setBounds(440, 180, 100, 50);
        quit.addActionListener(this);
        add(quit);

        back.setBounds(440, 280, 100, 50);
        back.addActionListener(this);
        add(back);

        label1.setBounds(440, 10, 100, 20);
        label2.setBounds(440, 110, 200, 20);
        add(label1);
        add(label2);

        text1.setBounds(440, 40, 100, 20);
        add(text1);

        
        panel1.setLayout(new GridLayout(50, 1));
        panel2.setLayout(new GridLayout(50, 1));
        
        scrollPane1.setLayout(new ScrollPaneLayout());
        scrollPane2.setLayout(new ScrollPaneLayout());
        scrollPane1.setBounds(10, 10, 200, 300);
        scrollPane2.setBounds(220, 10, 200, 300);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane1);
        add(scrollPane2);
        
        
        loadScrollpane(status.getVariable());
    }

    private void loadScrollpane(String mode) {
        Iterator<JButton> buttonIterator1 = buttonList1.iterator();
        Iterator<JButton> buttonIterator2 = buttonList2.iterator();
        while(buttonIterator1.hasNext()) {
            panel1.remove(buttonIterator1.next());
            buttonIterator1.remove();
        }
        while(buttonIterator2.hasNext()) {
            panel2.remove(buttonIterator2.next());
            buttonIterator2.remove();
        }
        if(mode == "fish") {    //Load only left panel
            ArrayList<Fish> baitList = fishParser.getFishList();
            Iterator<Fish> baitIterator = baitList.iterator();
            JButton tempButton;
            while(baitIterator.hasNext()) {
                tempButton = new JButton(String.format("%s", baitIterator.next().getName()));
                tempButton.addActionListener(this);
                buttonList1.add(tempButton);
                panel1.add(tempButton);
            }
        }
        else if(mode == "bait") {   //Load left panel with with in fish; load right panel with available
            ArrayList<Double> likelinessList = baitManagerParser.getLikelinessList(current);
            ArrayList<String> baitNameList = baitManagerParser.getAvailableBaitList(current);

            Iterator<Double> likelinessIterator = likelinessList.iterator();
            Iterator<String> nameIterator = baitNameList.iterator();
            JButton tempButton;
            while(nameIterator.hasNext()) {
                tempButton = new JButton(String.format("%s, %.2f",nameIterator.next(), likelinessIterator.next()));
                tempButton.addActionListener(this);
                buttonList1.add(tempButton);
                panel1.add(tempButton);
            }

            ArrayList<String> availableFishNames = baitManagerParser.getAvailableBaitList(current);
            Iterator<String> availableNameIterator = availableFishNames.iterator();

            while(availableNameIterator.hasNext()) {
                tempButton = new JButton(availableNameIterator.next());
                tempButton.addActionListener(this);
                buttonList2.add(tempButton);
                panel2.add(tempButton);
            }
        }
        panel1.revalidate();
        panel1.repaint();
        panel2.revalidate();
        panel2.repaint();
    }   
}
