package cn.wjb114514.interpret.Code;

import java.util.HashMap;

/**
 * 抽象类表达式，
 * 形参为一个 hashMap键值对，可以获取到变量的值
 */
public abstract class Expression {

    // 解释 公式和数值的关系 key:表达式里的参数[a+b] a就是表达式中的参数， value:此参数的具体值
    // hashMap把符号和具体值进行了关联
    public abstract int interpreter(HashMap<String,Integer> var);
}
