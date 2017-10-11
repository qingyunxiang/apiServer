package moe.haozi.qingyunxiang.apiServer.HttpServer;

import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Router;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Server;
import moe.haozi.qingyunxiang.apiServer.Json.JsonArray;
import moe.haozi.qingyunxiang.apiServer.Json.JsonObject;

import java.net.InetSocketAddress;


public class RestfulServer {
    public RestfulServer(org.bukkit.Server bukkit) throws Exception {
        Server server = new Server();
//        Router.Get("/whitelist", (ctx) -> {
//
//        });
        server.use(Router.route());
        server.listen(new InetSocketAddress(8099));
    }
}

