package cn.lixx.designpattern_app.service.pattern.structural.bridge;

/**
 * 抽象化类 - 形状
 */
public abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
