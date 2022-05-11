package cn.wjb114514.combination.Code;

/**
 * 好处：增加一个新类型的节点，很方便。
 * 维护一个树形结构，树上的每个节点都抽象为统一的组织对象，忽略其中组织大小的划分，也就是虽然学校包含学院，学院包含系，但仍把他们 看做统一的组织
 * 具体的层次关系体现在了树形结构。
 *
 * 组合模式在操作的对象里的纵向层次不是那么重要，而且操作方法类似，结构符合树形结构时很适用。
 *
 * 比如本网站的类型下拉条就是组合模式。提供一个类型对象接口，实现子类A一级类型，B二级类型。可以看到，如果用B继承A来实现这样的“一级和二级的层次关系”，明显太蠢了。因为操作B的时候还要考虑A会不会被操作。采取的做法是，对于这种层次关系不是很重要的，可以采取统一的接口，把A和B看做同一个树结构里的对象。A和B都持有可操作的对象集合，即子类型的List。以及操作方法
 */
public class Client {
    public static void main(String[] args) {
        // 测试组合模式
        // 从大到小创建树形结构
        OrganizationComponent university = new University("清华大学", "中国顶级大学");

        OrganizationComponent college1 = new College("计算机学院", "计算机学院");
        OrganizationComponent college2 = new College("信息工程学院", "信息工程学院");

        university.add(college1);
        university.add(college2);

        // 创建各学院的院系
        college1.add(new Department("软件工程","薪资挺高"));
        college1.add(new Department("网络工程","yyds"));
        college1.add(new Department("计算机科学与技术","老盘专业"));

        college2.add(new Department("通信工程","挺难的"));
        college2.add(new Department("信息工程","好学"));

        university.print();
    }
}
