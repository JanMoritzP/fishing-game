import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class FishGame extends JFrame implements ActionListener{

    private MainFrame mf;
    private Inventory playerInventory;
    private Area playerArea;

    private ArrayList<Fish> liveFish = new ArrayList<Fish>();
    private ArrayList<int[]> fishPosition = new ArrayList<int[]>();
    private Mutable<Boolean> gameRuns, fishCreated;

    private JButton back = new JButton("Back");

    


    public FishGame(MainFrame mf, Inventory playerInventory, Area playerArea) {
        this.playerInventory = playerInventory;
        this.mf = mf;
        this.playerArea = playerArea;

        gameRuns = new Mutable<Boolean>(true);
        fishCreated = new Mutable<Boolean>(true);

        setTitle("Do you want to play a game?");
        setSize(1000,1000);
        setLocation(new Point(100, 100));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        FishGamePanel fishGamePanel = new FishGamePanel(liveFish, fishPosition, gameRuns, playerInventory);
        setContentPane(fishGamePanel);


        initComponent();
        Thread createLiveFish = new Thread(new CreateLiveFish(liveFish, fishPosition, fishCreated, playerArea));
        createLiveFish.start();
    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            this.setVisible(false);
            mf.setVisible(true);
            gameRuns.setVariable(false);
            fishCreated.setVariable(false);
        }
    }

    public void initComponent() {
        back.setBounds(100,50,900,900);
        back.addActionListener(this);
        add(back);
    }
    
}
