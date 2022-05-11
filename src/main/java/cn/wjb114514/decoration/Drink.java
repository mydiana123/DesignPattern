package cn.wjb114514.decoration;

public abstract class Drink {
    public String des; // 对当前Drink的描述
    private float price = 0.0f; // 当前饮品的价格

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // 计算费用的方法，由子类具体实现
    public abstract float cost();
}
