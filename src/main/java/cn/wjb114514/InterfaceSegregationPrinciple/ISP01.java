package cn.wjb114514.InterfaceSegregationPrinciple;

/**
 * 接口隔离原则：
 * 1.客户端不应该依赖它不需要的接口，即一个类对另一个类的依赖，应该建立在最小的接口上
 */
public class ISP01 {
    public static void main(String[] args) {
        new A().Depend1(new B()); // B实现了fun1
        new A_().Depend1(new B_()); // B_实现了fun1
    }
}

class B implements Interface01 {

    @Override
    public void fun1() {
        System.out.println("B实现了fun1");
    }

    @Override
    public void fun2() {
        System.out.println("B实现了fun2");
    }

    @Override
    public void fun3() {
        System.out.println("B实现了fun3");
    }

    @Override
    public void fun4() {
        System.out.println("B实现了fun4");
    }

    @Override
    public void fun5() {
        System.out.println("B实现了fun5");
    }
}

// A通过Interface01依赖B实现其的方法，但只会使用前三个方法
class A {
    // 使用A的depend1方法时，会传入interface01的实现类B。相当于A通过接口和B建立了依赖关系
    public void Depend1(Interface01 i) {
        i.fun1();
    }
    public void Depend2(Interface01 i) {
        i.fun2();
    }
    public void Depend3(Interface01 i) {
        i.fun3();
    }
}

class C {
    public void Depend1(Interface01 i) {
        i.fun1();
    }
    public void Depend2(Interface01 i) {
        i.fun4();
    }
    public void Depend3(Interface01 i) {
        i.fun5();
    }
}
interface Interface01 {
    void fun1();
    void fun2();
    void fun3();
    void fun4();
    void fun5();
}

// 解决方案：把接口interface01拆分成最小的接口单元
interface Interface01_1 {
    void fun1();
}
interface Interface01_2 {
    void fun2();
    void fun3();
}
interface Interface01_3 {
    void fun4();
    void fun5();
}
class B_ implements Interface01_1,Interface01_2 {

    @Override
    public void fun1() {
        System.out.println("B_实现了fun1");
    }

    @Override
    public void fun2() {

    }

    @Override
    public void fun3() {

    }
}

class B__ implements Interface01_1,Interface01_3 {

    @Override
    public void fun1() {

    }

    @Override
    public void fun4() {

    }

    @Override
    public void fun5() {

    }
}

class A_ {
    // 使用A的depend1方法时，会传入interface01的实现类B。相当于A通过接口和B建立了依赖关系
    public void Depend1(Interface01_1 i) {
        i.fun1();
    }
    public void Depend2(Interface01_2 i) {
        i.fun2();
    }
    public void Depend3(Interface01_2 i) {
        i.fun3();
    }
}