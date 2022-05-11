package cn.wjb114514.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.*;

// 继承一个原型接口(jdk里提供的接口就是Cloneable)
//@Data
//@ToString
//@AllArgsConstructor
public class Sheep implements Cloneable, Serializable {

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    // 此方法来自于Object类的native方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object sheep = null;
        // 先用默认的clone()方法，完成对基本类型的拷贝
        sheep = super.clone();
        // 针对引用类型，单独处理
        Sheep sheepTemp = (Sheep) sheep;
        // 把当前已经把基本类型克隆完毕的sheep转换为Sheep类型，目的是获取其中的引用类型成员变量
        // 由于引用类型的变量本身也有clone()方法，因此我们在类的内部直接访问成员变量 并让friend自己完成克隆
        // 注意，这里内部引用的是相同类型的变量，如果A引用了B，会导致异常出现，因为clone()方法是protected的，protected方法不能在两个兄弟之间用
        // 也就是虽然A和B都是Object的儿子，但是A不能用他兄弟B 的protected方法，可以提高B的clone()方法访问权限为public解决

        // 这么操作可以递归的完成所有friend的深拷贝
        if (this.friend != null)
        sheepTemp.friend = (Sheep) this.friend.clone();

        return sheepTemp;
    }

    // 通过序列化实现深拷贝
    public Object deepClone() {
        // 创建流对象
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            // 把当前对象序列化输出到字节数组里
            oos.writeObject(this);

            // 目前bais就被输入了 之前序列化并存为字节数组的对象 [对象本质也可以用二进制字节码表示，所以字节数组流里就存放了表示一个对象的二进制数据]
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            // 由于从序列化的对象里读回来之后，需要给二进制的流对象分配空间以获取动态数据结构，相当于完成了深拷贝
            Sheep deepCopySheep = (Sheep) ois.readObject();
            return deepCopySheep;
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                bais.close();
                baos.close();
                oos.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    private String name;
    private int age;
    private String color;
    public Sheep friend; // 引用类型的属性

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", friend=" + friend +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Sheep getFriend() {
        return friend;
    }

    public void setFriend(Sheep friend) {
        this.friend = friend;
    }
}
