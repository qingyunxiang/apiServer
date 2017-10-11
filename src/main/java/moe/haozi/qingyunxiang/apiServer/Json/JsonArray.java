package moe.haozi.qingyunxiang.apiServer.Json;

import java.util.ArrayList;

public class JsonArray {
    private ArrayList<JsonValue> list = new ArrayList<>();

    public JsonArray() {
    }

    public JsonArray put(String key, Integer value){
        list.add(new JsonValue(EJsonValueType.Int, "", value));
        return this;
    }
    public JsonArray put(String key, JsonArray value){
        list.add(new JsonValue(EJsonValueType.Int, "", value));
        return this;
    }
    public JsonArray put(String key, JsonObject value){
        list.add(new JsonValue(EJsonValueType.Int, "", value));
        return this;
    }
    public JsonArray put(String key, String value){
        list.add(new JsonValue(EJsonValueType.Int, "", value));
        return this;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0; i < list.size(); i++) {
            sb.append(",");
            sb.append(list.get(i).toString());
        }
        sb.append("]");
        return sb.toString();
    }
}
