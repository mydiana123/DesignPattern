package cn.wjb114514.decoration;

// Component层和ConcreteComponent层之间的进一步分层
public class Coffee  extends Drink{

    @Override
    public float cost() {
        // 可以看到分层带来的缺点就是 本来一层可以完成的代码需要重复包装
        return super.getPrice();
    }
}
