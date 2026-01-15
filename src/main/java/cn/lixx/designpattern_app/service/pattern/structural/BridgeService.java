package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.bridge.*;
import org.springframework.stereotype.Service;

@Service
public class BridgeService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 桥接模式演示 ===\n\n");

        // 创建颜色
        Color red = new RedColor();
        Color blue = new BlueColor();
        Color green = new GreenColor();

        output.append("1. 红色圆形：\n");
        Shape redCircle = new Circle(red);
        redCircle.draw();

        output.append("\n2. 蓝色正方形：\n");
        Shape blueSquare = new Square(blue);
        blueSquare.draw();

        output.append("\n3. 绿色圆形：\n");
        Shape greenCircle = new Circle(green);
        greenCircle.draw();

        output.append("\n4. 组合不同形状和颜色：\n");
        Shape[] shapes = {
            new Circle(red),
            new Square(blue),
            new Circle(green),
            new Square(red)
        };

        for (Shape shape : shapes) {
            shape.draw();
        }

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 实现化接口 - 颜色
interface Color {
    String apply();
}

// 具体实现化
class RedColor implements Color {
    @Override
    public String apply() {
        return "红色";
    }
}

class BlueColor implements Color {
    @Override
    public String apply() {
        return "蓝色";
    }
}

// 抽象化类 - 形状
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

// 扩展抽象化
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("绘制" + color.apply() + "的圆形");
    }
}

// 使用示例
Color red = new RedColor();
Shape redCircle = new Circle(red);
redCircle.draw();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Color {
        <<interface>>
        +apply() String
    }
    class RedColor {
        +apply() String
    }
    class BlueColor {
        +apply() String
    }
    class GreenColor {
        +apply() String
    }
    class Shape {
        <<abstract>>
        #Color color
        +Shape(Color)
        +draw() void
    }
    class Circle {
        +Circle(Color)
        +draw() void
    }
    class Square {
        +Square(Color)
        +draw() void
    }

    Color <|.. RedColor
    Color <|.. BlueColor
    Color <|.. GreenColor
    Shape <|-- Circle
    Shape <|-- Square
    Shape o-- Color : uses
""";
    }
}
