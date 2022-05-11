package cn.wjb114514.Command.Code;

// 具体的命令接受者， 其方法规定了命令作用于接受者后产生的某种影响。 比如开灯命令作用了，结果就是接受者被打开...
// 命令作用在接受者的结果就是，接受者对象调用了某方法
// 接受者 ==收到命令==> 接受者.执行命令();
public class LightReceiver {
    public void on() {
        System.out.println(" 电灯打开了 ....");
    }
    public void off() {
        System.out.println(" 电灯关闭了 ....");
    }
}
