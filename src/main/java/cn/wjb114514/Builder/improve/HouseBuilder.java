package cn.wjb114514.Builder.improve;

public abstract class HouseBuilder {
    // 描述建造流程
    public abstract void buildBase();
    public abstract void buildWalls();
    public abstract void roofed();

    // 内部组合产品对象
    House house = new House();

    public House buildHouse () {
        return house;
    }
}
