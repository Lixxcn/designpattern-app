package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.service.pattern.structural.decorator.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class DecoratorService {

    private final CodeReaderUtil codeReaderUtil;

    public DecoratorService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 decorator 包读取所有示例代码
     */
    public String getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.structural.decorator"
        );
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
