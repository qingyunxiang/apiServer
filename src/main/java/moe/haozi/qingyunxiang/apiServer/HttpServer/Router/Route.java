package moe.haozi.qingyunxiang.apiServer.HttpServer.Router;

import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.Tools.PathRegex;
import org.bukkit.Bukkit;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

public class Route {
    public String path;
    public Consumer<Context> callback;
    public int order  = 0;
    public Context.HttpMethod method = Context.HttpMethod.GET;

    public LinkedHashMap<Annotation, String> parmaList = new LinkedHashMap<>();
    public LinkedHashMap<Annotation, Object> parmaListValue = new LinkedHashMap<>();
    public int defaultHttpCode = 200;
    public String defaultResText = "hello world";
    public PathRegex pathRegex ;
    public Class<?> returnType;

    public Route addParma(Annotation annotation, String key) {
        parmaList.put(annotation, key);
        return this;
    }

    public void setPath(String path) {
        this.path = path;
        Bukkit.getLogger().info("path ->" + path);
        pathRegex = new PathRegex(path);
    }

    public void setCallback(Consumer<Context> callback) {
        this.callback = callback;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Route addParma(Annotation annotation, Object key) {
        parmaList.put(annotation, null);
        parmaListValue.put(annotation, key);
        return this;
    }

    public Boolean exec(Context ctx) {
        ctx.route = this;
        if(!ctx.route.pathRegex.exec(ctx.url().getPath()) || ctx.method != method) {
            return false;
        }

        this.callback.accept(ctx);
        return true;
    }

    public String getParma(Context ctx, String key) {
        return pathRegex.getKey(ctx.url().getPath(), key);
    }

    public String getQuery(Context ctx, String key) {
        return ctx.Query.get(key);
    }

    public Route method(Context.HttpMethod method) {
        this.method = method;
        return  this;
    }

    public Route setHttpCode(int code) {
        this.defaultHttpCode = code;
        return this;
    }
}
