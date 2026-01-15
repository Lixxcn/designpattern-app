package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.service.pattern.creational.abstractfactory.*;
import org.springframework.stereotype.Service;

@Service
public class AbstractFactoryService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 抽象工厂模式演示 ===\n\n");

        // 使用工厂1创建产品族1
        output.append("1. 使用工厂1创建产品族1：\n");
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractProductA productA1 = factory1.createProductA();
        AbstractProductB productB1 = factory1.createProductB();
        productA1.operationA();
        productB1.operationB();

        output.append("\n2. 使用工厂2创建产品族2：\n");
        AbstractFactory factory2 = new ConcreteFactory2();
        AbstractProductA productA2 = factory2.createProductA();
        AbstractProductB productB2 = factory2.createProductB();
        productA2.operationA();
        productB2.operationB();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 抽象产品接口
interface AbstractProductA {
    void operationA();
}

interface AbstractProductB {
    void operationB();
}

// 具体产品A1, A2
class ProductA1 implements AbstractProductA {
    public void operationA() { System.out.println("产品A1的操作"); }
}

class ProductA2 implements AbstractProductA {
    public void operationA() { System.out.println("产品A2的操作"); }
}

// 具体产品B1, B2
class ProductB1 implements AbstractProductB {
    public void operationB() { System.out.println("产品B1的操作"); }
}

class ProductB2 implements AbstractProductB {
    public void operationB() { System.out.println("产品B2的操作"); }
}

// 抽象工厂接口
interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

// 具体工厂1
class ConcreteFactory1 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA1();
    }
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}

// 具体工厂2
class ConcreteFactory2 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA2();
    }
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}

// 使用示例
AbstractFactory factory = new ConcreteFactory1();
AbstractProductA productA = factory.createProductA();
AbstractProductB productB = factory.createProductB();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class AbstractFactory {
        <<interface>>
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class ConcreteFactory1 {
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class ConcreteFactory2 {
        +createProductA() AbstractProductA
        +createProductB() AbstractProductB
    }
    class AbstractProductA {
        <<interface>>
        +operationA()
    }
    class AbstractProductB {
        <<interface>>
        +operationB()
    }
    class ProductA1 {
        +operationA()
    }
    class ProductA2 {
        +operationA()
    }
    class ProductB1 {
        +operationB()
    }
    class ProductB2 {
        +operationB()
    }

    AbstractFactory <|.. ConcreteFactory1
    AbstractFactory <|.. ConcreteFactory2
    AbstractProductA <|.. ProductA1
    AbstractProductA <|.. ProductA2
    AbstractProductB <|.. ProductB1
    AbstractProductB <|.. ProductB2
    ConcreteFactory1 ..> ProductA1 : creates
    ConcreteFactory1 ..> ProductB1 : creates
    ConcreteFactory2 ..> ProductA2 : creates
    ConcreteFactory2 ..> ProductB2 : creates
""";
    }
}
