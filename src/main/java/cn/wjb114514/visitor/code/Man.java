package cn.wjb114514.visitor.code;

public class Man extends Person{
    @Override
    public void accept(Action action) {
        // 可以看到，我们通过接口编程，即传入接口的实现子类 完成对不同逻辑的调用，省略大量if-else判断
        action.getManResult(this);
    }
}
