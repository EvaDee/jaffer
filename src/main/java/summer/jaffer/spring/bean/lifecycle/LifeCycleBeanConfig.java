package summer.jaffer.spring.bean.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import summer.jaffer.log.LogUtils;

//@Configuration
public class LifeCycleBeanConfig {

    @Bean(name = "signleBeanTmp", initMethod = "init", destroyMethod = "selfDestroy")
    public SimpleBean getSimpleBean() {
        return new SimpleBean();
    }

}
