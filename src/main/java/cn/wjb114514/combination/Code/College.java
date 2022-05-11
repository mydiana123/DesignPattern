package cn.wjb114514.combination.Code;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们在生成 此类时，基本上是把University类的所有内容拷贝一份过来
 * 这就体现了接口编程，也就是OrganizationComponent这个对象的优越性
 * 他把具有层次关系的对象抽象成同一个组织对象[树的一个节点]，并制定了对某一节点的操作方法，十分方便
 * 相比于用继承实现这些组织内部的包含关系，太死板，不如用父节点内部聚合子节点的方式，抽象成树状结构，
 * 这又是组合代替继承的规范，避免了继承导致的操作困难，把整体抽象为一个树结构，层次明显，操作简单。
 */
public class College extends OrganizationComponent{
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String des) {
        super(name, des);
    }


    @Override
    protected void add(OrganizationComponent organizationComponent) {
        // 当然，这里的add方法可能 有不同的业务逻辑，根据组织的功能差异性，实现不同的业务逻辑
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }


    @Override
    protected void print() {
        // 输出学校包含的学院
        System.out.println("-------------------" + getName() + "----------------");
        for(OrganizationComponent organizationComponent : organizationComponents) {
            // 循环代替递归 打印子组织。
            organizationComponent.print();
        }
    }
}
