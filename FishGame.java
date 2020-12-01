import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.event.*;
import java.awt.*;

public class FishGame extends JFrame implements ActionListener{

    private MainFrame mf;
    private Inventory playerInventory;

    private JButton back = new JButton("Back");

    


    public FishGame(MainFrame mf, Inventory playerInventory) {
        this.playerInventory = playerInventory;
        this.mf = mf;

        setTitle("Do you want to play a game?");
        setSize(1000,1000);
        setLocation(new Point(100, 100));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        FishGamePanel fishGamePanel = new FishGamePanel();
        setContentPane(fishGamePanel);

        initComponent();
    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            this.setVisible(false);
            mf.setVisible(true);
        }
    }

    public void initComponent() {
        back.setBounds(100,50,900,900);
        back.addActionListener(this);
        add(back);
    }
    
}
