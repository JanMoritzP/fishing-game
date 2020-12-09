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

public class SrcManager extends JFrame implements ActionListener {
    public static void main(String[] args) {
        SrcManager srcManager = new SrcManager();
        srcManager.setVisible(true);
    }

    private JPanel panel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panel);

    private JButton quit = new JButton("Quit");
    private JButton add = new JButton("Add");

    private JButton fish = new JButton("Fish");
    private JButton area = new JButton("Area");
    private JButton current;

    private JLabel label1 = new JLabel("");
    private JLabel label2 = new JLabel("");
    private JLabel label3 = new JLabel("");
    
    private JTextField text1 = new JTextField();
    private JTextField text2 = new JTextField();
    private JTextField text3 = new JTextField();

    private FishParser fishParser = new FishParser();
    private AreaParser areaParser = new AreaParser();

    private ArrayList<JButton> buttonList = new ArrayList<JButton>();

    {
        setTitle("Src-Manager");
        setSize(600, 400);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add) {
            if(current == fish) {
                fishParser.addObject(text1.getText(), Integer.valueOf(text2.getText()), Integer.valueOf(text3.getText()));
            }
            else if(current == area) {
                areaParser.addObject(text1.getText(), Integer.valueOf(text2.getText()));
            }
            loadScrollpane(current);
        }
        else if (e.getSource() == quit) {
            System.exit(1);
        }
        else if(e.getSource() == fish || e.getSource() == area) {
            current = (JButton) e.getSource();
            add.setVisible(true);
            loadScrollpane((JButton) e.getSource());
            loadTextAndLables((JButton) e.getSource());
        }
        else {
            int index = buttonList.indexOf(e.getSource());
            if(current == fish) {
                fishParser.removeObject(index);
            }
            else if(current == area) {                
                areaParser.removeObject(index);
            }
            loadScrollpane(current);
        }
    }

    public void initComponent() {

        quit.setBounds(470, 280, 100, 50);
        add.setBounds(470, 150, 100, 50);
        quit.addActionListener(this);
        add.addActionListener(this);
        add(quit);
        add(add);
        
        add.setVisible(false);

        fish.setBounds(350, 10, 100, 50);
        area.setBounds(350, 150, 100, 50);
        fish.addActionListener(this);
        area.addActionListener(this);
        add(fish);
        add(area);

        label1.setBounds(220, 10, 100, 20);
        label2.setBounds(220, 110, 100, 20);
        label3.setBounds(220, 210, 100, 20);
        add(label1);
        add(label2);
        add(label3);

        text1.setBounds(220, 40, 100, 20);
        text2.setBounds(220, 140, 100, 20);
        text3.setBounds(220, 240, 100, 20);
        add(text1);
        add(text2);
        add(text3);
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);

        
        panel.setLayout(new GridLayout(50, 1));
        
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBounds(10, 10, 200, 200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

    }

    private void loadScrollpane(JButton source) {
        Iterator<JButton> buttonIterator = buttonList.iterator();
        while(buttonIterator.hasNext()) {
            panel.remove(buttonIterator.next());
            buttonIterator.remove();
        }
        if(source == fish) {
            ArrayList<Fish> fishList = fishParser.getFishList();
            Iterator<Fish> fishIterator = fishList.iterator();
            Fish tempFish;
            JButton tempButton;
            while(fishIterator.hasNext()) {
                tempFish = fishIterator.next();
                tempButton = new JButton(String.format("Name: %s, Size: %d, Value: %d",tempFish.getName(), tempFish.getSize(), tempFish.getValue()));
                tempButton.addActionListener(this);
                panel.add(tempButton);
                buttonList.add(tempButton);
            }
        }
        if(source == area) {
            ArrayList<Area> areaList = areaParser.getAreaList();
            Iterator<Area> areaIterator = areaList.iterator();
            Area tempArea;
            JButton tempButton;
            while(areaIterator.hasNext()) {
                tempArea = areaIterator.next();
                tempButton = new JButton(String.format("Name: %s, Price: %d",tempArea.getName(), tempArea.getPrice()));
                tempButton.addActionListener(this);
                panel.add(tempButton);
                buttonList.add(tempButton);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    private void loadTextAndLables(JButton source) {
        if(source == fish) {
            label1.setText("Name");
            label2.setText("Size");
            label3.setVisible(true);
            label3.setText("Value");
            text1.setVisible(true);
            text2.setVisible(true);
            text3.setVisible(true);
        }
        if(source == area) {
            label1.setText("Name");
            label2.setText("Price");
            label3.setVisible(false);   
            text1.setVisible(true);
            text2.setVisible(true);  
            text3.setVisible(false);       
        }
    }
    
}
