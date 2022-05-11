package cn.wjb114514.Adapter;

public class Phone {
    public void charging(Voltage5V v) {
        if (v.output5V() == 5) {
            System.out.println("电压为5伏特，可以充电");
        }else {
            System.out.println("现在电压不适合冲");
        }
    }
}
