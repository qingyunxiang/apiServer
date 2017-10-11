package moe.haozi.qingyunxiang.apiServer.Json;

import java.util.ArrayList;

public class JsonArray {
    public ArrayList<JsonValue> list = new ArrayList<>();

    public JsonArray() {
    }

    public JsonArray put(Integer value){
        list.add(new JsonValue(EJsonValueType.Int, "", value));
        return this;
    }
    public JsonArray put(JsonArray value){
        list.add(new JsonValue(EJsonValueType.Array, "", value));
        return this;
    }
    public JsonArray put(JsonObject value){
        list.add(new JsonValue(EJsonValueType.Object, "", value));
        return this;
    }
    public JsonArray put(String value){
        list.add(new JsonValue(EJsonValueType.String, "", value));
        return this;
    }
    public JsonArray put(Float value){
        list.add(new JsonValue(EJsonValueType.Float, "", value));
        return this;
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if ((i + 1) < list.size()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
