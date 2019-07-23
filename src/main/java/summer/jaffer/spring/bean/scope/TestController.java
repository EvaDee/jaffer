package summer.jaffer.spring.bean.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value = "prototype")
public class TestController {
    @Autowired
    private SessionBean sessionBean;

    @Autowired
    private RequestBean requestBean;

    @Autowired
    private SingletonBean singletonBean;

    @Autowired
    private PrototypeBean prototypeBean;

    @RequestMapping("/test")
    public String test() {
        System.out.println("singletonBean is :" + singletonBean);
        System.out.println("prototypeBean is :" + prototypeBean);
        System.out.println("requestBean is :" + requestBean);
        System.out.println("sessionBean is :" + sessionBean);
        return sessionBean.toString();
    }
}
