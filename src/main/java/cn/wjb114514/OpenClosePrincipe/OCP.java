package cn.wjb114514.OpenClosePrincipe;

/**
 * 开闭原则：
 * 一个软件实体entity 如类class，模块module和函数function，应该对扩展开放(提供方)，对修改关闭(使用方)。用抽象构建框架，用实现扩展细节
 * 软件需要变化时，尽量通过扩展软件实体的行为实现变化，不是通过修改已有的代码来实现变化
 * 也就是说：使用这份代码的人，不会因为提供方扩展功能而受影响，也就是提供方不会修改原有的代码，而是在原有代码基础上增加实体
 *
 * 因为接口实现类是多样的，因此根据多态，接口的实现方法是多样的，动态的，灵活的
 * 针对接口的修改方式是添加实体(添加实现类和实现方法)
 * 而针对类的修改方式，由于只能单继承，所以只能在功能类上完成业务逻辑的判断，修改源代码~不满足OCP
 *
 * java编程的核心:包装+分层
 */
public class OCP {
    public static void main(String[] args) {
        new GraphicEditor().drawShape(new Rectangle());
        new GraphEditorPlus().drawGraph(new Rec());
    }
}

// 用于绘图的类
// 好处:代码简单易懂
// 缺点:不满足ocp，如果需要增加功能，在增加实体(类和方法)的基础上，还要对GraphicEditor的drawShape方法进行修改，而且实体方法的增加是在已经存在的类上进行了修改！
class GraphicEditor {
    public void drawShape(Shape shape) {
        // 这里的大量if-else，一旦要拓展功能，比如画一个三角形，就要多一个m_type，多一个if分支
        if (shape.m_type == 1) {
            drawRec();
        }else if (shape.m_type == 2){
            drawCir();
        }
    }

    private void drawCir() {
        System.out.println("画圈");
    }

    private void drawRec() {
        System.out.println("画矩形");
    }
}

class GraphEditorPlus {
    public void drawGraph(Graph g) {
        g.draw();
    }
}
class Shape {
    int m_type;
}

class Rectangle extends Shape{
    public Rectangle(){
        super.m_type = 1;
    }
}

class Circle extends Shape {
    public Circle() {
        super.m_type = 2;
    }
}

// 改进方法，把图形做成抽象类
abstract class Graph {
    int m_type;
    public abstract void draw();
}

class Rec extends Graph {

    public Rec() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}