package moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.)
public @interface Prefix {
    String value() default "/";
}