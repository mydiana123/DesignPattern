package cn.wjb114514.Factory.AbstractFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {
    // 通过set方法完成OrderPizza对absFactory的聚合
    // 工厂的实现类只负责创建对象，其他的业务逻辑供上层类完成
    AbsFactory absFactory;

    public OrderPizza(AbsFactory absFactory) {
        setAbsFactory(absFactory);
    }
    // 这里要求传入AbsFactory的实现子类，依赖于java的多态，实现了此方法的多样性
    private void setAbsFactory(AbsFactory absFactory) {
        this.absFactory = absFactory;
        Pizza pizza = null;
        do {
            String orderType = getType();
            pizza = absFactory.createPizza(orderType);
            if (pizza != null) {
                pizza.box();
            }else {
                System.out.println("没有这种类型的pizza");
                break;
            }
        }while (true);
    }


    // 获取用户希望订购的披萨种类
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
