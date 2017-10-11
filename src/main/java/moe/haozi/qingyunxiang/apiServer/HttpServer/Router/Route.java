package moe.haozi.qingyunxiang.apiServer.HttpServer.Router;

import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Route {
    public String path;
    public Consumer<Context> callback;
    public int order  = 0;
    public String method = "Get";

    public LinkedHashMap<Route.ParmaType, String> parmaList = new LinkedHashMap<>();
    public int defaultHttpCode = 200;
    public String defaultResText = "hello world";

    public Route(String path) {
        this.path = path;
        this.callback = (a) -> {};
    }

    public Route(String method, String path) {
        this.method = method;
        this.path = path;
        this.callback = (a) -> {};
    }

    public Route(String method, String path, Consumer<Context> callback) {
        this.method = method;
        this.path = path;
        this.callback = callback;
    }

    public Route(String method, String path, Consumer<Context> callback, int order) {
        this.method = method;
        this.path = path;
        this.callback = callback;
        this.order = order;
    }

    public Route addParma(Route.ParmaType type, String key) {
        parmaList.put(type, key);
        return this;
    }

    public Route addParma(Route.ParmaType type, Object value) {
        parmaList.put(type, value);
        return this;
    }

    public static enum ParmaType {
        Server,
        Query,
        Parma
    }

    public Boolean exec(Context ctx) {
        this.callback.accept(ctx);
        return true;
    }
}
