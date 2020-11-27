public class Hook {
    
    private int size;
    private double durability; //This ranges from size*10 to 0

    public Hook(int size) {
        this.size = size;
        this.durability = size*10;
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
