package cn.wjb114514.proxy.DynamicCglibProxy.Start;

public class Client {
    public static void main(String[] args) {
        // 创建目标对象
        TeacherDao teacherDao = new TeacherDao();
        TeacherDao proxy = (TeacherDao) new ProxyFactory(teacherDao).getProxyInstance();
        proxy.teach();
    }
}
