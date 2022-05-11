package cn.wjb114514.proxy.DynamicCglibProxy.Start;

/**
 * Cglib代理 不基于接口，而是直接通过动态生成 比当前类更强大的子类。子类是不需要手写的，而是通过反射在内存里动态生成。
 * Cglib在Spring-AOP里实现方法拦截等工作。
 * 底层原理：使用字节码处理框架ASM来转换字节码并生成新的类。
 * 使用Cglib代理的目标对象，不需要实现任何接口。
 *
 * 注意事项：
 * 1.导包
 * 2.在内存中动态构建子类，所以被代理的类不能是final，那还构造个毛子类
 * 3.目标对象的方法如果是final/static 就不会被拦截而实现增强。 也很好理解，就按照final/static方法 的实质理解就好
 */
public class Start {

}
