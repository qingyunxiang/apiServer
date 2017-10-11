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
            new RestfulServer(getServer());
            Set<Class<?>> classes = new ClassHelper().scanner(getClassLoader().getResources("moe/haozi/qingyunxiang/apiServer")).getClasses();
//            for(Class<?> _class : classes) {
//                for(Method method: _class.getMethods()) {
//                    if (method.isAnnotationPresent(Get.class)) {
//                        Get get = method.getAnnotation(Get.class);
//                        Router.Get(method.getAnnotation(Path.class).value(), (ctx) -> {
//                            try {
//                                method.invoke(_class.newInstance(), new Object[]{ ctx});
//                            }catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        });
//
//                    }
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
