package cn.wjb114514.prototype;

public class Client {
    public static void main(String[] args) {
        // 传统方法实现克隆羊
        /*
        缺点：
        1.优点是比较好理解 简单易操作
        2.但是：创建新对象时，总要获取原始对象的所有属性，如果创建的对象比较复杂，要获取较多的属性，效率低
        3.总要重新初始化对象，不能动态获取，原始对象改变时，其他对象没变化
        改进：
        Object提供了clone()方法，用于克隆对象。如果一个类实现了标记接口Cloneable，就可以克隆别人或者被克隆
         */
        Sheep sheep = new Sheep("tom", 1, "白色");
        Sheep sheep1 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        Sheep sheep2 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        Sheep sheep3 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        Sheep sheep4 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        System.out.println("第一只羊:" + sheep);
        System.out.println("第二只羊:" + sheep1);
        System.out.println("第三只羊:" + sheep2);
        System.out.println("第四只羊:" + sheep3);
        System.out.println("第五只羊:" + sheep4);
    }
}
