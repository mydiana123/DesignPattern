package cn.wjb114514.proxy.DynamicProxy;

public class Client {
    public static void main(String[] args) {
        // 创建目标对象
        ITeacherDao target = new TeacherDao();

        // 获得代理对象
        ITeacherDao proxy = (ITeacherDao) new ProxyFactory(target).getProxyInstance();

        // proxy的运行类型为class com.sun.proxy.$Proxy0: 这不就是一个匿名类吗，因此JDK代理就是在运行期，在内存里动态生成一个目标对象的叔叔类。
        // 以增强功能~~~
        System.out.println("proxy的运行类型为" + proxy.getClass());


        // 代理对象具体增强什么方法，取决于我们调了目标对象的什么方法
        // 原理就是，我们调用了 目标对象的什么方法，事件处理器的method参数就会反射获取到对应方法~~
        proxy.teach();
        System.out.println("---------");

        // 可以看到，就连toString()方法都被增强了...
        System.out.println(proxy.toString());
    }
}
