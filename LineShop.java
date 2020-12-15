import java.awt.Point;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class LineShop extends JFrame implements ActionListener{
    
    private ShopFrame shopFrame;
    private Inventory playerInventory;

    private JButton back = new JButton("Back");

    private JButton lineButton1 = new JButton("Bad Line [10]");
    private JButton lineButton2 = new JButton("Medium Line [20]");
    private JButton lineButton3 = new JButton("Decent Line [50]");
    private JButton lineButton4 = new JButton("Good Line [100]");
    private JButton lineButton5 = new JButton("Divine Line [500]");

    private Line line1 = new Line(2, 10, "Bad Line");
    private Line line2 = new Line(5, 20, "Medium Line");
    private Line line3 = new Line(10, 50, "Decent Line");
    private Line line4 = new Line(15, 100, "Good Line");
    private Line line5 = new Line(20, 500, "Divine Line");

    public LineShop(ShopFrame shopFrame, Inventory playerInventory) {
        this.playerInventory = playerInventory;
        this.shopFrame = shopFrame;

        setTitle("Line-Shop");
        setSize(600,400);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            this.setVisible(false);
            shopFrame.setVisible(true);
            shopFrame.enableDisableComponents();
        }
        if(e.getSource() == lineButton1) {
            playerInventory.useMoney(10);
            playerInventory.addLine(line1);
        }
        if(e.getSource() == lineButton2) {
            playerInventory.useMoney(20);
            playerInventory.addLine(line2);            
        }
        if(e.getSource() == lineButton3) {
            playerInventory.useMoney(50);
            playerInventory.addLine(line3);            
        }
        if(e.getSource() == lineButton4) {
            playerInventory.useMoney(100);
            playerInventory.addLine(line4);            
        }
        if(e.getSource() == lineButton5) {
            playerInventory.useMoney(500);
            playerInventory.addLine(line5);            
        }        
        enableDisableComponents();
    }

    public void initComponent() {
        back.setBounds(450, 20, 100, 50);
        add(back);
        back.addActionListener(this);
        
        lineButton1.setBounds(20, 20, 150, 50);
        lineButton2.setBounds(20, 80, 150, 50);
        lineButton3.setBounds(20, 140, 150, 50);
        lineButton4.setBounds(20, 200, 150, 50);
        lineButton5.setBounds(20, 260, 150, 50);

        add(lineButton1);
        add(lineButton2);
        add(lineButton3);
        add(lineButton4);
        add(lineButton5);

        lineButton1.addActionListener(this);
        lineButton2.addActionListener(this);
        lineButton3.addActionListener(this);
        lineButton4.addActionListener(this);
        lineButton5.addActionListener(this);

        enableDisableComponents();
    }

    private void enableDisableComponents() {
        lineButton1.setEnabled(false);
        lineButton2.setEnabled(false);
        lineButton3.setEnabled(false);
        lineButton4.setEnabled(false);
        lineButton5.setEnabled(false);

        if(playerInventory.getMoney() > 10) {
            lineButton1.setEnabled(true);
        }
        if(playerInventory.getMoney() > 20) {
            lineButton2.setEnabled(true);
        }
        if(playerInventory.getMoney() > 50) {
            lineButton3.setEnabled(true);
        }
        if(playerInventory.getMoney() > 100) {
            lineButton4.setEnabled(true);
        }
        if(playerInventory.getMoney() > 500) {
            lineButton5.setEnabled(true);
        }
    }
}
