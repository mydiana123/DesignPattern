package cn.wjb114514.templateMethod.improve;

/**
 * 1.模板方法：规定其子类的执行逻辑都按照模板方法进行
 * 2.抽象方法：模板方法的某一步有所不同，按照子类实现而实现。比如制作豆浆的添加辅料，需要按照子类提供的方法实现 再比如beanFactory，需要按照子类提供的读取beanFactory的方法进行读取。但是无论是制作豆浆还是初始化beanFactory，都是按照模板方法指定的步骤做，只不过不同子类，在模板方法的执行流程中，有一些步骤是因子类而定的。
 * 3.钩子方法：此方法仅作为空实现，但是此方法的实现会干涉到模板方法的执行流程[但是由于不影响模板方法的结构，所有子类的实现逻辑仍然是不变的。钩子方法仅起到干涉作用，不影响模板的结构]
 *
 */
public abstract class SoyaMilk {
    // 模板方法：由父类提供统一的算法框架， 不允许子类重写，防止覆盖框架，因此作为final
    final void make() {
        select();
        if (customerWantCondiment())  addCondiments();
        soak();
        beat();
    }

    // 钩子方法：决定是否添加配料
    // 钩子的定义：通过一个方法可以影响[干涉]到另一个方法的行为
    // 也就是使用钩子，可以让子类 去干涉模板方法 的执行流程，但又不破坏算法框架。
    boolean customerWantCondiment() {
        return true;
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
