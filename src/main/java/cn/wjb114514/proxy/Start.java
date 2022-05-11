package cn.wjb114514.proxy;

/**
 * 代理模式：为一个对象提供一个替身，以控制这个对象 的访问，通过代理访问目标对象。
 * 好处是：可以在目标对象实现的基础上，增强额外的功能。即扩展目标对象的功能‘
 * 被代理的对象可以是远程对象，创建开销大的对象，或者需要安全控制的对象，或者sql执行过程中的事务操作
 * 也可以通过代理完成，[aop]
 * 代理模式的形式：静态代理，动态代理[JDK代理，接口代理  cglib代理] cglib代理可以在内存中动态的创建对象，不需要实现接口。
 *
 *
 * 其他代理模式：
 * 1.防火墙代理 ====> 内网通过代理穿透防火墙，实现对公网访问 VPN的原理。代理功能更强大，可以访问内网和外网，因此使用代理可以实现内网到外网的访问
 * 2.缓存代理： ====> 这个就是http协议的缓存代理服务器，如果用户请求一个资源，可以先去缓存服务器请求，如果没有再去目标服务器请求，当然缓存也有过期时间
 * 3.远程代理： ====> 远程对象的本地代表，可以把远程对象当做本地对象来调用，相当于其他国家总理来中国办理本国业务。远程代理通过网络和真正的远程对象沟通信息。
 * 4.同步代理： ====> 完成多线程的同步工作，相当于此代理完成了业务方法的线程安全性。
 */
public class Start {
}
