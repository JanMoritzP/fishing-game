import java.time.LocalTime;
import java.util.ArrayList;

public class Area {

    private String name;
    private int price;

    private ArrayList<Fish> fishList;

    public Area(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getTime() {
        return LocalTime.now().toString().substring(0, 8);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Fish createFish() { // Maybe this should be Fish[] or ArrayList<Fish>
        FishParser fishParser = new FishParser();
        return fishParser.getFishList().get(0);
    }

}
