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

    public ArrayList<String> getAvailableBaitList(String name) {
        BaitParser baitParser = new BaitParser();
        ArrayList<Bait> baitList = baitParser.getBaitList();
        Iterator<Bait> baitIterator = baitList.iterator();

        ArrayList<String> baitNames = getBaitNameList(name);

        while(baitIterator.hasNext()) {
            if(baitNames.contains(baitIterator.next().getName())) {
                baitIterator.remove();
            }
        }
        
        baitIterator = baitList.iterator();
        ArrayList<String> availableBaitNames = new ArrayList<String>();

        while(baitIterator.hasNext()) {
            availableBaitNames.add(baitIterator.next().getName());
        }

        return availableBaitNames;
    }

    public ArrayList<Double> getLikelinessList(String name) {
        ArrayList<Double> likelinessList = new ArrayList<Double>();
        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/BaitManager.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("baitManager");
        JSONObject tempBaitObjectArray;

        String tempName = "";

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            tempBaitObjectArray = itr2.next();
            tempName = (String) tempBaitObjectArray.get("fish");
            if(name.equals(tempName)) {
                JSONArray baitObjArray = (JSONArray) tempBaitObjectArray.get("bait");
                Iterator<JSONObject> baitObjIterator = baitObjArray.iterator();
                while(baitObjIterator.hasNext()) {
                    likelinessList.add((Double) baitObjIterator.next().get("likeliness"));
                }
            }
        }        
        return likelinessList;
    }

    public void addObject(String name, double likeliness, String baitName) {
        JSONObject jo = new JSONObject();
        JSONArray JSONBaitList = new JSONArray();
        JSONArray JSONFishList = new JSONArray();
        JSONObject bait = new JSONObject();
        JSONObject baitObject = new JSONObject();

        BaitParser baitParser = new BaitParser();
        ArrayList<Bait> baitList = baitParser.getBaitList();
        Iterator<Bait> baitIterator = baitList.iterator();
        
        Bait tempBait;
        ArrayList<String> baitNameList;
        ArrayList<Double> likelinessList;
        Iterator<String> baitNameIterator;
        Iterator<Double> likelinessIterator;
        while(baitIterator.hasNext()) {
            tempBait = baitIterator.next();
            if(tempBait.getName().equals(baitName)) {
                baitNameList = getBaitNameList(tempBait.getName());
                likelinessList = getLikelinessList(tempBait.getName());
                baitNameList.add(name);
                likelinessList.add(likeliness);
                likelinessIterator = likelinessList.iterator();
                baitNameIterator = baitNameList.iterator();
                while(baitNameIterator.hasNext()) {
                    bait.put("name", baitNameIterator.next());
                    bait.put("likeliness", likelinessIterator.next());
                    JSONFishList.add(bait);
                    bait = new JSONObject();
                }  
            }
            else {
                baitNameList = getBaitNameList(tempBait.getName());
                likelinessList = getLikelinessList(tempBait.getName());
                baitNameIterator = baitNameList.iterator();
                likelinessIterator = likelinessList.iterator();
                while(baitNameIterator.hasNext()) {
                    bait.put("name", baitNameIterator.next());
                    bait.put("likeliness", likelinessIterator.next());
                    JSONFishList.add(bait);
                    bait = new JSONObject();
                }  
            }
            baitObject.put("fish", tempBait.getName());
            baitObject.put("bait", JSONFishList);
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

    public void removeObject(int index, String baitName) {
        JSONObject jo = new JSONObject();
        JSONArray JSONBaitList = new JSONArray();
        JSONArray JSONFishList = new JSONArray();
        JSONObject bait = new JSONObject();
        JSONObject baitObject = new JSONObject();

        BaitParser baitParser = new BaitParser();
        ArrayList<Bait> baitList = baitParser.getBaitList();
        Iterator<Bait> baitIterator = baitList.iterator();
        
        
        ArrayList<String> baitNameList;
        ArrayList<Double> likelinessList;
        Iterator<String> baitNameIterator;
        Iterator<Double> likelinessIterator;
        Bait tempBait;
        while(baitIterator.hasNext()) {
            tempBait = baitIterator.next();
            if(tempBait.getName().equals(baitName)) {
                baitNameList = getBaitNameList(tempBait.getName());
                likelinessList = getLikelinessList(tempBait.getName());
                baitNameList.remove(index);
                likelinessList.remove(index);
                baitNameIterator = baitNameList.iterator();
                likelinessIterator = likelinessList.iterator();
                while(baitNameIterator.hasNext()) {
                    bait.put("name", baitNameIterator.next());
                    bait.put("likeliness", likelinessIterator.next());
                    JSONFishList.add(bait);
                    bait = new JSONObject();
                }  
            }
            else {
                baitNameList = getBaitNameList(tempBait.getName());
                likelinessList = getLikelinessList(tempBait.getName());
                baitNameIterator = baitNameList.iterator();
                likelinessIterator = likelinessList.iterator();
                while(baitNameIterator.hasNext()) {
                    bait.put("name", baitNameIterator.next());
                    bait.put("likeliness", likelinessIterator.next());
                    JSONFishList.add(bait);
                    bait = new JSONObject();
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

    public void initializeFish() {
        FishParser baitParser = new FishParser();
        ArrayList<Fish> realFishList = baitParser.getFishList();
        Iterator<Fish> realFishListIterator = realFishList.iterator();
        ArrayList<String> realFishListStr= new ArrayList<String>();
        ArrayList<String> fishNameList = new ArrayList<String>();
        while(realFishListIterator.hasNext()) {
            realFishListStr.add(realFishListIterator.next().getName());
        }
        //Now get FishNames in FishManager
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
            fishNameList.add((String) itr2.next().get("fish"));
        }

        Iterator<String> realFishStrIterator = realFishListStr.iterator();
        String tempBait;
        while(realFishStrIterator.hasNext()) {
            tempBait = realFishStrIterator.next();
            if(fishNameList.contains(tempBait) == false) {
                addObject("DefaultBait", 1, tempBait);
            }
        }
           
    }

}
