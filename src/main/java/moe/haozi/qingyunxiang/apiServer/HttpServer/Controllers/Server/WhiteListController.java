package moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers.Server;

import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.*;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Error.NotFoundError;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.HttpStatuCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Controller("/whitelist")
public class WhiteListController {

    @Get("")
    @HttpCode(HttpStatuCode.CREATED)
    public JSONObject getWhiteList(Context context, @Server org.bukkit.Server server) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        server.getWhitelistedPlayers().forEach((offlinePlayer -> {
            jsonArray.add(offlinePlayer.getName());
        }));
        jsonObject.put("players", jsonArray.toJSONString());
        return jsonObject;
    }

    @Put("")
    @HttpCode(HttpStatuCode.CREATED)
    public String add (Context context, @Server org.bukkit.Server server) throws NotFoundError {
        server.dispatchCommand(server.getConsoleSender(), "whitelist add " + context.body.get("name"));
        return "add " + context.body.get("name") + "ok~";
    }

    @Delete("/:name")
    @HttpCode(HttpStatuCode.NOCONTENT)
    public String delect (Context context, @Server org.bukkit.Server server, @Param("name") String name) throws NotFoundError {
        server.dispatchCommand(server.getConsoleSender(), "whitelist remove " + name);
        return "delect " + name + "ok~";
    }
}
