package cn.wjb114514.visitor.code;

public class DoubleDispatch {
    public static void main(String[] args) {
        Boss boss = new Boss();
        boss.allocate(new D1()); // 处理A的业务逻辑 ?? 为什么出现这种情况
    }
}

class Boss {
    public void allocate(A a) {
        // 传入A的子类型，Boss类进行任务分配
        Displayer displayer = new Displayer();
        displayer.display(a);
    }
}


/*
注意：我们在此类 使用[方法重载] 对不同参数类型的方法进行了判断。
至于为什么不用 一个方法，之后if-else判断 instanceof 谁，这就不多说了，违反ocp...
但是使用方法重载带来的弊端是
由于Boss类的allocate方法会优先匹配参数最接近的方法。
也就是，对于重载方法，不同于重写方法，重载方法是一个编译期的概念，只会在编译期对参数类型最接近的方法匹配
allocate()的参数是什么类型，displayer就会调用什么类型的display，而且只看编译类型，不看运行类型
所以就算你把子类传入了，也不管。
所以：要想实现 运行时的动态分派，就要借助方法重写了
 */
// 在A和D1以及D2里定义了业务逻辑，此类负责调度业务逻辑
class Displayer {
    public void display(A a) {
        System.out.println("处理A的业务逻辑");
    }
    public void display(D1 d1){
        System.out.println("处理d1的业务逻辑");
    }
    public void display(D2 d2){
        System.out.println("处理d2的业务逻辑");
    }
}

class A {
    public void method() {
        // 业务逻辑...
    }
}
class D1 extends A {
    @Override
    public void method() {
        // 更强大的业务逻辑 分类1...
    }
}
class D2 extends A {
    @Override
    public void method() {
        // 更强大的业务逻辑 分类2...
    }
}