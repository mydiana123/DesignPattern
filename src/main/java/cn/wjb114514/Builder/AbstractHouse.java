package cn.wjb114514.Builder;

/**
 * 缺点：产品和产品的创造过程进行了耦合！
 * House是产品对象，而产品对象内部居然耦合了创建产品对象的方法。
 * 如果用户想要自定义一个产品对象，就需要同时完成创建产品对象的方法。就比如设计师设计一款汽车，还要兼顾这款汽车是怎么造的，不是很合理
 *
 * 建造者模式：Builder Pattern 或者生成器模式，是一种创建型模式
 * 可以把复杂对象的建造过程抽取出来，使得这个抽象过程的不同实现方法，可以构造出不同表现类型的对象
 * 建造者模式一步一步的创建复杂的对象，他允许用户只通过指定复杂对象的类型和内容就可以创造它们，不需要了解内部的细节。
 *
 * 四个角色：
 * 1.Product：具体的产品对象
 * 2.Builder：抽象建造者：制定一个建造流程的规范，具体细节不管
 * 3.ConcreteBuilder：具体建造者：是抽象建造者的子类，用于描述具体的制造流程
 * 4.Director：用于创建一个复杂的对象，隔离了产品与产品对象的生产过程，隔离了客户与产品对象的生产过程
 *
 */
public abstract class AbstractHouse {
    public abstract void buildBase();
    public abstract void buildWalls();
    public abstract void roofed();

    public void build() {
        buildBase();
        buildWalls();
        roofed();
    }
}
