package moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers.Server;

import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Get;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Path;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Query;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.Json.JsonArray;
import moe.haozi.qingyunxiang.apiServer.Json.JsonObject;

public class ServerController {
    @Get
    @Path("/whitelist")
    public void getWhiteList(Context ctx, @Server() org.bukkit.Server server, @Query("a") String a) {
        System.out.println("Url -> ");
        System.out.println(ctx.httpExchange.getRequestURI());
        try {
            ctx.statu(200);
            ctx.ContentType("application/json");
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("url", ctx.url().getPath());
            JsonArray jsonArray = new JsonArray();
            server.getWhitelistedPlayers().forEach(player -> {
                jsonArray.put(player.getName());
            });
            jsonObject.put("users", jsonArray);
            ctx.write(jsonObject.toString().getBytes());
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
