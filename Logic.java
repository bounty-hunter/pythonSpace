package trials;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ConcurrentHashMap;

public class JSONParserTest {

    private static ConcurrentHashMap<String, String> paramMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        JSONParserTest obj = new JSONParserTest();
        obj.run();
    }

    //private static final String JSON_MSG = "{\"STARTDATE\": [{\"DEFAULT\": \"val1\",\"VALUE\": \"val2\"}], \"ENDDATE\": [{\"DEFAULT\": \"val3\",\"VALUE\": \"val4\"}]}";
    //private static final String JSON_MSG = "{\"STARTDATE\": [{\"DEFAULT\": \"val1\",\"VALUE\": \"\"}], \"ENDDATE\": [{\"DEFAULT\": \"val3\",\"VALUE\": \"val4\"}]}";
    private static final String JSON_MSG = "{\"STARTDATE\": [{\"DEFAULT\": \"\",\"VALUE\": \"\"}], \"ENDDATE\": [{\"DEFAULT\": \"\",\"VALUE\": \"\"}]}";

    public void run() {
        JSONObject json = new JSONObject(JSON_MSG);
        for(String key : json.keySet()) {
            if (json.get(key) instanceof JSONArray) {
                System.out.println("Resolving report parameter: " + key);
                JSONArray array = (JSONArray)json.get(key);
                String defaultValue = (String)array.getJSONObject(0).get("DEFAULT");
                String value = (String)array.getJSONObject(0).get("VALUE");
                if(StringUtils.isBlank(value))
                    value = defaultValue;
                paramMap.put(key, value);
            } else {
                throw new JSONParsingException("No JSON array present for key: " + key + ". Abandoning parsing process.");
            }
        }
        for(String key : paramMap.keySet())
            System.out.println("Key: " + key + "  :::  Value: " + paramMap.get(key));
    }

}

class JSONParsingException extends IllegalArgumentException {
    private String exceptionMessage;

    public JSONParsingException(String msg) {
        super(msg);
        this.exceptionMessage = msg;
    }

    public String getExceptionMessage() { return exceptionMessage; }
    public void setExceptionMessage(String exceptionMessage) { this.exceptionMessage = exceptionMessage; }
}
