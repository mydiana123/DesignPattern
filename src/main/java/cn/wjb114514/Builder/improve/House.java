package cn.wjb114514.Builder.improve;

public class House {
    private String base;
    private String roof;
    private String walls;

    public House() {
    }

    public House(String base, String roof, String walls) {
        this.base = base;
        this.roof = roof;
        this.walls = walls;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }
}
