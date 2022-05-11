package cn.wjb114514.iterator.code;

import java.util.Iterator;

// 迭代器的实现方式，和具体的聚合方式有关。所以我们必须知道，Department是以什么结构存放到ComputerCollege里
public class ComputerIterator implements Iterator<Department> {

    Department[] departments;
    int position = 0; // 遍历的位置

    public ComputerIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        return position < departments.length && departments[position] != null;

    }

    @Override
    public Department next() {
        return departments[position++];
    }

    @Override
    public void remove() {
    }
}
