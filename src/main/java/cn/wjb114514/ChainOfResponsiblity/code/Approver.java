package cn.wjb114514.ChainOfResponsiblity.code;

public abstract class Approver {
    Approver next;
    String name ;

    public Approver(String name) {
        this.name = name;
    }

    // 设置下一个处理者
    public void setApprover(Approver next) {
        this.next = next;
    }

    // 处理审批请求的方法，具体实现由子类实现。 模板方法
    public abstract void processRequest(PurchaseRequest pr);
}
