package cn.wjb114514.iterator.code;

import java.util.Iterator;
import java.util.List;

public class InfoCollegeIterator implements Iterator<Department> {

    List<Department> departments; // 信息工程学院以 List方式存放数据

    int index = -1;

    public InfoCollegeIterator(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if (index >= departments.size() - 1) return false;
        else {
            index += 1;
            return true;
        }
    }

    @Override
    public Department next() {
        return departments.get(index);
    }

    @Override
    public void remove() {

    }
}
