import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TestBackend extends JFrame {
    public static void main(String[] args) {
        TestBackend frame = new TestBackend();
        frame.setVisible(true);
        frame.animation();
    }
    public BufferedImage image;

    public JPanel panel = new JPanel();
    public JLabel label = new JLabel();

    public TestBackend() {
        setTitle("Test Animation");
        setSize(600, 300);
        setLocation(new Point(600, 300));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponent();

        try {
            label.setIcon(new ImageIcon(ImageIO.read(new File("icons/idle/0.bmp"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void animation() {
        while (true) {
            try {
                label.setIcon(new ImageIcon(ImageIO.read(new File("icons/idle/0.bmp"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                label.setIcon(new ImageIcon(ImageIO.read(new File("icons/idle/1.bmp"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                label.setIcon(new ImageIcon(ImageIO.read(new File("icons/idle/2.bmp"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                label.setIcon(new ImageIcon(ImageIO.read(new File("icons/idle/3.bmp"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                label.setIcon(new ImageIcon(ImageIO.read(new File("icons/idle/4.bmp"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void initComponent() {
        label.setBounds(0, 0, 400, 200);
        panel.setBounds(0, 0, 400, 200);
        panel.add(label);
        add(panel);
    }
}
