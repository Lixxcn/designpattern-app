package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.decorator.*;
import org.springframework.stereotype.Service;

@Service
public class DecoratorService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 装饰器模式演示 ===\n\n");

        // 原始咖啡
        output.append("1. 原始咖啡：");
        Component coffee = new ConcreteComponent("黑咖啡");
        System.out.println(); // 为了测试输出
        coffee.operation();
        System.out.println(); // 为了测试输出

        // 添加牛奶
        output.append("\n2. 加牛奶：");
        Component coffeeWithMilk = new MilkDecorator(coffee);
        coffeeWithMilk.operation();
        System.out.println();

        // 添加牛奶和糖
        output.append("\n3. 加牛奶和糖：");
        Component coffeeWithMilkAndSugar = new SugarDecorator(coffeeWithMilk);
        coffeeWithMilkAndSugar.operation();
        System.out.println();

        // 完整配料
        output.append("\n4. 完整配料（牛奶+糖+奶泡）：");
        Component fullCoffee = new WhipDecorator(coffeeWithMilkAndSugar);
        fullCoffee.operation();
        System.out.println();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 组件接口
interface Component {
    void operation();
}

// 具体组件
class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.print("基础组件");
    }
}

// 装饰器抽象类
abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

// 具体装饰器
class MilkDecorator extends Decorator {
    public MilkDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.print(" + 牛奶");
    }
}

// 使用示例
Component coffee = new ConcreteComponent();
Component coffeeWithMilk = new MilkDecorator(coffee);
coffeeWithMilk.operation();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Component {
        <<interface>>
        +operation() void
    }
    class ConcreteComponent {
        +operation() void
    }
    class Decorator {
        <<abstract>>
        #Component component
        +Decorator(Component)
        +operation() void
    }
    class MilkDecorator {
        +MilkDecorator(Component)
        +operation() void
    }
    class SugarDecorator {
        +SugarDecorator(Component)
        +operation() void
    }
    class WhipDecorator {
        +WhipDecorator(Component)
        +operation() void
    }

    Component <|.. ConcreteComponent
    Component <|.. Decorator
    Decorator <|-- MilkDecorator
    Decorator <|-- SugarDecorator
    Decorator <|-- WhipDecorator
    Decorator o-- Component : decorates
""";
    }
}
