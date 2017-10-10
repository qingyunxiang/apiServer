package moe.haozi.qingyunxiang.apiServer;

import moe.haozi.qingyunxiang.apiServer.HttpServer.RestfulServer;
import org.bukkit.plugin.java.JavaPlugin;

public class ApiServer extends JavaPlugin{
    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        try {
            new RestfulServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
