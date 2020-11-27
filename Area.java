import java.time.LocalTime;

public class Area {

    private String name;
    private int time;   //Values from 0 to 100 where 0 == 100 -> Night and 50 -> Day || Maybe we can use some kind of clock graphic?


    public Area(String name) {
        this.name = name;
        this.time = 0;
    }

    public String getTime() {
        return LocalTime.now().toString().substring(0, 8);
    }


    public String getName() {
        return name;
    }

    public Fish createFish() {  //Maybe this should be Fish[] or ArrayList<Fish>
        if(name == "Wyoming Lake") {
            Fish returnFish = new Fish("Trout", 6, 20); 
            return returnFish;

        }
        else if(name == "Area2") {
            return null;
        }
        else if(name == "Area3") {
            return null;
        }
        return null;
    }

}
