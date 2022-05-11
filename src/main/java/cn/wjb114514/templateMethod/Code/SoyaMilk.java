package cn.wjb114514.templateMethod.Code;

public abstract class SoyaMilk {
    // 模板方法：由父类提供统一的算法框架， 不允许子类重写，防止覆盖框架，因此作为final
    final void make() {
        select();
        addCondiments();
        soak();
        beat();
    }

    // 选材[这些所有实现类都一样的方法，可以做成私有]
    void select() {
        System.out.println("1.选择美味的，新鲜的黄豆");
    }
    // 添加不同的配料
    abstract void addCondiments();

    void soak() {
        System.out.println("3.黄豆和配料浸泡3h");
    }

    void beat() {
        System.out.println("4.到豆浆机去打");
    }
}
