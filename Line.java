public class Line {
    private double strength;
    private int cost;
    private String name;
    
    
    public Line(double strength, int cost, String name) {
        this.strength = strength;
        this.cost = cost;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCost() {
        return cost;
    }

    public double getStrength() {
        return strength;
    }

}
