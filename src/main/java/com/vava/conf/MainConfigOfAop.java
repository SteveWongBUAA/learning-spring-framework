package com.vava.conf;

import java.lang.reflect.Method;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.vava.aop.LogAspects;
import com.vava.aop.MathCalculator;

/**
 * @author steve
 * Created on 2020-03-09
 * <p>
 * AOP: 在程序运行期间动态地将某段代码切入到指定方法指定位置运行的编程方式
 * <p>
 * 1.导入AOP模块 spring-aspects
 * 2.定义业务逻辑类 {@link com.vava.aop.MathCalculator}, 在业务逻辑运行的饿时候打印日志 （方法运行前，运行后、方法出现异常的时候）
 * 3.定义一个日志切面类 {@link com.vava.aop.LogAspects}, 切面类里面的方法需要动态地感知到 {@link com.vava.aop.MathCalculator} 的div方法运行到哪了
 * 3.1.通知方法：
 * - 前置通知 @Before :logStart
 * - 后置通知 @After :logEnd: 无论正常返回还是异常返回
 * - 返回通知 @AfterReturning :logRet
 * - 异常通知 @AfterThrowing :logException
 * - 环绕通知 @Around :动态代理，手动推进方法运行，joinPoint.proceed
 * 4.给切面类的方法标注
 * 5.将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 * 6.告诉Spring哪个类是切面类（给切面类加上注解： @Aspect）
 * 7.开启AOP功能
 * - XML： <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 * - 注解：@EnableAspectJAutoProxy
 * <p>
 * AOP原理：
 * 含义：在程序运行期间，动态地把某段代码切入到指定方法指定位置进行运行的 编程方式
 * 底层原理：动态代理
 * 看代码方法：看给容器中注册了什么组件，这个组件什么时候工作
 * <p>
 * 1.@EnableAspectJAutoProxy是什么
 * - Import了 {@link org.springframework.context.annotation.AspectJAutoProxyRegistrar}
 * - 利用 {@link org.springframework.context.annotation.AspectJAutoProxyRegistrar} 自定义给容器中注册Bean
 * 2.注册的组件是：internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 * 3.来看{@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator}
 * - 父类：{@link org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator}
 * -- 父类：{@link org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator}
 * --- 父类：{@link org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator}
 * ---- 父类: {@link org.springframework.aop.framework.ProxyProcessorSupport}，
 * ---- 实现了: {@link org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor} 和
 * ---- {@link org.springframework.beans.factory.BeanFactoryAware}
 * 也就是说，引入了这个组件，他实现了Bean后置处理器（初始化完成前后嵌入逻辑），而且这个Bean可以获得BeanFactory；
 * 打断点：就在我们熟悉的地方(上面说的这两个接口)打断点：{@link org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator} 实现了xxxBeanPostProcessor，那就从这个地方找PostProcessorBeforeInitialization
 *
 * 重点看以下这些我们熟悉的接口
 * {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#postProcessBeforeInitialization(Object, String)}
 * - 没有逻辑
 * {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#postProcessBeforeInstantiation(Class, String)}
 * - 将增强的advisor加载好
 * {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#postProcessAfterInitialization(Object, String)}
 * - {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#wrapIfNecessary(Object, String, Object)}
 * --- 对于被增强的bean，会得到specificInterceptors
 * --- 然后创建对象：{@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#createProxy(Class, String, Object[], TargetSource)}
 * --- 然后就包装好对象了
 *
 * {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#setBeanFactory(BeanFactory)}
 * - {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#initBeanFactory(ConfigurableListableBeanFactory)}
 *
 * 流程
 * 传入配置类，创建IoC容器
 * - 注册配置类，调用{@link AnnotationConfigApplicationContext#refresh()}
 * --- 注册bean的后置处理器来拦截bean的创建 {@link AnnotationConfigApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory)}
 * ----- 获取容器中所有的BeanPostProcessor
 * ----- 给容器中添加别的BeanPostProcessor
 * ------- 优先注册实现了PriorityOrdered接口的BeanPostProcessor, 然后是Ordered 然后是其他
 * ------- 注册BeanPostProcessor，实际上就是创建对象{@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator}，保存在容器中
 * ------- 创建bean实例
 * ------- populateBean，属性赋值
 * ------- initializeBean，初始化bean
 * --------- {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#invokeAwareMethods(String, Object)}
 * ----------- {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#setBeanFactory(BeanFactory)}
 * ------------- {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#initBeanFactory(ConfigurableListableBeanFactory)}
 * --------------- 创建 {@link org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory}
 * --------------- 创建 {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator.BeanFactoryAspectJAdvisorsBuilderAdapter
 * --------- {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)}
 * --------- {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#invokeInitMethods(String, Object, RootBeanDefinition)}
 * --------- {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)}
 *
 * ========= 以上是创建和注册 {@link org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator} 的过程
 * {@link AnnotationConfigApplicationContext#finishBeanFactoryInitialization(ConfigurableListableBeanFactory)} 完成BeanFactory初始化工作，创建剩下的单实例
 *
 * 最终代理是怎么拦截的？断点打在目标方法执行那看
 * 拦截：{@link org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept(Object, Method, Object[], MethodProxy)}
 * - 有个拦截链 chain todo:
 *
 *
 *
 *
 *
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAop {
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
