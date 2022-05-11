package cn.wjb114514.visitor.code;

/**
 * 访问者模式，和桥接模式 有些类似...
 *
 * 很多设计模式都体现了桥接的概念
 *
 * 就是把两个直接耦合的对象，通过桥接层，进行解耦。
 *
 * 也就是 A==耦合==B
 *
 * A==耦合==桥接层==耦合==B
 *
 * 其中桥接层把B的细节隐藏，达到了A==解耦==B的效果
 *
 * 双分派：
 */
public class Client {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());
//        objectStructure.attach(new Man());

        Success success = new Success();
        objectStructure.display(success);
        // 评价完 就从集合里移除
        objectStructure.detach(null);

        Fail fail = new Fail();
        objectStructure.attach(new Man());
        objectStructure.display(fail);
    }
}
