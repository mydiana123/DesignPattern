package cn.wjb114514.Factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class OrderPizza2 {
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
    public OrderPizza2() {
        do {
            String orderType = getType();
            Pizza pizza2 = SimpleFactory.createPizza2(orderType);
            if (Objects.nonNull(pizza2)) {
                pizza2.prepare();
            }else {
                System.out.println("没有这种类型的pizza！");
                break;
            }
        }while (true);
    }
}
