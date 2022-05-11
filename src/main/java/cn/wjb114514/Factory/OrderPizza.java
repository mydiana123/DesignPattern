package cn.wjb114514.Factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 用于订购披萨的类
 */
public class OrderPizza {

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
    public OrderPizza() {
        Pizza pizza = null;
        // 订购披萨的类型
        String orderType;
        do {
            orderType = getType();
            // 这里的大量if-else语句，违反了ocp
            if ("greek".equals(orderType)) {
                pizza = new GreekPizza();
                pizza.setName(orderType + "pizza");
            }else if("cheese".equals(orderType)) {
                pizza = new CheesePizza();
                pizza.setName(orderType + "pizza");
            }
            // 违反开闭原则的代码！
            else if ("newType".equals(orderType)) {
                pizza = new NewTypePizza();
                pizza.setName(orderType + "pizza");
            }
            else {
                break;
            }
            // 输出pizza制作流程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }while (true);
    }
}
