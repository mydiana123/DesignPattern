package cn.wjb114514.iterator.code;

import java.util.Iterator;

/**
 *   欲遍历的对象[聚合对象] ==> 内部聚合了存放数据的对象，并可以提供一个针对此对象的迭代器[聚合对象可以实现iterable接口]
 *   迭代器 ==> 需要了解聚合对象的底层存储，并提供Iterator接口的方法实现
 *
 *   当我们想要遍历聚合对象时，由于不知道聚合对象内部的结构，但是聚合对象给我们提供了一个统一的迭代器。因此我们使用统一的迭代器遍历即可
 */
public class ComputerCollege implements College {


    Department[] departments;
    int numOfDepartment = 0; // 保存当前的数组对象个数

    public ComputerCollege() {
        departments = new Department[5];
        addDepartment("java","学习java");
        addDepartment("go","学习go");
        addDepartment("php","学习php");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String des) {
        Department department = new Department();
        department.setName(name);
        department.setDes(des);

        departments[numOfDepartment ++] = department;
    }

    @Override
    public Iterator<Department> iterator() {
        return new ComputerIterator(departments);
    }
}
