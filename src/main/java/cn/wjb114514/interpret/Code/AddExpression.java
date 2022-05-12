package cn.wjb114514.interpret.Code;

import java.util.HashMap;

// 加法解析器
public class AddExpression extends SymbolExpression {

    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    // 处理相加操作
    // var仍然是 变量-具体值 的集合。
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        // super.left.interpreter(var) ==> var是一个符号表达式的集合，，使用左表达式按照 符号表达式的映射，获取左表达式的值。
        return super.left.interpreter(var) + super.right.interpreter(var);
    }
}
