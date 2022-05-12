package cn.wjb114514.interpret.Code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String expStr = getExpStr();
        HashMap<String, Integer> map = getValue(expStr);
        Calculator calculator = new Calculator(expStr);
        System.out.println("计算结果" + expStr + "==>" + calculator.run(map));
    }

    private static String getExpStr() throws IOException {
        System.out.println("请输入表达式");
        return (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }

    // 获取值的映射: 主要是针对非 运算符号的表达式，进行值的解析
    public static HashMap<String, Integer> getValue(String expStr) {
        HashMap<String, Integer> map = new HashMap<>();

        for(char ch : expStr.toCharArray()) {
            if (ch != '+' && ch != '-') {
                if (!map.containsKey(ch)) {
                    System.out.println("请输入" + ch + "的值:");
                    int i = new Scanner(System.in).nextInt();
                    map.put(String.valueOf(ch),i);
                }
            }
        }
        return map;
    }

}
