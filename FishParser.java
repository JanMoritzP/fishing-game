import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FishParser {

    private ArrayList<Fish> fishList = new ArrayList<Fish>();

    public ArrayList<Fish> getFishList() {
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
        int tempSize = 0;
        int tempValue = 0;
        Fish tempFish = null;

        Iterator<JSONObject> itr2 = ja.iterator();
        while(itr2.hasNext()) {
            tempFishObject = itr2.next();
            tempName = (String) tempFishObject.get("name");
            tempSize = ((Long) tempFishObject.get("size")).intValue();
            tempValue = ((Long) tempFishObject.get("value")).intValue();
            tempFish = new Fish(tempName, tempSize, tempValue);
            fishList.add(tempFish);
        }
        
        return fishList;
    }
}
