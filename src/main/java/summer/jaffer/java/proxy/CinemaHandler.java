package summer.jaffer.java.proxy;

import summer.jaffer.log.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CinemaHandler implements InvocationHandler {

    private Object target;

    CinemaHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(target, args);
        after();
        return null;
    }

    private void before() {
        LogUtils.sout("动态代理 准备爆米花");
    }

    private void after() {
        LogUtils.sout("动态代理 带走垃圾");
    }
}
