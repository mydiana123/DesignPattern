package cn.wjb114514.interpret.Code;

import java.util.HashMap;

/**
 * 变量解析器，主要解析
 */
public class VarExpression extends Expression {

    private String key; // 表达式中 具体的变量

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key); // 获取此变量 绑定的数值
    }
}
