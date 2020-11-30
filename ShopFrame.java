import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShopFrame extends JFrame implements ActionListener
{
    private JButton test = new JButton("test");
    private JButton back = new JButton("back");
    private MainFrame mf;


    public ShopFrame(MainFrame mf) {
        this.mf = mf;

        setTitle("Shop-Menu");
        setSize(600,300);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == test) {
            System.out.println("test");
        }
        if(e.getSource() == back) {
            this.setVisible(false);
            mf.setVisible(true);
        }
    }

    private void initComponent() //all the bounds can change
    {
        test.setBounds(50, 200, 100, 50);
        back.setBounds(150, 200, 100, 50);

        add(test);
        add(back);

        test.addActionListener(this);
        back.addActionListener(this);
    }


}
