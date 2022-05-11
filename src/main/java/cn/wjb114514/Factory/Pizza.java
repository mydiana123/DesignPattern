package cn.wjb114514.Factory;

// 把主类做成抽象方法
public abstract class Pizza {
    protected String name;
    // 披萨制作的原料准备，因披萨而异
    public abstract void prepare();

    // 烘烤切割与打包过程大同小异，无需重写！
    public void bake() {
        System.out.println(name + " baking");
    }
    public void cut() {
        System.out.println(name + " cutting");
    }
    public void box() {
        System.out.println(name + " box");
    }

    public void setName(String name) {
        this.name = name;
    }
}

class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("奶酪披萨需要奶酪");
    }
}

class GreekPizza extends  Pizza {

    @Override
    public void prepare() {
        System.out.println("希腊披萨需要希腊！");
    }
}

// 增加一个新类型的披萨
class NewTypePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("新口味的奥利给披萨");
    }
}