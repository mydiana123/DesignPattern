package cn.wjb114514.proxy.DynamicCglibProxy.Start;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {

    // 内部维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 返回一个代理对象[基于目标对象的子类增强。] 代理对象其实是 由cglib修改字节码生成的匿名类的实例
    public Object getProxyInstance() {
        // 1.创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 2.设置父类:就是目标对象的类型
        enhancer.setSuperclass(target.getClass());
        // 3.设置回调函数
        enhancer.setCallback(this);
        // 4.创建子类对象以及代理对象
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 有些类似之前的 InvocationHandler 的invoke方法，用于调用目标方法
        System.out.println("Cglib代理开始 ==== 可以在目标方法执行前执行pre预处理");
        Object invokeReturnValue = method.invoke(target,args);
        System.out.println("Cglib代理结束 ==== 可以在结束前进行后处理postHandle");
        return invokeReturnValue;
    }
}
