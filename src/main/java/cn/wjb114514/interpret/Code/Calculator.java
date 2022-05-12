package cn.wjb114514.interpret.Code;

import java.util.HashMap;
import java.util.Stack;

public class Calculator {

    private Expression expression; // 待计算的表达式

    // 直接使用构造器传参，并进行解析
    public Calculator(String expStr) {
        // 1.安排运算先后顺序
        Stack<Expression> stack = new Stack<>();
        // 2.拆分为字符数组，进行抽象语法树的构建
        char[] charArray = expStr.toCharArray();

        Expression left = null;
        Expression right = null;

        // 3.遍历字符数组 [这里主要是针对表达式的处理，根据不同的表达式 可以做不同的处理。如果引入了更复杂的表达式，这里的业务逻辑也会更复杂，不过大体上都使用栈完成的]
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '+': // 解析到一个加法运算
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i])); // 我们目前只能计算 +/- 而且是中缀表达式，所以 一个符号之后一定是一个变量。如果涉及到 括号，负号等，就不能这么简单的判断了。
                    stack.push(new AddExpression(left, right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left, right));
                    break;
                default: // 解析到一个变量字面串
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
            }
        }

        // 如果正确，最后栈顶一定是一个 被解析为语法树的表达式
        /*
            语法树的形状 [大概就是这样
   a+b-c              -
                    +    c
                   / \
                  a  b
         */
        this.expression = stack.pop();
    }

    public int run(HashMap<String,Integer> var) {
        return this.expression.interpreter(var); // 按照子类的具体类型，进行抽象语法树的递归解析
    }

}
