import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AreaParser {

    private ArrayList<Area> areaList;

    public ArrayList<Area> getAreaList() {
        areaList = new ArrayList<Area>();

        JSONParser parser = new JSONParser();

        Object object = null;
        try {
            object = parser.parse(new FileReader("src/Area.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) object;        
        JSONArray ja = (JSONArray) jo.get("area");
        JSONObject tempAreaObject;

        String tempName = "";
        int tempPrice = 0;
        Area tempArea = null;

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            tempAreaObject = itr2.next();
            tempName = (String) tempAreaObject.get("name");
            tempPrice = ((Long) tempAreaObject.get("price")).intValue();
            tempArea = new Area(tempName, tempPrice);
            areaList.add(tempArea);
        }
        
        return areaList;
    }

    public void addObject(String name, int price) {  
        areaList = getAreaList();

        JSONObject jo = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject area = new JSONObject();

        Iterator<Area> areaIterator = areaList.iterator();
        Area tempArea;
        while(areaIterator.hasNext()) {
            tempArea = areaIterator.next();
            area.put("name", tempArea.getName());
            area.put("price", tempArea.getPrice());
            list.add(area);
            area = new JSONObject();
        }
        
        area.put("name", name);
        area.put("price", price);
        list.add(area);

        jo.put("area", list);
        
        try (FileWriter file = new FileWriter("src/Area.json")) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void removeObject(int index) {
        areaList = getAreaList();
        areaList.remove(index);
        JSONObject jo = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject area = new JSONObject();

        Iterator<Area> areaIterator = areaList.iterator();
        Area tempArea;
        while(areaIterator.hasNext()) {
            tempArea = areaIterator.next();
            area.put("name", tempArea.getName());
            area.put("price", tempArea.getPrice());
            list.add(area);
            area = new JSONObject();
        }
        jo.put("area", list);
        
        try (FileWriter file = new FileWriter("src/Area.json")) {
            file.write(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
