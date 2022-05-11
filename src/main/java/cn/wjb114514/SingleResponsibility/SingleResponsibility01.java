package cn.wjb114514.SingleResponsibility;

/**
 * 演示单一职责原则
 * 1.主要降低了类的复杂度，一个类只负责一项职责
 * 2.提高了类的可读性，可维护性
 * 3.降低变更带来的风险
 * 4.通常情况下，应该严格遵守单一职责，只有在逻辑足够简单时：在可以在代码级违反单一职责原则：只有类中方法足够少，可以在方法级别保持单一职责原则
 *
 */
public class SingleResponsibility01 {
    public static void main(String[] args) {
        VehicleModify vehicle = new VehicleModify();
        vehicle.run("motto");
        vehicle.runWater("ship");
        vehicle.runAir("aeroplane");
    }
}

// 1.违反了单一职责原则，公路上运行的类 兼管水上跑的和天上飞的
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路上运行....");
    }
}

// 2.改写：把集成多个功能的类A，分解为A1，A2和A3 但是这么干开销很大~
class RoadVehicle {
    public void run(String roadVehicle) {
        System.out.println(roadVehicle + "在公路上运行");
    }
}
class WaterVehicle {
    public void run(String waterVehicle) {
        System.out.println(waterVehicle + "在水中上运行");
    }
}
class SkyVehicle {
    public void run(String skyVehicle) {
        System.out.println(skyVehicle + "在天上运行");
    }
}
// 3.改写
// 1.虽然原来的类仍然不满足单一职责原则， 但是在方法级别上，遵守单一职责原则
class VehicleModify {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路上运行....");
    }
    public void runAir(String vehicle) {
        System.out.println(vehicle + "在天上飞");
    }
    public void runWater(String vehicle) {
        System.out.println(vehicle + "在水上游行");
    }
}
