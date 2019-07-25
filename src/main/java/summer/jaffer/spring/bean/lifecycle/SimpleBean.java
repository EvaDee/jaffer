package summer.jaffer.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import summer.jaffer.log.LogUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class SimpleBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
        BeanPostProcessor, InitializingBean, DisposableBean {


    SimpleBean() {
        LogUtils.info(this, "1. SimpleBean Contructor");
    }

    @Override // BeanNameAware
    public void setBeanName(String s) {
        LogUtils.info(this, "2. setBeanName");
    }

    @Override // BeanFactoryAware
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LogUtils.info(this, "3. setBeanFactory");
    }

    @Override // ApplicationContextAware
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LogUtils.info(this, "4. setApplicationContext");
    }

    @PostConstruct
    public void start() {
        LogUtils.info(this, "5. start");
    }

    @Override // InitializingBean
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(this, "6. afterPropertiesSet");
    }

    public void init() {
        LogUtils.info(this, "7. init");
    }

    @Override // BeanPostProcessor before
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LogUtils.info(this, "8. postProcessBeforeInitialization");
        return null;
    }

    @Override // BeanPostProcessor after
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LogUtils.info(this, "9. postProcessAfterInitialization");
        return null;
    }

    @PreDestroy
    public void end() {
        LogUtils.info(this, "10. end");
    }

    @Override // DisposableBean
    public void destroy() throws Exception {
        LogUtils.info(this, "11. destroy");
    }

    public void selfDestroy() throws Exception {
        LogUtils.info(this, "12. selfDestroy");
    }

}
