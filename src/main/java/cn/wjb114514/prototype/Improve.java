package cn.wjb114514.prototype;

public class Improve {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom",1,"白色");
        try {
            Sheep sheep1 = (Sheep) sheep.clone();
            Sheep sheep2 = (Sheep) sheep.clone();
            Sheep sheep3 = (Sheep) sheep.clone();
            Sheep sheep4 = (Sheep) sheep.clone();
            System.out.println("第一只羊:" + sheep1);
            System.out.println("第二只羊:" + sheep2);
            System.out.println("第三只羊:" + sheep3);
            System.out.println("第四只羊:" + sheep4);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
