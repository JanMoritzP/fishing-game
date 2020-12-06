public class Hook {
    
    private int size;
    private double durability; //This ranges from size*10 to 0
    private String name;

    public Hook(String name, int size) {
        this.name = name;
        this.size = size;
        this.durability = size*10;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void useHook() {
        durability -= 10;
    }

    public double getDurability() {
        return durability;
    }

}
