package moe.haozi.qingyunxiang.apiServer.HttpServer;

import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Router;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Server;
import moe.haozi.qingyunxiang.apiServer.Json.JsonObject;

import java.net.InetSocketAddress;


public class RestfulServer {
    public RestfulServer() throws Exception {
        Server server = new Server();
//        server.use((ctx, next) -> {
//            try {
//                ctx.statu(200);
//                ctx.ContentType("text/plain");
//                ctx.write("hello world".getBytes());
//                ctx.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        Router.Get("/whitelist", (ctx) -> {
            System.out.println("Url -> ");
            System.out.println(ctx.httpExchange.getRequestURI());
            try {
                ctx.statu(200);
                ctx.ContentType("text/plain");
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("url", ctx.url().getPath());
                ctx.write(jsonObject.toString().getBytes());
                ctx.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        server.use(Router.route());
        server.listen(new InetSocketAddress(8099));
    }
}

