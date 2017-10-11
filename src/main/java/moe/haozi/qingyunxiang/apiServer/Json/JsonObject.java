package moe.haozi.qingyunxiang.apiServer.Json;

public class JsonObject extends JsonArray {
    public JsonObject() {
        super();
    }
    public JsonArray put(String key, Integer value){
        list.add(new JsonValue(EJsonValueType.Int, key, value));
        return this;
    }
    public JsonArray put(String key, JsonArray value){
        list.add(new JsonValue(EJsonValueType.Array, key, value));
        return this;
    }
    public JsonArray put(String key, JsonObject value){
        list.add(new JsonValue(EJsonValueType.Object, key, value));
        return this;
    }
    public JsonArray put(String key, String value){
        list.add(new JsonValue(EJsonValueType.String, key, value));
        return this;
    }
    public JsonArray put(String key, Float value){
        list.add(new JsonValue(EJsonValueType.Float, key, value));
        return this;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).key);
            sb.append(":");
            sb.append(list.get(i).toString());
            if ((i + 1) < list.size()) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
