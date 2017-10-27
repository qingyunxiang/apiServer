package moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 运行时保存
public @interface Body {
}
