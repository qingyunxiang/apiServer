package moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers.Server;

import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.*;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.HttpStatuCode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.Metadatable;
import org.json.simple.*;

import java.sql.Connection;

@Controller("/server")
public class ServerController {

    @Get("/serverinfo")
    @HttpCode(HttpStatuCode.OK)
    public JSONObject getServerInfo(Context context, @Server org.bukkit.Server server) {
        JSONObject serverInfo = new JSONObject();
        serverInfo.put("serverName", server.getServerName());

        JSONArray playsers = new JSONArray();

        server.getOnlinePlayers().stream().forEach(player -> {
            playsers.add(((Player) player).getDisplayName());
        });

        serverInfo.put("players", playsers);
        return serverInfo;
    }


    @Post("/dispatchCommand")
    @HttpCode(HttpStatuCode.CREATED)
    public String dispatchCommand(Context context, @Server org.bukkit.Server server) {
        server.dispatchCommand(server.getConsoleSender(), context.body.get("cmd").toString());
        return "ok~";
    }

    @Get("/blockMetadata/:id")
    @HttpCode(HttpStatuCode.CREATED)
    public JSONObject getBlockMetadata(Context context, @Server org.bukkit.Server server) {
        // TODO 获取方块信息
        JSONObject itemInfo = new JSONObject();
        try {
            ItemStack stack  = new ItemStack(2333);
            itemInfo.put("display_name", stack.getItemMeta().getDisplayName());
        } catch (Exception e) {

        }
        return itemInfo;
    }
}