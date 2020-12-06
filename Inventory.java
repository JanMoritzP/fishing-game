import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

    private int inventorySize;
    private double money;

    private ArrayList<Fish> fishArray;
    private ArrayList<Rod> rodArray;
    private ArrayList<Bait> baitArray;
    private ArrayList<Integer> baitAmountArray;
    private ArrayList<Hook> hookArray;
    private ArrayList<Integer> hookAmountArray;
    



    public Inventory() {
        this.inventorySize = 15;
        this.money = 100;
        this.fishArray = new ArrayList<Fish>();
        this.rodArray = new ArrayList<Rod>();
        this.baitArray = new ArrayList<Bait>();
        this.baitAmountArray = new ArrayList<Integer>();
        this.hookArray = new ArrayList<Hook>();
        this.hookAmountArray = new ArrayList<Integer>();
    }
    
    //FISH--------------------------------------------------------------------------------------------------------------------------------
    
    public void sellFish(int index) {
        money += fishArray.get(index).getValue();
        fishArray.remove(index);
    }

    public double sellAllFish() {
        Iterator<Fish> fishIterator = fishArray.iterator();
        double sum = 0;
        while(fishIterator.hasNext()) {
            sum += fishIterator.next().getValue();
        }
        fishArray = new ArrayList<Fish>();
        return sum;
    }

    public Boolean checkForFish() {
        if(fishArray.size() != 0) return true;
        else return false;
    }
    
    public void addFish(Fish fish) {
        fishArray.add(fish);
    }

    public int getFishAmount() {
        return fishArray.size();
    }

    public ArrayList<Fish> getFish() {
        return fishArray;
    }

    //BAIT----------------------------------------------------------------------------------------------------------------------------------------------------------------
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

    //MONEY----------------------------------------------------------------------------------------------------------------------------------



    
    
    public void addMoney(double amount) {
        this.money += amount;
    }
    
    public double getMoney() {
        return money;
    }
    
    public void useMoney(double amount) {
        this.money -= amount;
    }

    //MISC--------------------------------------------------------------------------------------------------------------------------------

    public void addRod(Rod rod) {
        if(rodArray.contains(rod)) {
            System.out.println("No need to add an existing rod. This particular rod should not be purchasable!");
        }
        else {
            rodArray.add(rod);
        }
    }    

    public ArrayList<Rod> getRodList() {
        return rodArray;
    }

    public void addHook(Hook hook) {
        if(hookArray.indexOf(hook) != -1) {
            hookAmountArray.set(hookArray.indexOf(hook), hookAmountArray.get(hookArray.indexOf(hook)) + 1);
        }
        else {
            hookArray.add(hook);
            hookAmountArray.add(1);
        }
    }

    public ArrayList<Hook> getHookList() {
        return hookArray;
    }

    public ArrayList<Integer> getHookAmountList() {
        return hookAmountArray;
    }

    public void useHook(Hook hook) {
        hook.useHook();
        if(hook.getDurability() < 0) {
            hookAmountArray.set(hookArray.indexOf(hook), hookAmountArray.get(hookArray.indexOf(hook)) - 1);
        }
        if(hookAmountArray.get(hookArray.indexOf(hook)) < 0) System.out.println("You used a non existent hook you fiend!");
    }
    
}

