package cn.wjb114514.iterator.code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoCollege implements College {

    List<Department> departmentList;

    public InfoCollege() {
        departmentList = new ArrayList<>();
        addDepartment("信息安全","信息安全技术");
        addDepartment("系统安全","系统安全技术");
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String des) {
        Department department = new Department();
        department.setDes(des); department.setName(name);
        departmentList.add(department);
    }

    @Override
    public Iterator<Department> iterator() {
        return new InfoCollegeIterator(departmentList);
    }
}
