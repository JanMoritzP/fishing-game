import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BaitParser {

    private ArrayList<Fish> fishList;

    public ArrayList<Fish> getFishList() {
        fishList = new ArrayList<Fish>();

        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/Fish.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("fish");
        JSONObject tempFishObject;

        String tempName = "";
        double tempSize = 0;
        double tempValue = 0;
        Fish tempFish = null;

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            tempFishObject = itr2.next();
            tempName = (String) tempFishObject.get("name");
            tempSize = (Double) tempFishObject.get("size");
            tempValue = (Double) tempFishObject.get("value");
            tempFish = new Fish(tempName, tempSize, tempValue);
            fishList.add(tempFish);
        }
        
        return fishList;
    }

    public Fish createFish(ArrayList<String> names, ArrayList<Double> percentages) {
        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/Fish.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("fish");
        JSONObject tempFishObject;

        double random = Math.random();
        
        Iterator<JSONObject> itr2 = ja.iterator();
        int index;
        double percOffset = 0;
        String fishName;
        double fishSize;
        double fishValue;
        
        while(itr2.hasNext()) {
            tempFishObject = itr2.next();
            if(names.contains(tempFishObject.get("name"))) {
                index = names.indexOf(tempFishObject.get("name"));
                if(percentages.get(index) + percOffset >= random) {     //Create Fish
                    fishName = (String) tempFishObject.get("name");
                    fishSize = (Double) tempFishObject.get("size");
                    fishValue = (Double) tempFishObject.get("value");
                    random = Math.random();
                    fishSize = fishSize * 0.4 * (random - 0.5) + fishSize;
                    fishValue = fishValue * 0.4 * (random - 0.5) + fishValue;
                    return new Fish(fishName, fishSize, fishValue);
                }
                else {
                    percOffset += percentages.get(index);
                }
            }
        }

        return null;
    }

    public void addObject(String name, double size, double value) {  
        fishList = getFishList();

        JSONObject jo = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject fish = new JSONObject();

        Iterator<Fish> fishIterator = fishList.iterator();
        Fish tempFish;
        while(fishIterator.hasNext()) {
            tempFish = fishIterator.next();
            fish.put("name", tempFish.getName());
            fish.put("size", tempFish.getSize());
            fish.put("value", tempFish.getValue());
            list.add(fish);
            fish = new JSONObject();
        }
        
        fish.put("name", name);
        fish.put("size", size);
        fish.put("value", value);
        list.add(fish);

        jo.put("fish", list);
        
        try (FileWriter file = new FileWriter("src/Fish.json")) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void removeObject(int index) {
        fishList = getFishList();
        fishList.remove(index);
        JSONObject jo = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject fish = new JSONObject();

        Iterator<Fish> fishIterator = fishList.iterator();
        Fish tempFish;
        while(fishIterator.hasNext()) {
            tempFish = fishIterator.next();
            fish.put("name", tempFish.getName());
            fish.put("size", tempFish.getSize());
            fish.put("value", tempFish.getValue());
            list.add(fish);
            fish = new JSONObject();
        }
        jo.put("fish", list);
        
        try (FileWriter file = new FileWriter("src/Fish.json")) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
