package cn.wjb114514.Builder.improve;

public class Director {
    // 内部聚合建造者
    HouseBuilder houseBuilder = null;

    // 构造器/set方法传入建造者


    public Director(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public Director() {
    }

    // 建造房子的流程，交给指挥者，可以完成产品和产品建造分离
    public House constructHouse() {
        houseBuilder.buildBase();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}
