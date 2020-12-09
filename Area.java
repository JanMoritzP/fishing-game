import java.time.LocalTime;
import java.util.ArrayList;

public class Area {

    private String name;
    private int price;

    private AreaManagerParser areaManagerParser = new AreaManagerParser();

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
        ArrayList<Double> percentageList = areaManagerParser.getPercentageList(getName());
        ArrayList<String> fishList = areaManagerParser.getAreaFishList(getName());
        FishParser fishParser = new FishParser();
        return fishParser.createFish(fishList, percentageList);
    }

}
