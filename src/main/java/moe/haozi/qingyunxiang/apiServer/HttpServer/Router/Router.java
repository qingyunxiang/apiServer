package moe.haozi.qingyunxiang.apiServer.HttpServer.Router;

import com.sun.javaws.security.Resource;
import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.RestfulServer;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Get;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Path;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.Post;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.Tools.ClassHelper;

import javax.annotation.Resources;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Router {
    static public ArrayList<Route> routes = new ArrayList<>();

    static public BiConsumer<Context, Consumer<String>> route() {
        return (ctx, next) -> {
            for(int i = 0; i < Router.routes.size(); i++) {
                Route route = Router.routes.get(i);
                if (route != null) {
                    route.exec(ctx);
                }
            }
            next.accept(null);
        };
    }

    static public Route Get(String path, Consumer<Context> callback) {
        Route route = new Route("Get", path, callback);
        Router.routes.add(route);
        return route;
    }

    static public void RegisterRoute(Enumeration<URL> resources) {
        Set<Class<?>> classes = new ClassHelper().scanner(resources).getClasses();
        try {
            for(Class<?> _class : classes) {
                for(Method method: _class.getMethods()) {
                    Route route = null;
                    if (method.isAnnotationPresent(Get.class)) {
                        route = Router.Get(method.getAnnotation(Path.class).value(), (args) -> {
                            try {
                                method.invoke(_class.newInstance(), args);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    if (method.getParameterCount() > 0) {
                        int index = 0;
                        Class[] parameterTypes = method.getParameterTypes();
                        for(Annotation[] annotations : method.getParameterAnnotations()) {
                            for(Annotation annotation : annotations) {
                                if(annotation instanceof Server) {

                                }
                            }
                            index ++;
                        }
                    }
                    if (method.isAnnotationPresent(Post.class)) {
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}


