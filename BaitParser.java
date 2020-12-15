import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BaitParser {

    private ArrayList<Bait> baitList;

    public ArrayList<Bait> getBaitList() {
        baitList = new ArrayList<Bait>();

        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/Bait.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("bait");
        JSONObject tempBaitObject;

        String tempName = "";
        double tempPrice = 0;
        Bait tempBait = null;

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            tempBaitObject = itr2.next();
            tempName = (String) tempBaitObject.get("name");
            tempPrice =  (Double) tempBaitObject.get("price");
            tempBait = new Bait(tempName, tempPrice);
            baitList.add(tempBait);
        }
        
        return baitList;
    }

    public void addObject(String name, double price) {  
        baitList = getBaitList();

        JSONObject jo = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject bait = new JSONObject();

        Iterator<Bait> baitIterator = baitList.iterator();
        Bait tempBait;
        while(baitIterator.hasNext()) {
            tempBait = baitIterator.next();
            bait.put("name", tempBait.getName());
            bait.put("price", tempBait.getPrice());
            list.add(bait);
            bait = new JSONObject();
        }
        
        bait.put("name", name);
        bait.put("price", price);
        list.add(bait);

        jo.put("bait", list);
        
        try (FileWriter file = new FileWriter("src/Bait.json")) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void removeObject(int index) {
        baitList = getBaitList();
        baitList.remove(index);
        JSONObject jo = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject bait = new JSONObject();

        Iterator<Bait> baitIterator = baitList.iterator();
        Bait tempBait;
        while(baitIterator.hasNext()) {
            tempBait = baitIterator.next();
            bait.put("name", tempBait.getName());
            bait.put("price", tempBait.getPrice());
            list.add(bait);
            bait = new JSONObject();
        }
        jo.put("bait", list);
        
        try (FileWriter file = new FileWriter("src/Bait.json")) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
