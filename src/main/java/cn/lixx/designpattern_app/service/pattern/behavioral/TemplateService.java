package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.service.pattern.behavioral.template.AbstractClass;
import cn.lixx.designpattern_app.service.pattern.behavioral.template.ConcreteClassA;
import cn.lixx.designpattern_app.service.pattern.behavioral.template.ConcreteClassB;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 模板方法模式演示 ===\n\n");

        // 具体类A
        output.append("1. 使用ConcreteClassA：\n");
        AbstractClass classA = new ConcreteClassA();
        classA.templateMethod();

        output.append("\n2. 使用ConcreteClassB：\n");
        AbstractClass classB = new ConcreteClassB();
        classB.templateMethod();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 抽象类
abstract class AbstractClass {
    // 模板方法 - 定义算法骨架
    public final void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    // 基本方法 - 由子类实现
    protected abstract void primitiveOperation1();
    protected abstract void primitiveOperation2();
    protected abstract void primitiveOperation3();
}

// 具体类A
class ConcreteClassA extends AbstractClass {
    @Override
    protected void primitiveOperation1() {
        System.out.println("步骤1");
    }

    @Override
    protected void primitiveOperation2() {
        System.out.println("步骤2");
    }

    @Override
    protected void primitiveOperation3() {
        System.out.println("步骤3");
    }
}

// 使用示例
AbstractClass obj = new ConcreteClassA();
obj.templateMethod();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class AbstractClass {
        <<abstract>>
        +templateMethod() void
        #primitiveOperation1() void
        #primitiveOperation2() void
        #primitiveOperation3() void
    }
    class ConcreteClassA {
        +primitiveOperation1() void
        +primitiveOperation2() void
        +primitiveOperation3() void
    }
    class ConcreteClassB {
        +primitiveOperation1() void
        +primitiveOperation2() void
        +primitiveOperation3() void
    }
    class Client {
        +main()
    }

    AbstractClass <|-- ConcreteClassA
    AbstractClass <|-- ConcreteClassB
    Client --> AbstractClass : uses
""";
    }
}
