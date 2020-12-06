public class Rod {
    private String baseName;
    private int level;
    
    public Rod(String name, int level) {
        this.baseName = name;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getFullName() {
        return baseName + String.format(" Level %d", level);
    }

    public String getName() {
        return baseName;
    }

}
