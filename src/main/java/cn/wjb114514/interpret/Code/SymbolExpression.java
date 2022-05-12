package cn.wjb114514.interpret.Code;

import java.util.HashMap;

/**
 * 抽象运算符号解析器 ==> 每个运算符号 都只和自己的左右两个数字有关系
 * 但左右两个数字有可能也是一个解析的结果，无论何种类型，都是Expression类的实现类
 */
public class SymbolExpression extends Expression{

    // 一个运算符两边 可能是一个数，也可能是一个表达式，比如 a+b 。对+来说，两边都是一个数[当然也可以看做变量表达式]
    // 而 a-b+c。对于+来说，左边是一个减法表达式。
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    // SymbolExpression是由子类实现的，因此此方法是一个空实现。
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}
