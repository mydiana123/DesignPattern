package cn.wjb114514.iterator.code;

import java.util.Iterator;

// 需要实现Iterable接口的类，必须实现提供遍历器的方法
public interface College extends Iterable<Department>{
    String getName();
    void addDepartment(String name,String des);

    @Override
    Iterator<Department> iterator();
}
