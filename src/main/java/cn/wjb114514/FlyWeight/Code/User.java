package cn.wjb114514.FlyWeight.Code;

// 本对象作为不共享，不缓存的 外部状态，由用户自行提供，不进行缓存
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
