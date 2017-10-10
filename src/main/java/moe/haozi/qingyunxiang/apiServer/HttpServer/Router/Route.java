package moe.haozi.qingyunxiang.apiServer.HttpServer.Router;

import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;

import java.util.function.Consumer;

public class Route {
    public String path;
    public Consumer<Context> callback;
    public int order  = 0;
    public String method = "Get";

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

    public Boolean exec(Context ctx) {
        this.callback.accept(ctx);
        return true;
    }
}
