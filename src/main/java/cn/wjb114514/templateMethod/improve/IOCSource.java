package cn.wjb114514.templateMethod.improve;

import org.springframework.context.ConfigurableApplicationContext;

public class IOCSource {
    public static void main(String[] args) {
//        ConfigurableApplicationContext
    }
}

/*
public interface ConfigurableApplicationContext extends ApplicationContext, Lifecycle, Closeable {
    void refresh() throws BeansException, IllegalStateException;
}
 */

/*
Home
 联系
搜索

 个人中心
 发布文章
 退出登录
 首页  设计模式 IOC模板方法
IOC模板方法
发布时间: 2022-05-08 11:34:27
发布者:w**3  1 |  0 |  0
public interface ConfigurableApplicationContext extends ApplicationContext, Lifecycle, Closeable {
    void refresh() throws BeansException, IllegalStateException;
}
public abstract class AbstractApplicationContext extends DefaultResourceLoader
      implements ConfigurableApplicationContext {
@Override // 此refresh就是模板方法
public void refresh() throws BeansException, IllegalStateException {
   synchronized (this.startupShutdownMonitor) {
      StartupStep contextRefresh = this.applicationStartup.start("spring.context.refresh");

      // Prepare this context for refreshing.
      prepareRefresh();

      // Tell the subclass to refresh the internal bean factory.
      ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
protected abstract void refreshBeanFactory() throws BeansException, IllegalStateException;
   refreshBeanFactory(); // 这是一个抽象方法，待子类来实现。[refreshBeanFactory()]
public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
   return getBeanFactory(); // 这也是一个抽象方法，待子类实现 [getBeanFactory()]
}


      // Prepare the bean factory for use in this context.
      prepareBeanFactory(beanFactory);

      try {
         // Allows post-processing of the bean factory in context subclasses.
protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
} // 空实现的方法：子类对此方法的实现，可以影响到模板方法的处理流程 ==> 允许对上下文内的 容器对象进行 后加工。// Allows post-processing of the bean factory in context subclasses.
// 这里的钩子方法 都和事件机制结合了，也就是说默认对此类事件不做处理，但是子类重写后，模板方法就开始处理此类事件了
         postProcessBeanFactory(beanFactory); // 这是一个钩子方法

         StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
         // Invoke factory processors registered as beans in the context.
         invokeBeanFactoryPostProcessors(beanFactory);

         // Register bean processors that intercept bean creation.
         registerBeanPostProcessors(beanFactory);
         beanPostProcess.end();

         // Initialize message source for this context.
         initMessageSource();

         // Initialize event multicaster for this context.
         initApplicationEventMulticaster();

protected void onRefresh() throws BeansException {
   // For subclasses: do nothing by default.
}
         // Initialize other special beans in specific context subclasses.
         onRefresh(); // 这也是一个钩子方法，子类重写此方法会干涉 模板方法的执行流程，即模板方法会初始化其他特殊的bean对象在 具体的上下文中

         // Check for listener beans and register them.
         registerListeners();

         // Instantiate all remaining (non-lazy-init) singletons.
         finishBeanFactoryInitialization(beanFactory);

         // Last step: publish corresponding event.
         finishRefresh();
      }

      catch (BeansException ex) {
         if (logger.isWarnEnabled()) {
            logger.warn("Exception encountered during context initialization - " +
                  "cancelling refresh attempt: " + ex);
         }

         // Destroy already created singletons to avoid dangling resources.
         destroyBeans();

         // Reset 'active' flag.
         cancelRefresh(ex);

         // Propagate exception to caller.
         throw ex;
      }

      finally {
         // Reset common introspection caches in Spring's core, since we
         // might not ever need metadata for singleton beans anymore...
         resetCommonCaches();
         contextRefresh.end();
      }
   }
}
public class GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry {}
@Override
public final ConfigurableListableBeanFactory getBeanFactory() {
   return this.beanFactory;
} //  此子类实现了 AbstractApplicationContext中模板方法内的抽象方法
@Override
protected final void refreshBeanFactory() throws IllegalStateException {
   if (!this.refreshed.compareAndSet(false, true)) {
      throw new IllegalStateException(
            "GenericApplicationContext does not support multiple refresh attempts: just call 'refresh' once");
   }
   this.beanFactory.setSerializationId(getId());
}

 */