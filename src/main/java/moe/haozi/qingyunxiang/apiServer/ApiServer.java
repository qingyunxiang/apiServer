package moe.haozi.qingyunxiang.apiServer;

import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.RestfulServer;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Get;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Path;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Router;
import moe.haozi.qingyunxiang.apiServer.Tools.ClassHelper;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

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
            new RestfulServer(getServer(), getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
