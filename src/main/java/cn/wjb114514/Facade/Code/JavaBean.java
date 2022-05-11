package cn.wjb114514.Facade.Code;

public class JavaBean {

    private String field;
    private String field2;

    public JavaBean() {
    }

    public JavaBean(String field, String field2) {
        this.field = field;
        this.field2 = field2;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}
