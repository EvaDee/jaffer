### bean作用域
一共有一下5种

- Singleton - 每个 Spring IoC 容器仅有一个单实例。

- Prototype - 每次请求都会产生一个新的实例。使用注解标注bean为prototype类时，如果引用它的bean为singleton类型，prototype类型失效。
- Request - 每一次 HTTP 请求都会产生一个新的实例，并且该 bean 仅在当前 HTTP 请求内有效。
- Session - 每一次 HTTP 请求都会产生一个新的 bean，同时该 bean 仅在当前 HTTP session 内有效。
- Global-session - 类似于标准的 HTTP Session 作用域，不过它仅仅在基于 portlet 的 web 应用中才有意义。Portlet 规范定义了全局 Session 的概念，它被所有构成某个 portlet web 应用的各种不同的 portlet 所共享。在 global session 作用域中定义的 bean 被限定于全局 portlet Session 的生命周期范围内。如果你在 web 中使用 global session 作用域来标识 bean，那么 web 会自动当成 session 类型来使用。

### Bean生命周期

#### Bean初始化各个方法调用过程

1.Spring对bean进行实例化，调用bean的构造参数

2.设置对象属性，调用bean的set方法，将属性注入到bean的属性中

3.依次检查bean是否实现BeanNameAware、BeanFactoryAware、ApplicationContextAware接口，如果实现了这几个接口Spring会分别调用其中实现的方法。

BeanNameAware：setBeanName(String name)方法，参数是bean的ID

BeanFactoryAware：setBeanFactory(BeanFactory bf)方法，参数是BeanFactory容器

ApplicationContextAware：setApplicationContext(ApplicationContext context)方法，参数是bean所在的引用的上下文，如果是用Bean工厂创建bean，那就可以忽略ApplicationContextAware。

通过实现BeanFactoryAware和ApplicationContextAware接口可以获取bean的容器或者上下文，从而获取其他bean的引用。

4.如果bean是否实现BeanPostProcessor接口，Spring会在初始化方法的前后分别调用postProcessBeforeInitialization和postProcessAfterInitialization方法

5.如果bean是否实现InitalizingBean接口，将调用afterPropertiesSet()方法

6.如果bean声明初始化方法，也会被调用

7.使用bean，bean将会一直保留在应用的上下文中，直到该应用上下文被销毁。

8.检查bean是否实现DisposableBean接口，Spring会调用它们的destory方法

9.如果bean声明销毁方法，该方法也会被调用

#### 各种接口方法分类

1、Bean自身的方法: 这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法

2、Bean级生命周期接口方法: 这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法

3、容器级生命周期接口方法: 这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。

4、工厂后处理器接口方法: 这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器　　接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。

#### 总结

测试结果基于Spring 5.1.8RELEASE

实例化bean-->设置对象属性-->检查Aware相关接口并设置相关依赖-->检查是否是InitializingBean以决定是否调用afterPropertiesSet-->init-Method-->BeanPostProcessor前置和后置处理-->开始使用-->是否实现DisposableBean接口-->是否配置自定义的destory方法

是有singleTon作用域的bean的生命周期由spring维护。
### Reference
https://zhuanlan.zhihu.com/p/52537298

https://www.cnblogs.com/zrtqsk/p/3735273.html
