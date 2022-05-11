package cn.wjb114514.proxy.StaticProxy;

/**
 * 优点：静态代理在 不修改目标对象的基础上，增强了目标对象的功能
 * 需要实现同样的接口，复杂度较高，代理类较多。 如果共同实现的接口加了方法，目标对象和代理对象都要维护
 */
public class Client {
    public static void main(String[] args) {
        // 目标对象，客户端不直接访问
        ITeacherDao teacherDao = new TeacherDao();

        // 既然用了代理模式，就要通过代理对象访问，而不是直接访问
        teacherDao.teach(); // 直接访问目标对象，达不到效果！

        System.out.println("=====我是一个分割线=====");

        // 创建代理对象:需要把目标对象[被代理对象传入，聚合到代理对象内部]
        ITeacherDao proxy = new TeacherDaoProxy(teacherDao);
        proxy.teach(); // 通过代理访问目标对象，达到了我们需要的 效果~

        new Thread(()->{
            System.out.println("hello,world");
        }).start();
    }
}
/*
Thread怎么体现代理模式。
我们知道，Runnable接口只提供了run方法， 如果我们直接把runnable对象强转为Thread对象并调用run方法。
相当于直接调用目标对象的方法，就相当于在主线程里掉了个方法。
而我们想要开辟一个线程，肯定不是调用一个run方法这么简单的。
在Thread的代理模式里，我们通过代理对象Thread调用了目标对象Runnable。
其中，代理对象增强了run方法，在执行run方法前，开辟了线程组，设置守护进程.等一系列初始工作。并调用start方法开辟一个线程，随后才执行目标对象的run方法，
 */