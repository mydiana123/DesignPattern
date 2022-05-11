package cn.wjb114514.Builder.improve;

// 高楼建造者，为建造者的具体实现子类
public class HighHouse extends HouseBuilder{
    @Override
    public void buildBase() {
        System.out.println("高楼地基100m");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼用混凝土砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("高楼用玻璃做顶部");
    }
}
