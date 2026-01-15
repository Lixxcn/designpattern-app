package cn.lixx.designpattern_app.service.pattern.structural.bridge;

/**
 * 扩展抽象化 - 圆形
 */
public class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("绘制" + color.apply() + "的圆形");
    }
}
