package cn.wjb114514.FlyWeight.Code;

public class ConcreteWebSite extends WebSite{

    // 在具体的类型下指定类型信息
    private String type = ""; // 网站发布的形式(类型)
    @Override
    public void use(User user) {
        System.out.println("网站发布的形式为" + type +";用户" + user.getName() + "正在使用");

    }

    public ConcreteWebSite(String type) {
        this.type = type;
    }
}
