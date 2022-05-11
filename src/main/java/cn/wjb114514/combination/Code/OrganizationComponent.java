package cn.wjb114514.combination.Code;

// 组合模式中的Component，把其所有实现类 以相同的性质进行规范，都看做一个组织，而不关注组织的大小，提供统一的操作方法和属性
public abstract class OrganizationComponent {
    private String name; // 名字
    private String des;  // 描述

    protected void add(OrganizationComponent organizationComponent) {
        // 默认实现，因为叶子节点没有子节点，不会操作别人，只会被操作，所以叶子结点不用实现Component提供的操作规范
        throw new UnsupportedOperationException();
    }

    // OrganizationComponent作为形参，代表一个可被操作的节点。即当前节点要操作的子节点
    protected void remove(OrganizationComponent organizationComponent) {
        // 提供默认实现的方法就是扔一个不支持的操作异常，此异常表示如果不主动实现父类的方法，父类方法是用不了的
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent(String name, String des) {
        super();
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    // 此方法print不属于操作性质的方法，所以无论有没有操作的能力，都必须实现
    protected abstract void print();
}
