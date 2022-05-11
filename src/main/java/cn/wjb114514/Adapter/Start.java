package cn.wjb114514.Adapter;

/**
 * 适配器模式
 * A类m1方法想要调用B的m2方法，但是因为可能是形参列表的不同，导致无法直接调用，我们就采取适配器模式
 * 别名为Wrapper包装器。[mybatisPlus？]
 * 使用适配器，对A来说看似透明的调用了B。
 * 是一种结构性的设计模式，有三种模式：类适配器，对象适配器，接口适配器
 *
 * 类似转接口，电源转接口，耳机转接口
 * 1.将一个类的接口。转换为另一种接口，让原本接口不相容的类可以互相兼容
 * 2.从用户的角度看不到被适配者，是解耦的
 * 3.用户调用适配器转换出来的目标接口方法，适配器再调用被适配者的相关接口
 * 被适配者(src) -- Adaptor --> 需要的输出dst
 * 被适配者就是 三项插头，Adaptor就是三项插头转原型接口转接器，输出就是我们想要的插座口。 于是用户直接使用用户想要的插座口，但是如何得到的这个插座口对用户是不可见的。是解耦的
 *
 * 用户想要类B,于是我们使用类A+适配器。用户得到了类B，但是看不见类A，类A和类B通过适配器依赖， 但又不直接依赖
 *
 * 类适配器：继承被适配者，实现目标类的接口，完成src->dst的适配
 *
 * 手机充电：  220V电压 -- 电源适配器 --> 5V电压 --> 用户使用5V电压充电
 *
 * 对象适配器：
 * 针对类适配器需要的继承进行了解决！
 * 持有被适配者src的对象，实现dst接口。这样被适配者和适配器的关系由继承(泛化)转化为聚合。遵循合成复用原则，即组合和聚合代替继承
 *
 * 接口适配器：
 * 缺省适配器：可以先设计一个抽象类实现所有接口（默认的空方法），子类选择性重写父类的方法，实现接口需求
 * 适用于一个接口不想使用其所有方法的情况。
 * 比较典型的就是监听器Listener。有的监听器可以监听很多事件，比如鼠标点击，鼠标移动，鼠标聚焦。有时候我们不想监听这些事件，就可以选择性重写。
 * 但是接口必须要求实现，这样我们可以用jdk8新特性，接口的默认方法。或者使用一个抽象类，先把接口里的方法都给实现了。
 * 于是我们直接通过匿名内部类，new一个抽象类的实现类，完成方法的覆写，选择性的实现接口功能
 */
public class Start {
    public static void main(String[] args) {
        // 选择性实现
        AbstractAdapter adapter = new AbstractAdapter() {
            @Override
            public void method4() {
                System.out.println("选择性使用m4方法");
            }

            @Override
            public void method1() {
                System.out.println("选择性处理m1方法");
            }
        };
        adapter.method1();
    }
}

interface IListener {
    void method1();
    void method2();
    void method3();
    void method4();
}

abstract class AbstractAdapter implements IListener{
    @Override
    public void method1() {
        // 空实现
    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }

    @Override
    public void method4() {

    }
}