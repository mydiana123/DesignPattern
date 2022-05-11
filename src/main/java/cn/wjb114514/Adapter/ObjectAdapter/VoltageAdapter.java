package cn.wjb114514.Adapter.ObjectAdapter;

public class VoltageAdapter implements Voltage5V {

    // 被适配者和适配器是聚合关系
    private Voltage220V voltage220V ;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {

        // 检查被适配者的合法性
        int dst = 0;
        if (null != voltage220V) {
            // 获得被适配者的电压
            int src = voltage220V.output220V();
            // 进行适配器的处理
            dst = src / 44;
            System.out.println("适配完成，输出电压为v="+dst);
        }
        // 返回
        return dst;
    }
}
