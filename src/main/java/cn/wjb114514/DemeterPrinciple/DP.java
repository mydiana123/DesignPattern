package cn.wjb114514.DemeterPrinciple;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.一个对象应该对其他对象保持最少的了解
 * 2.类与类关系越密切，耦合度越大
 * 3.迪米特法则又叫最少知道原则，一个类对自己依赖的类，知道的越少越好，也就是说，对于被依赖的类不管多么复杂，都尽量将逻辑封装在类的内部。对外提供public方法
 * 4.只与直接的朋友通信:每个对象都会与其他对象有耦合关系，只要两个对象有耦合关系，就是朋友关系
 * 耦合的方式有很多:依赖，关联，组合，聚合。我们称出现成员变量，方法参数，方法返回值中的类，为直接的朋友，而出现在局部变量里的类不是直接朋友。
 * 陌生的类不要以局部变量的形式出现在类的内部
 *
 * 也就是说：一个陌生的类不要出现在其他类的方法体里，这样一旦陌生的类需要修改，那么这个方法的整体逻辑都可能变了，有可能需要调整陌生类的方法体。
 * 而作为成员变量，方法参数和返回值，可以让陌生类的修改对方法造成最少影响
 */
public class DP {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.printAll(new CollegeManager());
        manager.printAll2(new CollegeManager());
    }
}

/**
 * 有一个学校：下属有各个学院和总部，要求打印出 学校总部员工ID和学院员工ID
 */

// 学校总部员工
class Employee{
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

// 学院员工
class CollegeEmployee {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

// 学院员工的管理类，生成十个学院员工
class CollegeManager {
    public List<CollegeEmployee> getAllCollegeEmployee() {
        List<CollegeEmployee> list = new ArrayList<>();
        for(int i = 0; i< 10; i++) {
            CollegeEmployee collegeEmployee = new CollegeEmployee();
            collegeEmployee.setId("学院员工id"+i);
            list.add(collegeEmployee);
        }
        return list;
    }
    public void printAll() {
        // 学院员工的信息，由本方法自己负责
        System.out.println("-----学院员工------");
        for(CollegeEmployee e:getAllCollegeEmployee()) {
            System.out.println(e.getId());
        }
    }
}

// 分析此类的直接朋友和间接朋友
class Manager {
    // Employee出现在返回值，是直接朋友
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        for(int i = 0; i< 5; i++) {
            Employee employee = new Employee();
            employee.setId("总部员工id"+i);
            list.add(employee);
        }
        return list;
    }

    // 该方法完成输出学校总部和学院员工信息
    // CollegeManager 出现在形参，是直接朋友
    // !!CollegeEmployee出现在局部变量，不是直接朋友 违背迪米特法则~~
    void printAll(CollegeManager cm) {
        // 获取学院员工的所有信息
        List<CollegeEmployee> allCollegeManager = cm.getAllCollegeEmployee();
        System.out.println("-----学院员工------");
        for(CollegeEmployee e: allCollegeManager) {
            System.out.println(e.getId());
        }
        System.out.println("----------学校员工-----");
        List<Employee> allEmployee = getAllEmployee();
        for(Employee e:allEmployee) {
            System.out.println(e.getId());
        }
    }
    void printAll2(CollegeManager cm) {
        // 将输出学院员工的方法，封装到CollegeManager里
        System.out.println("----------学校员工-----");
        cm.printAll();
        List<Employee> allEmployee = getAllEmployee();
        for(Employee e:allEmployee) {
            System.out.println(e.getId());
        }
    }
}