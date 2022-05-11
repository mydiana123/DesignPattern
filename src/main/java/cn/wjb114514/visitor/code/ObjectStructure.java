package cn.wjb114514.visitor.code;

import java.util.LinkedList;
import java.util.List;

/**
 * Element 和 Visitor采取双分派模式。
 * 即Element需要Visitor作为参数，调用Visitor的方法
 * 同时调用Visitor的方法时，Element需要把自己作为参数传入
 * 双分派主要是因为 可以在重载时完成动态绑定
 * 如果不用双分派，也就是说Visitor的
 */
public class ObjectStructure {
    // 内部维护一个集合
    private List<Person> persons = new LinkedList<>();

    // 针对集合的操作
    public void attach(Person p){
        persons.add(p);
    }
    public void detach(Person p) {
        persons.remove(p);
    }
    public void display(Action a) {
        for(Person p : persons){
            p.accept(a);
        }

    }
}
