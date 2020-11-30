import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

    private int inventorySize;
    private double money;

    private ArrayList<Fish> fishArray;
    private ArrayList<Rod> rodArray;
    private ArrayList<Bait> baitArray;
    private ArrayList<Integer> baitAmountArray;

    public Inventory() {
        this.inventorySize = 15;
        this.money = 100;
        this.fishArray = new ArrayList<Fish>();
        this.rodArray = new ArrayList<Rod>();
        this.baitArray = new ArrayList<Bait>();
        this.baitAmountArray = new ArrayList<Integer>();
    }

    public void addBait(Bait bait) {
        if(baitArray.contains(bait)) {
            baitAmountArray.set(baitArray.indexOf(bait), baitAmountArray.get(baitArray.indexOf(bait)) + 1);
        }
        else {
            baitArray.add(bait);
            baitAmountArray.add(1);
        }
    }

    public void useBait(Bait bait) {
        baitAmountArray.set(baitArray.indexOf(bait), baitAmountArray.get(baitArray.indexOf(bait)) - 1);
    }

    public ArrayList<Integer> getBaitAmounts() {
        return baitAmountArray;
    }

    public ArrayList<Bait> getBaitList() {
        return baitArray;
    }

    public double sellAllFish() {
        Iterator<Fish> fishIterator = fishArray.iterator();
        double sum = 0;
        while(fishIterator.hasNext()) {
            sum += fishIterator.next().getValue();
        }
        return sum;
    }

    public void addRod(Rod rod) {
        if(rodArray.contains(rod)) {
            System.out.println("No need to add an existing rod. This particular rod should not be purchasable!");
        }
        else {
            rodArray.add(rod);
        }
    }    

    public void addFish(Fish fish) {
        fishArray.add(fish);
    }

    public int getFishAmount() {
        //return InventoryFishAmount 
        return fishArray.size();
    }

    public ArrayList<Fish> getFish() {
        return fishArray;
    }

    public void addMoney(double amount) {
        this.money += amount;
    }

    public double getMoney() {
        return money;
    }
    
    public void useMoney(double amount) {
        this.money -= amount;
    }


}
