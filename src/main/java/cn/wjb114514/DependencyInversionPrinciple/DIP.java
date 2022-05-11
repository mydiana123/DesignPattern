package cn.wjb114514.DependencyInversionPrinciple;

/**
 * 依赖倒转原则：
 * 1.高层模块不应该依赖底层模块，二者都应该依赖其抽象(抽象类/接口)
 * 2.抽象不应该依赖细节，细节应该依赖抽象
 * 3.依赖倒转的中心思想是面向接口编程
 * 4.设计理念：相对于细节的多变性，抽象的东西要稳定的多，以抽象为基础搭建的架构，比以细节为基础的架构要稳定的多，在java中，抽象指的是抽象类/接口。细节就是具体实现类
 * 5.使用接口/抽象类的目的 是制定好规范，而不涉及任何具体的操作，把展现细节的任务交给他们的实现类完成。
 *
 * java中依赖传递的方式
 * 1.接口传递 类A的方法参数可以是一个接口I，我们传入I的实现类B作为类A方法的参数，就造成了A对B的 依赖。因此A通过接口I和B产生了依赖。即只有B实现了I的功能，才能完成A的功能。
 * 2.构造器传递 (类似Spring依赖注入的方式) 类A的构造器接受一个接口I类型的参数，通过this.xx 把类A内部成员变量进行赋值。这样类A 有实现了接口I的类B类型的成员变量，，就产生了类A内部的依赖：类B
 * 3.setter方法传递，和2同理，类A的setter方法的参数是一个接口I，传入实现了I的类B时，就完成了类A的内部成员变量注入。
 * 较为典型的，比如service类内部有dao层的成员变量，比如UserService需要UserDao完成数据库的操作，这样UserService内部依赖了UserDao
 *
 * 实现方式：
 * 1.底层模块都尽量要有抽象类和接口，因为底层模块可能有各种各样的实现类(遵循单一职责导致实现类很多).所以我们使用接口这一缓冲层，便于操作和调度各实现类
 * 2.变量的声明类型尽量是抽象类或者接口(这是因为多态，可以是父类类型子类引用)，存在一个缓冲层，利于程序扩展和优化
 * 3.继承时遵循里氏替换原则。
 *
 * 使用接口作为类型参数，就可以充分的调用各实现类
 * 就比如一个父亲有各种各样专业的人才，父亲就是接口，孩子们就是实现类，如果一个方法的参数是父亲，那么其各种各样的子类都可以传入
 * 这样这个方法可以传入各种各样专业的人才，实现了实现类(孩子)的充分调用，功能也更加灵活强大
 */
public class DIP {
    public static void main(String[] args) {
        new Person().receive(new Email()); // 电子邮件信息:hello,world!
        new Person2().receiver(new Instagram()); // Instagram发来的信息:hello,world!
        new Person2().receiver(new QQ()); // qq发来的信息:hello,world!
    }
}

class Email {
    public String getInfo() {
        return "电子邮件信息:hello,world!";
    }
}
class WeChat {
    public String getInfo() {
        return "微信信息:hello,world!";
    }
}
// 实现一个类Person，完成接收信息的功能
// 方式1：好处：比较好想，代码简单
class Person {
    // Person类依赖了Email，硬编码性高！
    // 如果需要 WeiXin或者QQ的信息，receive方法需要重载。
    public void receive(Email email){
        System.out.println(email.getInfo());
    }
}

// 方法2
// 解决思路：引入一个抽象的接口IReceiver，Person类与抽象的接口发生依赖
// Wechat和Email都是具体的，细节的，我们对细节的依赖，会导致硬编码，对接口的依赖实现了一个"缓冲层"
// 在缓冲层上，可以有更多的灵活性。
/*
                            类A
                             |
                        依赖接口I(缓冲层)
                         /    |     \
                     实现类i1 实现类i2 实现类i3
         我们可以通过缓冲层，操作i1，i2以及in，降低了硬编码，提高了灵活性。
         如果没有缓冲层，我们需要直接操作i1，i2，in 及其不灵活~
 */

interface IReceiver {
    String getInfo();
}
class QQ implements IReceiver {

    @Override
    public String getInfo() {
        return "qq发来的信息:hello,world!";
    }
}

class Instagram implements IReceiver{
    @Override
    public String getInfo() {
        return "Instagram发来的信息:hello,world!";
    }
}

class Person2 {
    public void receiver(IReceiver iReceiver) {
        System.out.println(iReceiver.getInfo());
    }
}