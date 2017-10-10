package moe.haozi.qingyunxiang.apiServer.HttpServer.Router;

import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Router {
    static public ArrayList<Route> routes = new ArrayList<>();

    static public BiConsumer<Context, Consumer<String>> route() {
        return (ctx, next) -> {
            for(int i = 0; i < Router.routes.size(); i++) {
                Route route = Router.routes.get(i);
                if (route != null) {
                    route.exec(ctx);
                }
            }
            next.accept(null);
        };
    }

    static public void Get(String path, Consumer<Context> callback) {
        Router.routes.add(new Route("Get", path, callback));
    }
}


