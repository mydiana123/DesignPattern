package cn.wjb114514.ChainOfResponsiblity.code;

public class Client {
    public static void main(String[] args) {
        // Client类为请求的发送者。请求发送者无需手动判断请求类型，并分发给不同的处理者。也就是 请求的发送和处理进行解耦。

        PurchaseRequest buyMultiMediaComputer = new PurchaseRequest(1, 300000, 1);

        DepartmentApprover departmentApprover = new DepartmentApprover("张子明主任");
        CollegeApprover collegeApprover = new CollegeApprover("李莉院长");
        ViceSchoolMasterApprover viceSchoolMasterApprover = new ViceSchoolMasterApprover("赵日天副校长");
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("曾小洋校长");

        // 设置职责链.
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolMasterApprover);
        viceSchoolMasterApprover.setApprover(schoolMasterApprover);


        // 注意：职责链是环状的，如果 1000块钱设备交给校长处理，会报错 因为校长没有下一个，环形设计可以使得每个请求都能找到 相关的handler。
        // 当然我们可以认为的规定 做成单向的，即只允许从最低职责的handler处理
        // 当然，做成 环状后，如果一个请求谁也无法处理，就会导致 Stack Overflow异常
        schoolMasterApprover.setApprover(departmentApprover);

        departmentApprover.processRequest(buyMultiMediaComputer);

    }
}
