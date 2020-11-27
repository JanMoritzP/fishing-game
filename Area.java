import java.time.LocalTime;

public class Area {

    private String name;


    public Area(String name) {
        this.name = name;
    }

    public String getTime() {
        return LocalTime.now().toString().substring(0, 8);
    }


    public String getName() {
        return name;
    }

    public Fish createFish() {  //Maybe this should be Fish[] or ArrayList<Fish>
        return null;  //Placeholder
    }

}
