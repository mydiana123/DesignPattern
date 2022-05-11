package cn.wjb114514.bridge;

// refinedAbstraction
public class FoldedPhone extends Phone{
    // 由于初始化子类时，必须加载父类，因此子类需要提供创建父类的构造器需要的内容
    public FoldedPhone(Brand brand) {
        super(brand);
    }


    @Override
    public void open() {
        super.open();
        System.out.println("折叠手机");
    }
    @Override
    public void close() {
        super.close();
        System.out.println("折叠手机");
    }
    @Override
    public void call() {
        super.call();
        System.out.println("折叠手机");
    }
}
