package cn.wjb114514.interpret;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * parser 对象 就是构建抽象语法树的对象，此对象返回一个代表抽象语法树的 Expression对象
 */
public class SourceCode {
    public static void main(String[] args) {
        // 看看 spEl(Spring Expression Language) 是怎么被解析的
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

        // 这个parseExpression()方法，会根据不同的Parser对象，返回不同的Expression子类
        /*
            ExpressionParser <|-- TemplateAwareExpressionParser
            TemplateAwareExpressionParser <| SpelExpressionParser
            TemplateAwareExpressionParser <| InternalSpelExpressionParser

            Expression <|-- CompositeStringExpression,SpelExpression,LiteralExpression
            // Expression的具体实现子类，是根据Parser对象类型的不同而决定的。
            其中，Parser的parseExpression()方法体现了这一点
         */
        Expression expression = spelExpressionParser.parseExpression("1 + 2  + 5 + 4 * 45");
        Object value = expression.getValue();
        System.out.println(value);

    }
}

/*
 *
 *
 *
 *
 *     public Expression parseExpression(String expressionString, @Nullable ParserContext context) throws ParseException {
 *         return context != null && context.isTemplate() ? this.parseTemplate(expressionString, context) : this.doParseExpression(expressionString, context);
 *     }
 *
 */