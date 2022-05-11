package cn.wjb114514.decoration;

public class Soy extends Decorator{
    public Soy(Drink obj) {
        super(obj);
        setDes("豆浆");
        setPrice(1.5f);
    }
}
