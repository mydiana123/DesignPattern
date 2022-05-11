package cn.wjb114514.LiskovSubstitutionPrinciple;

/**
 * 继承的缺点
 * 1.继承包含这么一层含义:凡是父类实现好的方法，实际上是在设定规范和契约，虽然它不强制要求子类必须遵循这些契约，但是如果子类对已实现的方法随意重写，会导致整个继承体系的破坏
 * 比如HttpServlet的service方法，就表明了:我们自己的子类继承HttpServlet时 闲来无事不要重写service方法。
 * 2.使用继承会给程序带来侵入性，程序的可移植性降低，增加对象的耦合性，如果一个类被其他的类继承，那么修改这个类，要兼顾所有的子类。
 *
 * 里氏替换原则:
 * 1.引用基类的地方要能透明地使用其子类的对象
 * 2.使用子类方法时，尽量不要重写父类的方法
 * 3.里氏替换原则告诉我们:继承让两个类的耦合性增强了， 在适当的情况下，可以通过聚合，组合，依赖完成。
 * 比如类A继承类B，之后A把B的所有方法都重写了，这样B无法调用A的任何方法，因为功能都不一样，这就不能透明地使用子类对象。所以我们可以用一个抽象类C，让A和B继承更基础的类
 *
 * 解决方式:让依赖性高的 具有继承关系的A和B类，继承更加基础的基类Base。
 * 这样A和Base是继承，B和Base也是继承
 * A和B之间是依赖，聚合，组合的关系
 * 继承的目的是提供功能更加专一且强大的子类，或者提供一个模板供我们自己设计子类，但是滥用继承会导致如上的种种错误，这就引入了里氏替换原则
 * */
public class LSP {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("11-3="+a.func1(11,3)); // 11-3=8
        B b = new B();
        // B本来想使用父类A的func1方法，但是B类无意间重写了A的方法。
        System.out.println("11-3="+b.func1(11,3)); // 11-3=14 本质是求了11+3，因为B类的设计者不知道-3操作被+3重写了，B的设计者本意不是去重写A类
        System.out.println("11-3-9="+b.fun2(11,3)); // 11-3-9=5 本质是求了11+3-9，是因为B类的设计者不知道-3的操作被+3重写了
        // 使用改进设计后的B完成方法
        B_ b_ = new B_();
        System.out.println("11+3="+b.func1(11,3)); // 11+3=14 这下B_可以很明确的知道自己没有重写A_的方法，因为二者不存在继承的依赖关系
        // B是B的方法，A是A的方法
    }
}

class Base {
    // 把更基础的方法和成员写到Base类
}

class A_ extends Base {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

class B_ extends Base {
    public int func1(int num1, int num2) {
        return num1 + num2;
    }
    // 使用组合方式，在B类中引用A的方法
    A_ a = new A_();
    public int func3(int a, int b) {
        return this.a.func1(a,b)+9;
    }
}

// A类有一个基础方法func1，返回两数之差
class A {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

class B extends A {
    // B无意间重写了A的方法
    @Override
    public int func1(int num1, int num2) {
        return num1 + num2;
    }
    public int fun2(int num1,int num2) {
        return func1(num1,num2) - 9;
    }
}
