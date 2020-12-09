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



    public ArrayList<String> getAreaFishList(String name) {
        ArrayList<String> areaNameList = new ArrayList<String>();
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
        FishParser fishParser = new FishParser();
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
        ArrayList<Double> percentageList = new ArrayList<Double>();
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

    public void addObject(String name, double percentage, String area) {
        JSONObject jo = new JSONObject();
        JSONArray JSONAreaList = new JSONArray();
        JSONArray JSONFishList = new JSONArray();
        JSONObject fish = new JSONObject();
        JSONObject areaObject = new JSONObject();

        AreaParser areaParser = new AreaParser();
        ArrayList<Area> areaList = areaParser.getAreaList();
        Iterator<Area> areaIterator = areaList.iterator();
        
        Area tempArea;
        ArrayList<String> fishList;
        ArrayList<Double> percentageList;
        Iterator<String> fishIterator;
        Iterator<Double> percentageIterator;
        while(areaIterator.hasNext()) {
            tempArea = areaIterator.next();
            if(tempArea.getName().equals(area)) {
                fishList = getAreaFishList(tempArea.getName());
                percentageList = getPercentageList(tempArea.getName());
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
                fishList = getAreaFishList(tempArea.getName());
                percentageList = getPercentageList(tempArea.getName());
                fishIterator = fishList.iterator();
                percentageIterator = percentageList.iterator();
                while(fishIterator.hasNext()) {
                    fish.put("name", fishIterator.next());
                    fish.put("percentage", percentageIterator.next());
                    JSONFishList.add(fish);
                    fish = new JSONObject();
                }  
            }
            areaObject.put("name", tempArea.getName());
            areaObject.put("fish", JSONFishList);
            JSONAreaList.add(areaObject);
            JSONFishList = new JSONArray();
            areaObject = new JSONObject();
        }
        jo.put("areaManager", JSONAreaList);
        
        try (FileWriter file = new FileWriter("src/AreaManager.json", false)) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeObject(int index, String area) {
        JSONObject jo = new JSONObject();
        JSONArray JSONAreaList = new JSONArray();
        JSONArray JSONFishList = new JSONArray();
        JSONObject fish = new JSONObject();
        JSONObject areaObject = new JSONObject();

        AreaParser areaParser = new AreaParser();
        ArrayList<Area> areaList = areaParser.getAreaList();
        Iterator<Area> areaIterator = areaList.iterator();
        
        
        ArrayList<String> fishList;
        ArrayList<Double> percentageList;
        Iterator<String> fishIterator;
        Iterator<Double> percentageIterator;
        Area tempArea;
        while(areaIterator.hasNext()) {
            tempArea = areaIterator.next();
            if(tempArea.getName().equals(area)) {
                fishList = getAreaFishList(tempArea.getName());
                percentageList = getPercentageList(tempArea.getName());
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
                fishList = getAreaFishList(tempArea.getName());
                percentageList = getPercentageList(tempArea.getName());
                fishIterator = fishList.iterator();
                percentageIterator = percentageList.iterator();
                while(fishIterator.hasNext()) {
                    fish.put("name", fishIterator.next());
                    fish.put("percentage", percentageIterator.next());
                    JSONFishList.add(fish);
                    fish = new JSONObject();
                }  
            }
            areaObject.put("name", tempArea.getName());
            areaObject.put("fish", JSONFishList);
            JSONAreaList.add(areaObject);
            JSONFishList = new JSONArray();
            areaObject = new JSONObject();
        }
        jo.put("areaManager", JSONAreaList);
        
        try (FileWriter file = new FileWriter("src/AreaManager.json", false)) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initializeArea() {
        AreaParser areaParser = new AreaParser();
        ArrayList<Area> realAreaList = areaParser.getAreaList();
        Iterator<Area> realAreaListIterator = realAreaList.iterator();
        ArrayList<String> realAreaListStr= new ArrayList<String>();
        ArrayList<String> areaListStr= new ArrayList<String>();
        while(realAreaListIterator.hasNext()) {
            realAreaListStr.add(realAreaListIterator.next().getName());
        }
        //Now get AreaNames in AreaManager
        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/AreaManager.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("areaManager");

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            areaListStr.add((String) itr2.next().get("name"));
        }

        Iterator<String> realAreaStrIterator = realAreaListStr.iterator();
        String tempArea;
        while(realAreaStrIterator.hasNext()) {
            tempArea = realAreaStrIterator.next();
            if(areaListStr.contains(tempArea) == false) {
                addObject("DefaultFish", 1, tempArea);
            }
        }
           
    }

}
