package cn.wjb114514.visitor.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 优点：让程序具有优秀的扩展性，灵活性非常高
 * 可以对功能进行统一 [报表，UI,拦截器，过滤器]
 * 缺点：具体的元素对访问者公布细节，也就是访问者关注了其他类的细节[element类和visitor类耦合大]
 * 违背了依赖倒转原则：访问者依赖了具体的元素
 * 就是双分派造成了 element和visitor耦合死了，element和visitor由于双分派 必须互相知道彼此的细节
 * 而且由于双分派，element[Person]和Visitor[Action]
 *
 * Visitor必须依赖具体的实现子类，违反了 依赖倒转。因为Visitor应该依赖更底层的抽象层Person
 * 但是由于Visitor需要根据传入的具体子类型判断业务逻辑，就不能用接口接受...
 * 同理，Element必须知道Visitor的方法细节，才可以调用，相当于 Visitor并没有给Element一个完全封闭的方法接口。
 * 按照迪米特法则，Element只管调用Visitor提供的方法，不需要知道其内部细节，但是在访问者模式里，Element需要一定程度上了解Visitor干了什么...
 *
 * 什么时候选择访问者
 * 1.稳定的数据结构
 * 2.经常变化的功能需求
 *
 *  三个成分
 *  1.Element Visitor : 通过双分派 完成了重载方法的 “伪动态加载” 但是由于双分派，二者耦合度较高
 *  2.Structure ： 研究的数据结构，可以认为是一个系统。
 *
 */
public abstract class Action {

    // 得到男性的 测评
    public abstract void getManResult(Man man);
    // 得到女性的测评
    public abstract void getWomanResult(Woman woman);


}
