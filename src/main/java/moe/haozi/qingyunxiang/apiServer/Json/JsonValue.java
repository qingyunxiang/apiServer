package moe.haozi.qingyunxiang.apiServer.Json;

public class JsonValue {
    public EJsonValueType type;

    public Object Value;

    public String key = "";

    public JsonValue(EJsonValueType type, String key, Object value) {
        this.type = type;
        this.key = key;
        Value = value;
    }

    @Override
    public String toString() {
        switch (type) {
            case Int:
                return ((Integer)Value).toString();
            case Array:
                return ((JsonArray)Value).toString();
            case Object:
                return ((JsonObject)Value).toString();
            case String:
                return "\"" + Value.toString() + "\"";
            case Float:
                return ((Float)Value).toString();
            default:
                return "????? 这tm是什么东西";
        }
    }
}
