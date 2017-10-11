package moe.haozi.qingyunxiang.apiServer.Json;

import java.util.ArrayList;

public class JsonObject extends JsonArray {
    private ArrayList<JsonValue> list = new ArrayList<>();

    public JsonObject() {
        super();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for(int i = 0; i < list.size(); i++) {
            if( i > 0) {
                sb.append(",");
            }
            sb.append(list.get(i).key);
            sb.append(":");
            sb.append(list.get(i).toString());
        }
        sb.append("}");
        return sb.toString();
    }
}
