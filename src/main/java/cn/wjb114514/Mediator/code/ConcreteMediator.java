package cn.wjb114514.Mediator.code;

import java.util.HashMap;

public class ConcreteMediator extends Mediator {

    // 化继承为 组合/聚合。内部管理 同事类集合
    private HashMap<String, Colleague> colleagueMap;
    private HashMap<String, String> interMap;

    public ConcreteMediator() {
        this.colleagueMap = new HashMap<>();
        this.interMap = new HashMap<>();
    }

    @Override
    public void register(String colName, Colleague colleague) {
        colleagueMap.put(colName, colleague);

        // two hashMap maintain a relationship that [unique name -- colName -- colleague]
        // we can find colleague by interMap via name
        if (colleague instanceof Alarm) {
            interMap.put("Alarm", colName);
        } else if (colleague instanceof CoffeeMachine) {
            interMap.put("CoffeeMachine", colName);
        } else {
            // other concreteColleague

        }
    }

    // 中介者的核心方法
    @Override
    public void getMessage(int stateChange, String colleagueName) {
        // 根据得到的消息，完成对应的任务[中介者在这个 方法里，协调具体的同时对象，完成任务]

        // 业务逻辑：闹钟一响 ==> 打开打开咖啡机

        // 先判断是哪个 同事类 发出的消息
        if (colleagueMap.get(interMap.get(colleagueName)) instanceof Alarm) {
            // 闹钟同事 发来了消息，按照我们的逻辑，如果闹钟响了，我们就打开咖啡机
            ((CoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).on();
        }else {
            // 继续根据传入 同事的不同，从同事集合里拿出相关同事，进行协调工作，完成业务逻辑
        }
    }

    @Override
    public void sendMessage() {

    }
}
