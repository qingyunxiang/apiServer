package moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers;

import com.earth2me.essentials.IEssentials;
import com.earth2me.essentials.User;
import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Controller;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Get;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.HttpCode;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Param;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.HttpStatuCode;
import org.anjocaido.groupmanager.GroupManager;
import org.json.simple.JSONObject;

import java.util.UUID;


@Controller("/player")
public class PlayerController {
    @Get(":uuid/ess/prefix")
    @HttpCode(HttpStatuCode.OK)
    public JSONObject getPrefix(
            Context context,
            @Server org.bukkit.Server server,
            @Param("playerName") String  uuid
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
