import javax.swing.JFrame;
import java.awt.event.ActionListener;

public class ShopFishFrame extends JFrame implements ActionListener {

    private Inventory playerInventory;
    private ShopFrame shopFrame;


    public ShopFishFrame(Inventory playerInventory, ShopFrame shopFrame) {
        this.playerInventory = playerInventory;
        this.shopFrame = shopFrame;


    }
    
}
