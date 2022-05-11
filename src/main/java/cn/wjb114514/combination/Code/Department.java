package cn.wjb114514.combination.Code;

import java.util.ArrayList;
import java.util.List;

public class Department extends OrganizationComponent {
    // 不管理集合，此组织没有子节点
    // List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public Department(String name, String des) {
        super(name, des);
    }


    // 由于当前的Department是叶子结点，，没有需要管理的子节点，故不支持管理节点的方法
//    @Override
//    protected void add(OrganizationComponent organizationComponent) {
//        organizationComponents.add(organizationComponent);
//    }
//
//    @Override
//    protected void remove(OrganizationComponent organizationComponent) {
//        organizationComponents.remove(organizationComponent);
//    }


    @Override
    protected void print() {
        // 输出学校包含的学院
        System.out.println(getName());
        // for (OrganizationComponent organizationComponent : organizationComponents) {
            // 循环代替递归 打印子组织。
        //    organizationComponent.print();
        // }
    }
}
