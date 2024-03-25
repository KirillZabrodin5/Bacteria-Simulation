package model.entity;

public abstract class BaseEntity {
    public volatile double x;
    public volatile double y;

    public BaseEntity(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
