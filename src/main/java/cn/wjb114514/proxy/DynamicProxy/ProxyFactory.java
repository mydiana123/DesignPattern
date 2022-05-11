package cn.wjb114514.proxy.DynamicProxy;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Objects;

// JDK代理：动态生成一个 和目标对象实现相同接口的，更强大的叔叔类
public class ProxyFactory {
    // 维护一个目标对象
    private Object target ;
    // 通过构造器传参实现目标对象的聚合

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象生成一个 代理对象。
    public Object getProxyInstance() {

        // 1.loader
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 获取目标对象类加载器
                target.getClass().getInterfaces(), // 获取当前目标对象实现的所有接口，为了让代理对象也实现和目标对象相同的接口
                // jdk实现事件回调机制，是通过匿名内部类实现的
                (proxy, method, args) -> {
                    // 实现接口InvocationHandler的invoke方法，设置代理对象 执行目标对象方法的增强逻辑
                    System.out.println("我是jdk代理 ==>在目标对象方法之前 调用的方法");
                    Object returnVal = method.invoke(target,args); // 反射调用目标对象的方法
                    System.out.println("我是jdk代理 ==>在目标对象方法之后，调用的方法");
                    return returnVal;
                }
        );
    }
}

//    @CallerSensitive
//    public static Object newProxyInstance(ClassLoader loader, // 指定当前目标对象使用的类加载器，获取方法是固定的[反射
// 指定当前目标对象实现的接口类型，使用泛型方式确认类型。[这里就体现了代理对象也要实现目标对象的接口，只不过不需要我们手动创建一个接口实现类，而是通过反射动态获取]
// 可以认为，我们的代理对象是比目标对象更强大的叔叔类[俩人都实现同样的接口，都属于一个爹。]
//                                          Class<?>[] interfaces,
                                            // h:事件处理器，执行目标对象的方法时，会触发事件处理器的方法。
                                            // 也就是说，会把当前目标对象执行的方法当做参数传入。
//                                          InvocationHandler h)
//            throws IllegalArgumentException
//    {
//        Objects.requireNonNull(h);
//
//        final Class<?>[] intfs = interfaces.clone();
//        final SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
//        }
//
//        /*
//         * Look up or generate the designated proxy class.
//         */
//        Class<?> cl = getProxyClass0(loader, intfs);
//
//        /*
//         * Invoke its constructor with the designated invocation handler.
//         */
//        try {
//            if (sm != null) {
//                checkNewProxyPermission(Reflection.getCallerClass(), cl);
//            }
//
//            final Constructor<?> cons = cl.getConstructor(constructorParams);
//            final InvocationHandler ih = h;
//            if (!Modifier.isPublic(cl.getModifiers())) {
//                AccessController.doPrivileged(new PrivilegedAction<Void>() {
//                    public Void run() {
//                        cons.setAccessible(true);
//                        return null;
//                    }
//                });
//            }
//            return cons.newInstance(new Object[]{h});
//        } catch (IllegalAccessException|InstantiationException e) {
//            throw new InternalError(e.toString(), e);
//        } catch (InvocationTargetException e) {
//            Throwable t = e.getCause();
//            if (t instanceof RuntimeException) {
//                throw (RuntimeException) t;
//            } else {
//                throw new InternalError(t.toString(), t);
//            }
//        } catch (NoSuchMethodException e) {
//            throw new InternalError(e.toString(), e);
//        }
//    }
