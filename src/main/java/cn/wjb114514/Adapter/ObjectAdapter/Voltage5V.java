package cn.wjb114514.Adapter.ObjectAdapter;

public interface Voltage5V {
    // 这里的接口只提供一个实现的规范，也就是我们期望获得5V电压，这个期望就可以直到适配器接下来怎么干
    // 所以适配器需要实现用户的期望。
    int output5V();
}
