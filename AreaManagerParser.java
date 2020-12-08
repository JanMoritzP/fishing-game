import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import netscape.javascript.JSObject;

public class AreaManagerParser {

    //PLACEHOLDER CODE

    private ArrayList<String> areaNameList = new ArrayList<String>();
    private ArrayList<Double> percentageList = new ArrayList<Double>();

    private FishParser fishParser = new FishParser();
    private AreaParser areaParser = new AreaParser();

    public ArrayList<String> getAreaFishList(String name) {
        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/AreaManager.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("areaManager");
        JSONObject tempFishObjectArray;

        String tempName = "";

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            tempFishObjectArray = itr2.next();
            tempName = (String) tempFishObjectArray.get("name");
            if(name.equals(tempName)) {
                JSONArray fishObjArray = (JSONArray) tempFishObjectArray.get("fish");
                Iterator<JSONObject> fishObjIterator = fishObjArray.iterator();
                while(fishObjIterator.hasNext()) {
                    areaNameList.add((String) fishObjIterator.next().get("name"));
                }
            }
        }        
        return areaNameList;
    }

    public ArrayList<String> getAvailableFishList(String name) {
        ArrayList<Fish> fishList = fishParser.getFishList();
        Iterator<Fish> fishIterator = fishList.iterator();

        ArrayList<String> areaFishNames = getAreaFishList(name);

        while(fishIterator.hasNext()) {
            if(areaFishNames.contains(fishIterator.next().getName())) {
                fishIterator.remove();
            }
        }
        
        fishIterator = fishList.iterator();
        ArrayList<String> availableFishNames = new ArrayList<String>();

        while(fishIterator.hasNext()) {
            availableFishNames.add(fishIterator.next().getName());
        }

        return availableFishNames;
    }

    public ArrayList<Double> getPercentageList(String name) {
        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/AreaManager.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("areaManager");
        JSONObject tempFishObjectArray;

        String tempName = "";

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            tempFishObjectArray = itr2.next();
            tempName = (String) tempFishObjectArray.get("name");
            if(name.equals(tempName)) {
                JSONArray fishObjArray = (JSONArray) tempFishObjectArray.get("fish");
                Iterator<JSONObject> fishObjIterator = fishObjArray.iterator();
                while(fishObjIterator.hasNext()) {
                    percentageList.add((Double) fishObjIterator.next().get("percentage"));
                }
            }
        }        
        return percentageList;
    }

    public void addObject(String name, double percentage) {  
        fishList = fishParser.getFishList();

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
        fishList = fishParser.getFishList();
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

    private ArrayList<String> fishToName(ArrayList<Fish> inputList) {
        ArrayList<String> outputList = new ArrayList<String>();
        Iterator<Fish> fishIterator = inputList.iterator();
        while(fishIterator.hasNext()) {
            outputList.add(fishIterator.next().getName());
        }
        return outputList;
    }
}
