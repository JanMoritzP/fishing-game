import java.util.ArrayList;
import java.util.Iterator;

public class TestBackend {
    public static void main(String[] args) {
        BaitParser baitParser = new BaitParser();
        ArrayList<Bait> baitList = baitParser.getBaitList();
        Iterator<Bait> baitIterator = baitList.iterator();
        while(baitIterator.hasNext()) {
            System.out.println(baitIterator.next().getName());
        }
        BaitManagerParser baitManagerParser = new BaitManagerParser();
        System.out.println(baitManagerParser.getLikelinessList("Trout"));
    }
}
