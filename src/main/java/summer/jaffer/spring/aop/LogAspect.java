package summer.jaffer.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import summer.jaffer.log.LogUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 简单log AOP
 * 实现打印所有加@Log注解的方法的参数的打印
 * 切点、通知、连接点、切面、织入概念见序号1-5
 * 5.整个AOP代理对象加载到内存的过程为"织入"
 * Spring AOP中被代理的A方法内部直接调用同类的B方法，则B方法即使加@Log注解也不会被切面代理，因为调用B时未调用代理方法
 */
@Component
@Aspect // 4.定义切面
public class LogAspect {

    @Pointcut("@annotation(summer.jaffer.spring.aop.Log)") // 1.定义"切点"为加注解Log的地方
    public void logPointCut() {}

    /**
     * 创建环绕通知
     * @param jp ProceedingJoinPoint is only supported for around advice
     */
    @Around("logPointCut()") // 2.创建环绕"通知"
    public void methodLog(ProceedingJoinPoint jp) { // 3.jp为"连接点"，由jp可以获取被调用方法签名和实际参数，由方法签名可以通过反射获取该方法的Method
        try{
            MethodSignature signature = (MethodSignature) jp.getSignature();
            Method method = jp.getTarget().getClass().getMethod(signature.getName(), signature.getParameterTypes());
            Log log = method.getDeclaredAnnotation(Log.class);
            if (log != null) {
                if(log.des().equals(LogDestination.CONSOLE)) {
                    logToConsole(jp, method);
                } else if(log.des().equals(LogDestination.FILE)) {
                    logToFile(jp, method);
                } else if(log.des().equals(LogDestination.DATASTORE)) {
                    logToDataStore(jp, method);
                } else {
                    LogUtils.sout("有新增LogDestination类型未做判断");
                }
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    /**
     * 打印日志到控制台
     * @param jp
     * @param method
     * @throws Throwable
     */
    private void logToConsole(ProceedingJoinPoint jp, Method method) throws Throwable {
        Parameter[] params = method.getParameters();
        Object[] paramValues = jp.getArgs();
        // 打印方法调用参数参数
        for(int i = 0; i < params.length; i++) {
            LogUtils.sout(jp.getTarget().getClass().getName()
                    + "." + method.getName() + " : " + params[i].getName() + " : " + paramValues[i]);
        }
        Object res = jp.proceed();
        if (res != null)
            LogUtils.sout(jp.getTarget().getClass().getName()
                    + " resutl :" + res.toString());
    }

    /**
     * 打印日志到文件
     * @param jp
     * @param method
     * @throws Throwable
     */
    private void logToFile(ProceedingJoinPoint jp, Method method) throws Throwable {
        LogUtils.sout("sout to file");
    }

    /**
     * 打印日志到db
     * @param jp
     * @param method
     * @throws Throwable
     */
    private void logToDataStore(ProceedingJoinPoint jp, Method method) throws Throwable {
        LogUtils.sout("sout to db");
    }
}
