package moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers;

import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.*;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.HttpStatuCode;
import org.json.simple.JSONObject;

import java.util.UUID;


@Controller("/player")
public class PlayerController {
    @Post("/:uuid/ess/prefix")
    @HttpCode(HttpStatuCode.OK)
    public JSONObject setPrefix(
            Context context,
            @Server org.bukkit.Server server,
            @Param("uuid") String  uuid
    ) {
        JSONObject jsonObject = new JSONObject();
        String name = server.getPlayer(UUID.fromString(uuid)).getName();
        server.dispatchCommand(server.getConsoleSender(), "/manuaddv " +
                name + " prefix " + context.body.get("prefix"));
        jsonObject.put("uuid", uuid);
        jsonObject.put("prefix", context.body.get("prefix"));
        jsonObject.put("name", name);
        return jsonObject;
    }
}
