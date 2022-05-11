package cn.wjb114514.bridge;

// 实现层实现子类A ConcreteImplementorA
public class Vivo implements Brand{
    @Override
    public void open() {
        System.out.println("Vivo手机开机");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机关机");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机打电话");
    }
}
