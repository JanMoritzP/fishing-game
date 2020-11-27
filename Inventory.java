import java.util.ArrayList;

public class Inventory {

    private int inventorySize;
    private double money;

    private ArrayList<Fish> fishArr;
    private ArrayList<Rod> rodArr;
    private ArrayList<Bait> baitArr;
    private ArrayList<Integer> baitAmountArr;

    public Inventory() {
        this.inventorySize = 15;
        this.money = 0;
    }

    public void addBait(Bait bait) {
        if(baitArr.contains(bait)) {
            baitAmountArr.set(baitArr.indexOf(bait), baitAmountArr.get(baitArr.indexOf(bait)) + 1);
        }
        else {
            baitArr.add(bait);
            baitAmountArr.add(1);
        }
    }

    public void useBait(Bait bait) {
        baitAmountArr.set(baitArr.indexOf(bait), baitAmountArr.get(baitArr.indexOf(bait)) - 1);
    }

    public ArrayList<Integer> getBaitAmounts() {
        return baitAmountArr;
    }

    public ArrayList<Bait> getBaitList() {
        return baitArr;
    }


    public void addRod(Rod rod) {
        if(rodArr.contains(rod)) {
            System.out.println("No need to add an existing rod. This particular rod should not be purchasable!");
        }
        else {
            rodArr.add(rod);
        }
    }    

    public void addFishToInventory(Fish fish) {
        fishArr.add(fish);
    }

    public int getInventoryFishAmount() {
        //return InventoryFishAmount 
        return fishArr.size();
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
