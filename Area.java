import java.time.LocalTime;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Area {

    private String name;

    public Area(String name) {
        this.name = name;
    }

    public String getTime() {
        return LocalTime.now().toString().substring(0, 8);
    }

    public String getName() {
        return name;
    }

    public Fish createFish() throws FileNotFoundException, IOException, ParseException { // Maybe this should be Fish[] or ArrayList<Fish>

        JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader("scr/Fish.json"));
        JSONArray ja = (JSONArray) jo.get("fish");
        Iterator itr2 = ja.iterator();
        

        

        return null;  //Placeholder
    }

}
