package moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers.Player;

import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.*;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.HttpStatuCode;
import org.json.simple.JSONObject;

import java.util.UUID;

@Controller("/player/:uuid/ess")
public class EssController {

    /**
     * 设置 玩家头衔
     * @param context
     * @param server
     * @param uuid
     * @return
     */
    @Post("/prefix")
    @HttpCode(HttpStatuCode.OK)
    public JSONObject setPrefix(
            Context context,
            @Server org.bukkit.Server server,
            @Param("uuid") String  uuid
    ) {
        JSONObject jsonObject = new JSONObject();
        String name = server.getPlayer(UUID.fromString(uuid)).getName();
        server.dispatchCommand(server.getConsoleSender(), "manuaddv " +
                name + " prefix " + context.body.get("prefix"));
        jsonObject.put("uuid", uuid);
        jsonObject.put("prefix", context.body.get("prefix"));
        jsonObject.put("name", name);
        return jsonObject;
    }
}
