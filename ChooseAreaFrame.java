import javax.swing.*;
import javax.swing.WindowConstants;


import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ChooseAreaFrame extends JFrame implements ActionListener {
    
    private MainFrame mainFrame;
    private Inventory playerInventory;
    
    private ArrayList<JButton> buttonList;
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panel);

    private JButton back = new JButton("Back");

    private AreaParser areaParser = new AreaParser();
    private ArrayList<Area> areaList = areaParser.getAreaList();



    public ChooseAreaFrame(MainFrame mainFrame, Inventory playerInventory) {
        this.mainFrame = mainFrame;
        this.playerInventory = playerInventory;

        buttonList = new ArrayList<JButton>();

        setTitle("Select Area");
        setSize(600,300);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        initComponent();

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            this.setVisible(false);
            mainFrame.setVisible(true);
        }
        else {
            int index = buttonList.indexOf(e.getSource());
            playerInventory.useMoney(areaList.get(index).getPrice());;
            FishGame fishGame = new FishGame(mainFrame, playerInventory, areaList.get(index));
            fishGame.setVisible(true);
            this.setVisible(false);
        }
        
    }

    public void initComponent() {
        back.setBounds(470, 10, 100, 50);
        add(back);
        back.addActionListener(this);

        Iterator<Area> areaIterator = areaList.iterator();
        Area tempArea;
        JButton tempButton;
        while(areaIterator.hasNext()) {
            tempArea = areaIterator.next();
            tempButton = new JButton(String.format("%s [%d]", tempArea.getName(), tempArea.getPrice()));
            tempButton.addActionListener(this);
            panel.add(tempButton);
            buttonList.add(tempButton);
        }



        panel.setLayout(new GridLayout(10, 1)); 
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBounds(10, 10, 200, 200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

        enableDisableComponents();
    }

    private void enableDisableComponents() {
        Iterator<JButton> buttonIterator = buttonList.iterator();
        ArrayList<Area> areaList = areaParser.getAreaList();
        Iterator<Area> areaIterator = areaList.iterator();
        while(buttonIterator.hasNext()) {
            if(playerInventory.getMoney() < areaIterator.next().getPrice()) {
                buttonIterator.next().setEnabled(false);
            }
            else buttonIterator.next().setEnabled(true);
        }
    }



}
