package cn.wjb114514.ChainOfResponsiblity.code;

public class SchoolMasterApprover extends Approver{
    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest pr) {
        if (pr.getPrice() >= 20000 && pr.getPrice() <= 30000) {
            System.out.println("请求编号 id" + pr.getId() + this.name + "处理了");
        }else {
            System.out.println("处理不了，交给上级处理");
            next.processRequest(pr);
        }
    }
}
