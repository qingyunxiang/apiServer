package moe.haozi.qingyunxiang.apiServer.HttpServer.Router;

import com.avaje.ebean.Update;
import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.*;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
import moe.haozi.qingyunxiang.apiServer.Tools.ClassHelper;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Router {
    static public ArrayList<Route> routes = new ArrayList<>();
    static public HashMap<Class<? extends Annotation>, Object> parameterAnnotations = new HashMap<>();

    static public BiConsumer<Context, Consumer<String>> route() {
        return (ctx, next) -> {
            for(int i = 0; i < Router.routes.size(); i++) {
                Route route = Router.routes.get(i);
                if (route != null) {
                    if (route.exec(ctx)) {
                        break;
                    }
                }
            }
            next.accept(null);
        };
    }


    static public void RegisterRoute(String packagePath, ClassLoader classLoader, org.bukkit.Server server) throws IOException{
        Set<Class<?>> classes = new ClassHelper().scanner(classLoader.getResources(packagePath.replace(".", "/")), packagePath).getClasses();
        try {
            for(Class<?> _class : classes) {
                if(!_class.isAnnotationPresent(Controller.class)) {
                    return;
                }

                String prefix =  _class.getAnnotation(Controller.class).value();

                for(Method method: _class.getMethods()) {
                    final Route route = new Route();
                    route.method(Context.HttpMethod.GET);
                    route.setCallback((ctx) -> {
                        try {

                            ArrayList<Object> args = new ArrayList<>();
                            args.add(ctx);

                            route.parmaList.forEach((key, value) -> {

                                if(key instanceof Server) {
                                    if(value == null) {
                                        args.add(route.parmaListValue.get(key));
                                    } else {
                                        args.add(value);
                                    }
                                } else if (key instanceof Param) {
                                    args.add(route.getParma(ctx, value));
                                } else if(key instanceof Query) {
                                    args.add(route.getQuery(ctx, value));
                                }
                            });

                            method.invoke(_class.newInstance(), args.toArray() );
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    if (method.isAnnotationPresent(Get.class)) {
                        route.method(Context.HttpMethod.GET);
                    } else if (method.isAnnotationPresent(Post.class)) {
                        route.method(Context.HttpMethod.POST);
                    } else if (method.isAnnotationPresent(Post.class)) {
                        route.method(Context.HttpMethod.PUT);
                    } else if (method.isAnnotationPresent(Put.class)) {
                        route.method(Context.HttpMethod.DELETE);
                    } else if (method.isAnnotationPresent(Delete.class)) {
                        route.method(Context.HttpMethod.UPDATE);
                    } else if (method.isAnnotationPresent(UPDATE.class)) {
                        route.method(Context.HttpMethod.UPDATE);
                    } else if (method.isAnnotationPresent(PATCH.class)) {
                        route.method(Context.HttpMethod.PATCH);
                    } else if(method.isAnnotationPresent(Path.class)) {
                        route.setPath(prefix += method.getAnnotation(Path.class).value());
                    } else if(method.isAnnotationPresent(HttpCode.class)) {
                        route.setHttpCode(method.getAnnotation(HttpCode.class).value());
                    }

                    if (method.getParameterCount() > 0) {
                        for(Annotation[] annotations : method.getParameterAnnotations()) {
                            for(Annotation annotation : annotations) {
                                if (annotation instanceof Server) {
                                    route.addParma(annotation, server);
                                } else if (annotation instanceof Param) {
                                    route.addParma(annotation, ((Param) annotation).value());
                                }  else if (annotation instanceof Query) {
                                    route.addParma(annotation, ((Query) annotation).value());
                                }
                            }
                        }
                    }
                    Router.routes.add(route);
                }
            }
        } catch (Exception e) {

        }
    }
    static public void RegisterRoute(String packagePath) throws IOException{
        RegisterRoute(packagePath, Thread.currentThread().getContextClassLoader(), null);
    }

    /**
     * 添加参数注解接口
     * @param annotation
     * @param value
     */
    public static void RegisterParameternnotation(Class<? extends Annotation> annotation, Object value) {
        Router.parameterAnnotations.put(annotation, value);
    }


}


