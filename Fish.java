public class Fish {
    private String name;
    private double size;
    private double value;
    
    
    
    public Fish(String name, double size, double value) {
        this.name = name;
        this.size = size;
        this.value = value;
    }   

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

}
