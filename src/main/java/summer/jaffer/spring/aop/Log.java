package summer.jaffer.spring.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    // log被发送的位置，默认输出到控制台
    LogDestination des() default LogDestination.CONSOLE;

}
