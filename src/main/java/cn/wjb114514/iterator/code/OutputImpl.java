package cn.wjb114514.iterator.code;

import java.util.Iterator;
import java.util.List;

public class OutputImpl {
    // 学院集合
    List<College> collegeList;

    public OutputImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    // 输出学院 输出系
    public void printCollege() {
        // 取出所有学院:java中的List已经实现了Iterable接口，所以我们可以直接从.iterator() 返回一个迭代器
        Iterator<College> iterator = collegeList.iterator();

        while (iterator.hasNext()) {
            // 取出一个学院
            College college = iterator.next();
            System.out.println("----" + college.getName() + "----");
            printDepartment(college.iterator()); // 得到遍历学院下 的系对应的迭代器。
        }
    }


    public void printDepartment(Iterator<?> iterator) {
        while (iterator.hasNext()) {
            Department d = (Department) iterator.next();
            System.out.println(d.getName());
        }
    }
}
