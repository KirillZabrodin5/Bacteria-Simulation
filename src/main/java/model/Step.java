package model;

public enum Step {
    UP(0, 0,-1),
    UP_RIGHT(45,1,-1),
    RIGHT(90, 1,0),
    DOWN_RIGHT(135,1,1),
    DOWN(180,0,1),
    DOWN_LEFT(225, -1,1),
    LEFT(270,-1,0),
    UP_LEFT(315,-1,-1);

    private final int x;
    private final int y;
    private final int angle;
    Step(int angle, int x, int y) {
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
