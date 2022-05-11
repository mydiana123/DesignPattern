package cn.wjb114514.Factory.FactoryMethod;

import cn.wjb114514.Factory.Pizza;
import cn.wjb114514.Factory.SimpleFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public abstract class OrderPizza {

    // 就在这里体现了抽象方法模式！！
    // 具体实现由子类完成
    public abstract Pizza createPizza(String orderType);

    public OrderPizza() {
        do {
            String orderType = getType();
            Pizza pizza2 = createPizza(orderType);
            if (Objects.nonNull(pizza2)) {
                pizza2.prepare();
                pizza2.box();
            }else {
                System.out.println("没有这种类型的pizza！");
                break;
            }
        }while (true);
    }


    private String getType() {
        try {
            // 从标准输入获取输入字符流，转换为缓冲字符流
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input Pizza Type: ");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
