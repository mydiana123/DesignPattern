package cn.wjb114514.Factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class OrderPizzaPlus {


    public OrderPizzaPlus(SimpleFactory simpleFactory) {
        setSimpleFactory(simpleFactory);
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

    SimpleFactory simpleFactory = null;
    Pizza pizza = null;
    public void setSimpleFactory(SimpleFactory simpleFactory) {
        String orderType = "";
        this.simpleFactory = simpleFactory;
        do {
            orderType = getType();
            // 创建对象的细节，封装到简单工厂里
            simpleFactory.createPizza(orderType);

            if (Objects.nonNull(pizza)) {
                pizza.prepare(); pizza.bake(); pizza.cut(); pizza.box();
            }else {
                System.out.println("没有这种类型的披萨~");
            }
        }while (true);


    }
}
