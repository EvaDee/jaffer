package summer.jaffer.spring.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import summer.jaffer.log.LogUtils;

@RestController
public class AOPController {

    @RequestMapping("/abc")
    @Log(des = LogDestination.CONSOLE)
    public String test(String a, String b, String c) {
        LogUtils.sout("a: " + a);
        LogUtils.sout("b: " + b);
        LogUtils.sout("c: " + c);
        // 该方法调用不会被aop拦截 需要显示调用AOPController的代理类才有效
        notLogged("not be intercepted by aop");
        return "/abc";
    }

    @Log
    public void notLogged(String info) {
        LogUtils.sout(info);
    }
}
