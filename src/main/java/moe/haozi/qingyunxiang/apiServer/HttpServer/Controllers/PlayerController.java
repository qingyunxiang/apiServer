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
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;



@Controller("/player")
public class PlayerController {
    @Get(":playerName/ess/prefix")
    @HttpCode(HttpStatuCode.OK)
    public JSONObject getPrefix(
            Context context,
            @Server org.bukkit.Server server,
            @Param("playerName") String  playerName
    ) {
        JSONObject jsonObject = new JSONObject();
        IEssentials essentials = (IEssentials) server.getPluginManager().getPlugin("Essentials");
        User user = essentials.getUser(playerName);
//        if(user == null) {
//             NotFound Error
//            return "notFound";
//        }

    }
}
