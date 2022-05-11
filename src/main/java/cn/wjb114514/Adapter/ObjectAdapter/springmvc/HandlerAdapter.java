package cn.wjb114514.Adapter.ObjectAdapter.springmvc;

/**
 * 因为各种各样的 Controller处理的东西也不一样，主要体现在java的多态上，不同运行类型的controller可以处理不同的内容
 * Controller可以看成输出电压，因为我们用户需要用到他的结果
 * Adapter可以看成电压转换器，不同用户用到的电压不同==>不同处理的东西处理方式不同，有人用到5V，6V 或者220V。
 * 适配器内部聚合了controller对象，并使用handler方法把输出后的结果进行了输出。
 * 相当于
 *
 *     被适配者(Controller的子类) -专门的适配器-> handler方法输出转换后的结果
 *     因此，我们针对专门的Controller，设置专门的适配器
 *
 */
public interface HandlerAdapter {
    boolean support(Object handler);
    void handle(Object handler);
}


// 在SpringMVC里，此Adapter负责把http请求对象和响应对象传递给http请求处理器Controller。相当于实现了http的远程调用
class HttpHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof HttpController; // 判断当前handler的运行时类型是不是HttpController及其子类型
    }

    @Override
    public void handle(Object handler) {
        // 调用相关类型handler的方法
        ((HttpController)handler).doHttpHandler();
    }
}

/**
 * SimpleControllerHandlerAdapter是简单控制器处理器适配器，这个实现类将HTTP请求适配到一个控制器的实现进行处理。
 * 这里控制器的实现是一个简单的控制器接口的实现。
 * 简单控制器处理器适配器被设计成一个框架类的实现，不需要被改写，客户化的业务逻辑通常是在控制器接口的实现类中实现的。
 */
class SimpleHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof SimpleController;
    }

    @Override
    public void handle(Object handler) {
        ((SimpleController)handler).doSimpleHandler();
    }
}

// 此类用于针对 注解形式Controller的处理
class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof AnnotationController;
    }

    @Override
    public void handle(Object handler) {
        ((AnnotationController)handler).doAnnotationHandler();
    }
}
