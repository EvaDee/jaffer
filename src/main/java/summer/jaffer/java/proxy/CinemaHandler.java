package summer.jaffer.java.proxy;

import summer.jaffer.log.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CinemaHandler implements InvocationHandler {

    /**
     * 被代理的对象
     */
    private Object target;

    CinemaHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 实现了被代理接口的代理对象，根据该类型可以区分被代理的接口
     * @param method 被调用的方法
     * @param args 被掉用方法的方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 一个InvocationHandler代理多个接口，静态代理
        if (proxy instanceof Movie) {
            beforeMovie();
            method.invoke(target, args);
            afterMovie();
        } else if (proxy instanceof Speech) {
            beforeSpeech();
            method.invoke(target, args);
            afterSpeech();
        }

        return null;
    }

    private void beforeMovie() {
        LogUtils.sout("动态代理 准备爆米花");
    }

    private void afterMovie() {
        LogUtils.sout("动态代理 带走垃圾");
    }

    private void beforeSpeech() {
        LogUtils.sout("动态代理 看演讲资料");
    }

    private void afterSpeech() {
        LogUtils.sout("动态代理 向教授提问题");
    }

}
