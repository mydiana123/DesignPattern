package cn.wjb114514.Adapter;

// 继承被适配者，实现 适配接口，用于输出预期值
public class VoltageAdapter extends Voltage220V implements Voltage5V{
    @Override
    public int output5V() {
        // 获得被适配者的电压
        int src = output220V();
        // 进行适配器的处理
        int dst = src / 44;
        // 返回
        return dst;
    }
}
