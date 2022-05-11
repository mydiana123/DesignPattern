package cn.wjb114514.iterator.code;

import java.util.ArrayList;
import java.util.List;

/**
 * 原理：使用 具体的迭代器子类 包装 对应类型的数据结构，提供统一的遍历接口
 */
public class Client {
    public static void main(String[] args) {
        List<College> collegeList = new ArrayList<>();
        collegeList.add(new ComputerCollege());
        collegeList.add(new InfoCollege());

        new OutputImpl(collegeList).printCollege();

    }
}
