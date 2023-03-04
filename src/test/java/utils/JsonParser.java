package utils;

import data.ListData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private static final JSONParser parser = new JSONParser();
    public static JSONObject config;

    public static void parsing() {
        try {
            config = (JSONObject) parser.parse(new FileReader("src/test/resources/testData.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<ListData> getReminerList() {
        parsing();
        ListData listData1 = new ListData("List1", config.get("List1").toString());
        ListData listData2 = new ListData("List2", config.get("List2").toString());
        ListData listData3 = new ListData("List3", config.get("List3").toString());

        List<ListData> allLists = new ArrayList<>();
        allLists.add(listData1);
        allLists.add(listData2);
        allLists.add(listData3);

        return allLists;
    }


}
