package cn.wjb114514.combination.Code;


import java.util.ArrayList;
import java.util.List;

// Component的具体子部件A composite
public class University extends OrganizationComponent{
    // 此集合就能体现树的结构
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String des) {
        super(name, des);
    }


    @Override
    protected void add(OrganizationComponent organizationComponent) {
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
