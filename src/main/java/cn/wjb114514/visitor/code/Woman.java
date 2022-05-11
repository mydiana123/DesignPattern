package cn.wjb114514.visitor.code;


/**
 * 双分派：
 * 1.现在客户端程序中，将具体状态作为参数传到Woman中(第一次分派)
 * 2.然后Woman调用了传入的具体状态的方法，并把自己作为参数，传入了具体状态方法(第二次分派)。
 * 这种分派方式 达到了解耦，并且提供了 具体状态和Woman的多元化组合 [接口编程 替换大量if-else]
 */
public class Woman extends Person{
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
