import java.util.ArrayList;

public class CreateLiveFish implements Runnable {

    private ArrayList<Fish> liveFish;
    private ArrayList<int[]> fishPosition;
    private Mutable<Boolean> fishCreated;

    public CreateLiveFish(ArrayList<Fish> liveFish, ArrayList<int[]> fishPosition, Mutable<Boolean> fishCreated) {
        this.liveFish = liveFish;
        this.fishPosition = fishPosition;
        this.fishCreated = fishCreated;
    }

    @Override
    public void run() {
        while (liveFish.size() < 5 && fishCreated.getVariable()) { //Will create 5 fish
            if (Math.random() < 0.1) {
                liveFish.add(new Fish("Trout", 5, 5));
                int xOffset = (int) (Math.random() * 800);
                int yOffset = (int) (Math.random() * 800);
                int[] position = {50 + xOffset,50 + yOffset};
                fishPosition.add(position);
            }
            try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();} //time in ms

        }
    
    }
}
