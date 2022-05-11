package cn.wjb114514.visitor.code;

/**
 * 其实吧，双分派模式 就是把每一个 访问者和被访问者都关联了，因为两者之前是互相需要的，最后结果就是，对于任意访问者，都知道其要访问的对象究竟试试额
 */
public class DoubleImprove {

    public static void main(String[] args) {
        new Boss2().allocate(new D12());
        // 我们通过双分派，完成了 重载方法的[动态加载]，因为双分派下的重载方法需要一个具体的作用对象。
        // 即某一个类在调用 display这种展示性质的方法时，会把自己也作为参数传进去，这下重载方法就知道具体是哪个类型在调用自己了
        // 而不把自己作为参数， 重载方法是编译期的概念，只看编译类型哪个最合适，就用哪个
        // 总结：重载方法要想完成动态分派，必须借助多分派，注意重载方法是编译期的概念，只看编译类型，只有重写方法是运行期概念，看运行类型
    }
}

class Boss2 {
    public void allocate(A2 a) {
        // 传入A的子类型，Boss类进行任务分配
        a.method(new Displayer2());
    }
}

// 可以看到，Displayer类仍然采取重载，但是
// 其实重载方法就是根据参数类型执行不同方法的，目的就是为了解耦合，不需要在一个方法里进行大量if-else判断。..
class Displayer2 {
    public void display(A2 a) {
        System.out.println("处理A2的业务逻辑");
    }
    public void display(D12 d12){
        System.out.println("处理D12业务逻辑");
    }

}


// 现在，我们的displayer单方面的需要 A/D1/D2 作为参数，而要实现双分派，就需要两者互相作为参数
class A2 {
    public void method(Displayer2 displayer2) {

        displayer2.display(this);
    }
}

class D12 extends A2 {
    public void method(Displayer2 displayer2) {

        displayer2.display(this); // 这里的this当然就是需要被展示的对象
    }
}