package cn.wjb114514.prototype;

/**
 * 浅拷贝和深拷贝
 * 如果我们要克隆的对象，有一个对象类型呢?
 * 对于基本类型的变量(包括String)，会直接进行值拷贝，也就是把属性值复制一份
 * 对于引用类型的变量，比如数组或者类的实例对象，会把引用值(内存地址)复制一份
 * 我们克隆羊的朋友就是进行了浅拷贝
 *
 * 深拷贝：
 * 1.赋值对象的所有基本类型的成员变量值
 * 2.为所有引用数据类型的变量申请存储空间，并复制每个引用类型成员变量所引用的对象，直到完成所有标量的值拷贝。
 * 实现方式：
 * 1.重写clone()方法
 * 2.基于对象序列化
 *
 * 使用Lombok会导致克隆失效?应该是有些参数没设置
 */
public class Copy {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.friend = new Sheep("jack", 2, "黑色");
        try {
            /*
                第一只羊:Sheep{name='tom', age=1, color='白色'}
                第二只羊:Sheep{name='tom', age=1, color='白色'}
                第三只羊:Sheep{name='tom', age=1, color='白色'}
                第四只羊:Sheep{name='tom', age=1, color='白色'}
                第一只羊的朋友:460141958
                第二只羊的朋友:460141958
                第三只羊的朋友:460141958
                第四只羊的朋友:460141958
                四只羊的朋友指向了同一个对象
             */
            Sheep sheep1 = (Sheep) sheep.deepClone();
            Sheep sheep2 = (Sheep) sheep.deepClone();
            Sheep sheep3 = (Sheep) sheep.clone();
            Sheep sheep4 = (Sheep) sheep.clone();
            System.out.println("第一只羊:" + sheep1);
            System.out.println("第二只羊:" + sheep2);
            System.out.println("第三只羊:" + sheep3);
            System.out.println("第四只羊:" + sheep4);
            System.out.println("第一只羊的朋友:" + sheep1.friend.hashCode());
            System.out.println("第一只羊的朋友:" + sheep1.friend);
            System.out.println("第二只羊的朋友:" + sheep2.friend.hashCode());
            System.out.println("第三只羊的朋友:" + sheep3.friend.hashCode());
            System.out.println("第四只羊的朋友:" + sheep4.friend.hashCode());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        /*
            第一只羊:Sheep{name='tom', age=1, color='白色', friend=Sheep{name='jack', age=2, color='黑色', friend=null}}
            第二只羊:Sheep{name='tom', age=1, color='白色', friend=Sheep{name='jack', age=2, color='黑色', friend=null}}
            第三只羊:Sheep{name='tom', age=1, color='白色', friend=Sheep{name='jack', age=2, color='黑色', friend=null}}
            第四只羊:Sheep{name='tom', age=1, color='白色', friend=Sheep{name='jack', age=2, color='黑色', friend=null}}
            第一只羊的朋友:1956725890
            第一只羊的朋友:Sheep{name='jack', age=2, color='黑色', friend=null}
            第二只羊的朋友:356573597
            第三只羊的朋友:1735600054
            第四只羊的朋友:21685669
         */
        }
    }
}
