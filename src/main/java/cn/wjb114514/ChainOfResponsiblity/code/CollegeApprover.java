package cn.wjb114514.ChainOfResponsiblity.code;

public class CollegeApprover extends Approver{
    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest pr) {
        if (pr.getPrice() >= 5000 && pr.getPrice() <= 10000) {
            System.out.println("请求编号 id" + pr.getId() + this.name + "处理了");
        }else {
            System.out.println("处理不了，交给上级处理");
            next.processRequest(pr);
        }
    }
}
