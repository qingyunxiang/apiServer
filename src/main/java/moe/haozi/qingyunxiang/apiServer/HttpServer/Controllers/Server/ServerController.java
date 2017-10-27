package moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers.Server;

import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Controller;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Get;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.HttpCode;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Path;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import org.json.simple.*;

@Controller()
public class ServerController {
    @Get
    @Path("/whitelist")
    @HttpCode(200)
    public void getWhiteList(Context ctx, @Server() org.bukkit.Server server) {
        System.out.println("Url -> ");
        System.out.println(ctx.httpExchange.getRequestURI());
        try {
            ctx.statu(200);
            ctx.ContentType("application/json");
            JSONObject jb = new JSONObject();
            jb.put("url", ctx.url().getPath());
            JSONArray jr = new JSONArray();
            server.getWhitelistedPlayers().forEach(player -> {
                jr.add(player.getName());
            });
            jb.put("user", jr);
            jb.put("total", jr.size());
            ctx.write(jb.toJSONString().getBytes());
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
