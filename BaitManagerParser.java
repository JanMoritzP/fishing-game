import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BaitManagerParser {

    public ArrayList<String> getBaitNameList(String name) {
        ArrayList<String> baitNameList = new ArrayList<String>();
        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/BaitManager.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("baitManager");
        JSONObject tempFishObjectArray;

        String tempName = "";

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            tempFishObjectArray = itr2.next();
            tempName = (String) tempFishObjectArray.get("fish");
            if(name.equals(tempName)) {
                JSONArray fishObjArray = (JSONArray) tempFishObjectArray.get("bait");
                Iterator<JSONObject> fishObjIterator = fishObjArray.iterator();
                while(fishObjIterator.hasNext()) {
                    baitNameList.add((String) fishObjIterator.next().get("name"));
                }
            }
        }        
        return baitNameList;
    }

    public ArrayList<String> getAvailableFishList(String name) {
        FishParser fishParser = new FishParser();
        ArrayList<Fish> fishList = fishParser.getFishList();
        Iterator<Fish> fishIterator = fishList.iterator();

        ArrayList<String> baitFishNames = getBaitNameList(name);

        while(fishIterator.hasNext()) {
            if(baitFishNames.contains(fishIterator.next().getName())) {
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
        ArrayList<Double> percentageList = new ArrayList<Double>();
        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/BaitManager.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("baitManager");
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

    public void addObject(String name, double percentage, String bait) {
        JSONObject jo = new JSONObject();
        JSONArray JSONBaitList = new JSONArray();
        JSONArray JSONFishList = new JSONArray();
        JSONObject fish = new JSONObject();
        JSONObject baitObject = new JSONObject();

        BaitParser baitParser = new BaitParser();
        ArrayList<Bait> baitList = baitParser.getBaitList();
        Iterator<Bait> baitIterator = baitList.iterator();
        
        Bait tempBait;
        ArrayList<String> fishList;
        ArrayList<Double> percentageList;
        Iterator<String> fishIterator;
        Iterator<Double> percentageIterator;
        while(baitIterator.hasNext()) {
            tempBait = baitIterator.next();
            if(tempBait.getName().equals(bait)) {
                fishList = getBaitNameList(tempBait.getName());
                percentageList = getPercentageList(tempBait.getName());
                fishList.add(name);
                percentageList.add(percentage);
                percentageIterator = percentageList.iterator();
                fishIterator = fishList.iterator();
                while(fishIterator.hasNext()) {
                    fish.put("name", fishIterator.next());
                    fish.put("percentage", percentageIterator.next());
                    JSONFishList.add(fish);
                    fish = new JSONObject();
                }  
            }
            else {
                fishList = getBaitNameList(tempBait.getName());
                percentageList = getPercentageList(tempBait.getName());
                fishIterator = fishList.iterator();
                percentageIterator = percentageList.iterator();
                while(fishIterator.hasNext()) {
                    fish.put("name", fishIterator.next());
                    fish.put("percentage", percentageIterator.next());
                    JSONFishList.add(fish);
                    fish = new JSONObject();
                }  
            }
            baitObject.put("name", tempBait.getName());
            baitObject.put("fish", JSONFishList);
            JSONBaitList.add(baitObject);
            JSONFishList = new JSONArray();
            baitObject = new JSONObject();
        }
        jo.put("baitManager", JSONBaitList);
        
        try (FileWriter file = new FileWriter("src/BaitManager.json", false)) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeObject(int index, String bait) {
        JSONObject jo = new JSONObject();
        JSONArray JSONBaitList = new JSONArray();
        JSONArray JSONFishList = new JSONArray();
        JSONObject fish = new JSONObject();
        JSONObject baitObject = new JSONObject();

        BaitParser baitParser = new BaitParser();
        ArrayList<Bait> baitList = baitParser.getBaitList();
        Iterator<Bait> baitIterator = baitList.iterator();
        
        
        ArrayList<String> fishList;
        ArrayList<Double> percentageList;
        Iterator<String> fishIterator;
        Iterator<Double> percentageIterator;
        Bait tempBait;
        while(baitIterator.hasNext()) {
            tempBait = baitIterator.next();
            if(tempBait.getName().equals(bait)) {
                fishList = getBaitNameList(tempBait.getName());
                percentageList = getPercentageList(tempBait.getName());
                fishList.remove(index);
                percentageList.remove(index);
                fishIterator = fishList.iterator();
                percentageIterator = percentageList.iterator();
                while(fishIterator.hasNext()) {
                    fish.put("name", fishIterator.next());
                    fish.put("percentage", percentageIterator.next());
                    JSONFishList.add(fish);
                    fish = new JSONObject();
                }  
            }
            else {
                fishList = getBaitNameList(tempBait.getName());
                percentageList = getPercentageList(tempBait.getName());
                fishIterator = fishList.iterator();
                percentageIterator = percentageList.iterator();
                while(fishIterator.hasNext()) {
                    fish.put("name", fishIterator.next());
                    fish.put("percentage", percentageIterator.next());
                    JSONFishList.add(fish);
                    fish = new JSONObject();
                }  
            }
            baitObject.put("name", tempBait.getName());
            baitObject.put("fish", JSONFishList);
            JSONBaitList.add(baitObject);
            JSONFishList = new JSONArray();
            baitObject = new JSONObject();
        }
        jo.put("baitManager", JSONBaitList);
        
        try (FileWriter file = new FileWriter("src/BaitManager.json", false)) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initializeBait() {
        BaitParser baitParser = new BaitParser();
        ArrayList<Bait> realBaitList = baitParser.getBaitList();
        Iterator<Bait> realBaitListIterator = realBaitList.iterator();
        ArrayList<String> realBaitListStr= new ArrayList<String>();
        ArrayList<String> baitListStr= new ArrayList<String>();
        while(realBaitListIterator.hasNext()) {
            realBaitListStr.add(realBaitListIterator.next().getName());
        }
        //Now get BaitNames in BaitManager
        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/BaitManager.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("baitManager");

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            baitListStr.add((String) itr2.next().get("name"));
        }

        Iterator<String> realBaitStrIterator = realBaitListStr.iterator();
        String tempBait;
        while(realBaitStrIterator.hasNext()) {
            tempBait = realBaitStrIterator.next();
            if(baitListStr.contains(tempBait) == false) {
                addObject("DefaultFish", 1, tempBait);
            }
        }
           
    }

}
