package cn.lixx.designpattern_app.service.pattern.structural.bridge;

/**
 * 扩展抽象化 - 正方形
 */
public class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("绘制" + color.apply() + "的正方形");
    }
}
