public class Area {

    private String name;
    private int time;   //Values from 0 to 100 where 0 == 100 -> Night and 50 -> Day || Maybe we can use some kind of clock graphic?


    public Area(String name) {
        this.name = name;
        this.time = 0;
    }

    public String getTime() {
        return "Placeholder for time decoding";
    }


    public String getName() {
        return name;
    }

    public Fish createFish() {  //Maybe this should be Fish[] or ArrayList<Fish>
        return new Fish(null);  //Placeholder
    }





}
