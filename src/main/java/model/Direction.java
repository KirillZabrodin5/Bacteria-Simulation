package model;

public enum Direction {
    UP_LEFT(0, -1, -1),
    UP(45, 0, -1),
    UP_RIGHT(90, 1, -1),
    RIGHT(135, 1, 0),
    DOWN_RIGHT(180, 1, 1),
    DOWN(225, 0, 1),
    DOWN_LEFT(270, -1, 1),
    LEFT(315, -1, 0);

    private final int x;
    private final int y;
    private final int angle;

    Direction(int angle, int x, int y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAngle() {
        return angle;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
