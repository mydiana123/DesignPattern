package cn.wjb114514.SingleTon;

/**
 * 单例模式，就是采取一定的方法， 保证在整个软件系统里，对某个类只存在一个对象实例，并且该类只提供一个取得其对象实例的方法(一般是静态方法)
 * 比如hibernate 的SessionFactory:充当数据存储源的代理，并且负责存储Session，这就是一个重量级的对象，只需要一个
 *
 * 单例模式保证了整个系统内存里，只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，单例模式可以提高系统性能
 * 想实例化一个单例类的时候，不要使用new关键字，人家也不给你new 的机会，构造方法是私有的，而是使用共有的get方法获取
 * 使用场景：频繁创建和销毁的对象，创建对象耗时过多或者消耗资源过多(重量级对象) 但又常使用的对象，工具类对象，频繁访问数据库或文件的对象(dataSource,session工厂)
 *
 */
public class Start {
    public static void main(String[] args) {
        SingleTon instance1 = SingleTon.getInstance();
        SingleTon instance2 = SingleTon.getInstance();
        System.out.println(instance1 == instance2); // true
        System.out.println(instance1.hashCode()); // 460141958
        System.out.println(instance2.hashCode()); // 460141958

        SingleTon03 instance = SingleTon03.getInstance();
        SingleTon03 instance3 = SingleTon03.getInstance();
        System.out.println(instance == instance3); // true

        // 推荐使用双重检查单例对象
        DoubleCheckSingleTon instance4 = DoubleCheckSingleTon.getInstance();
        DoubleCheckSingleTon instance5 = DoubleCheckSingleTon.getInstance();
        System.out.println(instance4 == instance5); // true

        SingleTon08 instance6 = SingleTon08.INSTANCE;
        SingleTon08 instance7 = SingleTon08.INSTANCE;
        System.out.println(instance6 == instance7);
        instance6.sayOK();
    }
}

/**
 * 饿汉式1(静态常量)
 * 优缺点:
 * 优点:操作简单:装载类时就拿到了对象实例，没有线程安全问题
 * 缺点:无法达到懒加载的目的，我们这个实例可能用不到，这样就导致了不必要的实例加载。
 * 导致类装载的原因有很多，但是这种方法一旦类装载了，就会导致单例对象实例化。有可能造成资源的浪费
 * 结论：单例模式可以使用，但是会造成内存浪费！ 在我们确定一定会用到此类时，可采取这种方法~
 */
class SingleTon {
    // 1.构造器私有化：防止new出来
    private SingleTon() {

    }
    // 2.在本类内部创建一个对象实例(static的成员变量，在类的加载时，就初始化)
    private final static SingleTon instance = new SingleTon();

    // 3.提供一个获取单例对象的方法
    public static SingleTon getInstance() {
        return instance;
    }
}

/**
 * 饿汉式2(静态代码块)
 */
class SingleTon02 {
    // 1.构造器私有化：防止new出来
    private SingleTon02() {
    }

    static {
        instance = new SingleTon02();
    }
    // 2.在本类内部创建一个对象实例(static的成员变量，在类的加载时，就初始化)
    // final的作用可以 使得类加载不经过初始化一步，可以减少负担
    private static final SingleTon02 instance ;

    // 3.提供一个获取单例对象的方法
    public static SingleTon02 getInstance() {
        return instance;
    }
}

/**
 * 懒汉式1(懒加载的单例模式)
 * 线程不安全:
 * 原因：if (instance == null) 这个判断语句会引发线程安全问题
 * 如果线程1和2都来执行这个代码，那么，线程1和2由于此时没有instance对象，都会进入到这个判断语句，分别new一个instance出来
 * 这样线程1得到了自己new的instance，线程2也得到自己new的instance，相当于instance被new了两次，那肯定不是单例的~
 */
class SingleTon03 {
    private static SingleTon03 instance;
    private SingleTon03() {

    }
    // 提供一个静态公有方法，当使用到该方法时，才会去创建instance~
    public static SingleTon03 getInstance(){
        if (instance == null) {
            instance = new SingleTon03();
        }
        return instance;
    }
}

/**
 * 在有线程安全问题的代码处，使用同步代码块(同步方法)
 * 也就是说，执行获取instance的方法时，只允许一个线程进入，这样就不会同时执行线程不安全的代码 if(instance == null)
 * 优点：线程安全
 * 缺点：同步代码带来的开销很大，这种单例对象，往往是重量级的，使用频繁的，所以不推荐使用，效率太低~！
 */
class SingleTon04 {
    private static SingleTon04 instance;
    private SingleTon04() {

    }
    // 提供一个静态公有方法，当使用到该方法时，才会去创建instance~
    public synchronized static SingleTon04 getInstance(){
        if (instance == null) {
            instance = new SingleTon04();
        }
        return instance;
    }
}

/**
 * 使用同步代码块里解决
 * 注意：同步代码块的位置一定要加在出现线程安全的语句，通常是if语句 导致多个线程同时进入，所以我们一般在if方法里限制进入线程的数量
 * 有人在new SingleTon05()的位置加同步代码块，这是没用的，因为线程安全问题不是发生在new instance的地方，而是if语句的位置
 */
class SingleTon05 {
    private static SingleTon05 instance;
    private SingleTon05() {

    }
    // 提供一个静态公有方法，当使用到该方法时，才会去创建instance~
    public synchronized static SingleTon05 getInstance(){
        synchronized (SingleTon05.class) {
            if (instance == null) {
                instance = new SingleTon05();
            }
        }
        return instance;
    }
}

/**
 * 单例模式：双重检查（推荐使用）
 * 懒加载+线程安全+不用每次调用方法都去执行同步代码~，效率高
 * 这种方式在其他语言也有体现，很重要！！
 */
class DoubleCheckSingleTon {
    // volatile关键字表示不对变量进行任何缓存操作，只要修改就立即更新到主存(本意是不稳定的，易变的，可以理解为 "我很不稳定，很易变，所以就不要缓存我了")
    // 所谓缓存就是对那些不容易变化的变量进行缓存，但是既然我们声明了一个对象是volatile的，那就说明：不建议缓存了。
    // 此关键字是CPU层面的，很底层！
    private static volatile DoubleCheckSingleTon instance;
    private DoubleCheckSingleTon(){}
    public static DoubleCheckSingleTon getInstance() {
        // 最外层的if语句不涉及同步代码块，执行效率快。因此如果有一个线程创建出了单例对象，其他线程不会再执行同步代码块，直接用之前线程创建的实例
        if (instance == null) {
            synchronized (DoubleCheckSingleTon.class) {
                // 只有首次发现instance为null的线程才会执行同步代码块。此后的所有线程都无需再执行同步代码，效率高
                if (instance == null) {
                    instance = new DoubleCheckSingleTon();
                }
            }
        }
        return instance;
    }
}

/**
 * 单例模式：静态内部类
 * 外部类初次加载，会初始化静态变量、静态代码块、静态方法，但不会加载内部类和静态内部类。
 * 实例化外部类，调用外部类的静态方法、静态变量，则外部类必须先进行加载，但只加载一次。
 * 直接调用静态内部类时，外部类不会加载。
 * 这里可以简单的认为，静态内部类也有自己的名字，以及对应的字节码文件 outer$inner.class的形式
 * 所以外部类和静态内部类其实可以看成"同级关系"，即都是类层面的关系，而不是说父类和子类的关系
 * 我们知道加载子类一定要加载其父类，可是外部类和内部类没有父子关系，所以就不存在加载的传递！
 *
 * 优点：运用jvm的底层机制保证了线程安全，推荐使用！
 * 而且外部类的加载不会导致内部类的加载，实现了懒加载
 */
class SingleTon07 {
    private SingleTon07() {}
    // 1.外部类装载时，静态内部类不会立即加载
    // 2.调用内部类的方法时，才会使得内部类加载，但仅加载一次
    // 我们使用JVM 的底层机制，即JVM加载类的时候必须是线程安全的
    private static class SingleTonInstance {
        private static final SingleTon07 instance = new SingleTon07();
    }
    public static SingleTon07 getInstance() {
        return SingleTonInstance.instance;
    }
}

/**
 * 推荐使用
 * 借助jdk1.5添加的枚举，可以保证线程安全，而且还能防止反序列化重新创建对象
 * 枚举应该是饿汉式？
 */
enum SingleTon08 {
    INSTANCE;
    public void sayOK() {
        System.out.println("ok");
    }
}