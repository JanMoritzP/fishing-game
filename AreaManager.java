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

/*
IDEA: 2 Scrollpanes. Left one shows Areas. Click on Area. 
Right pane shows available fish, left one shows added fish with percentages. 
Click on fish on right and add to area with given percentage. Click on fish in left pane to delete
*/

public class AreaManager extends JFrame implements ActionListener {
    public static void main(String[] args) {
        AreaManager areaManager = new AreaManager();
        areaManager.setVisible(true);
    }

    private JPanel panel1 = new JPanel();
    private JScrollPane scrollPane1 = new JScrollPane(panel1);

    private JPanel panel2 = new JPanel();
    private JScrollPane scrollPane2 = new JScrollPane(panel2);

    private JButton quit = new JButton("Quit");
    private JButton back = new JButton("Back");

    private String current;
    private Mutable<String> status = new Mutable<String>("area");

    private JLabel label1 = new JLabel("Percentage");
    private JLabel label2 = new JLabel("Percentage is above 100%");
    
    private JTextField text1 = new JTextField();

    private FishParser fishParser = new FishParser();
    private AreaParser areaParser = new AreaParser();
    private AreaManagerParser areaManagerParser = new AreaManagerParser();

    private ArrayList<JButton> buttonList1 = new ArrayList<JButton>();
    private ArrayList<JButton> buttonList2 = new ArrayList<JButton>();

    {
        setTitle("Area-Manager");
        setSize(650, 400);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quit) {
            System.exit(1);
        }
        else if(e.getSource() == back) {
            status.setVariable("area");
            loadScrollpane(status.getVariable());
        }
        else if(status.getVariable() == "area") {  //Check first wether fish is already in areaManager or not
            //nameOfArea = e.getSource().getText(); <-- select area by clicking on it
            current = ((JButton) e.getSource()).getText();
            status.setVariable("fish");
            loadScrollpane(status.getVariable());
        }
        else {

        }
    }

    public void initComponent() {

        quit.setBounds(440, 280, 100, 50);
        quit.addActionListener(this);
        add(quit);

        label1.setBounds(440, 10, 100, 20);
        label2.setBounds(440, 110, 100, 20);
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
        if(mode == "area") {    //Load only left panel
            ArrayList<Area> areaList = areaParser.getAreaList();
            Iterator<Area> areaIterator = areaList.iterator();
            JButton tempButton;
            while(areaIterator.hasNext()) {
                tempButton = new JButton(String.format("%s", areaIterator.next().getName()));
                tempButton.addActionListener(this);
                buttonList1.add(tempButton);
                panel1.add(tempButton);
            }
        }
        else if(mode == "fish") {   //Load left panel with with in area; load right panel with available
            ArrayList<Double> percentageList = areaManagerParser.getPercentageList(current);
            ArrayList<String> fishNameList = areaManagerParser.getAreaFishList(current);

            Iterator<Double> percentageIterator = percentageList.iterator();
            Iterator<String> nameIterator = fishNameList.iterator();
            JButton tempButton;
            while(nameIterator.hasNext()) {
                tempButton = new JButton(String.format("%s, %.2f",nameIterator.next(), percentageIterator.next()));
                tempButton.addActionListener(this);
                buttonList1.add(tempButton);
                panel1.add(tempButton);
            }

            ArrayList<String> availableFishNames = areaManagerParser.getAvailableFishList(current);

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
