import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AreaParser {

    private ArrayList<Area> areaList = new ArrayList<Area>();

    public ArrayList<Area> getAreaList() {
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
}
