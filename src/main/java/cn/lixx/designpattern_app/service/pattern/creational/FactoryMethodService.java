package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.service.pattern.creational.factorymethod.*;
import org.springframework.stereotype.Service;

@Service
public class FactoryMethodService {

    public String executeExample() {
        StringBuilder output = new StringBuilder();

        output.append("=== 工厂方法模式演示 ===\n\n");

        // 使用具体工厂A创建产品A
        output.append("1. 使用工厂A创建产品：\n");
        Factory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.createProduct();
        productA.use();

        output.append("\n2. 使用工厂B创建产品：\n");
        Factory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.createProduct();
        productB.use();

        return output.toString();
    }

    public String getCodeExample() {
        return """
// 产品接口
interface Product {
    void use();
}

// 具体产品A
class ConcreteProductA implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品A");
    }
}

// 具体产品B
class ConcreteProductB implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品B");
    }
}

// 工厂接口
interface Factory {
    Product createProduct();
}

// 具体工厂A
class ConcreteFactoryA implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

// 具体工厂B
class ConcreteFactoryB implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}

// 使用示例
Factory factoryA = new ConcreteFactoryA();
Product productA = factoryA.createProduct();
productA.use();
""";
    }

    public String getMermaidDiagram() {
        return """
classDiagram
    class Product {
        <<interface>>
        +use() void
    }
    class ConcreteProductA {
        +use() void
    }
    class ConcreteProductB {
        +use() void
    }
    class Factory {
        <<interface>>
        +createProduct() Product
    }
    class ConcreteFactoryA {
        +createProduct() Product
    }
    class ConcreteFactoryB {
        +createProduct() Product
    }

    Product <|.. ConcreteProductA
    Product <|.. ConcreteProductB
    Factory <|.. ConcreteFactoryA
    Factory <|.. ConcreteFactoryB
    Factory ..> Product : creates
    ConcreteFactoryA ..> ConcreteProductA : creates
    ConcreteFactoryB ..> ConcreteProductB : creates
""";
    }
}
