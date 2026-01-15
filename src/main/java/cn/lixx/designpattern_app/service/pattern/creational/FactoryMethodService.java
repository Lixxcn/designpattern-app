package cn.lixx.designpattern_app.service.pattern.creational;
import cn.lixx.designpattern_app.model.CodeFile;
import java.util.List;

import cn.lixx.designpattern_app.service.pattern.creational.factorymethod.*;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.springframework.stereotype.Service;

@Service
public class FactoryMethodService {

    private final CodeReaderUtil codeReaderUtil;

    public FactoryMethodService(CodeReaderUtil codeReaderUtil) {
        this.codeReaderUtil = codeReaderUtil;
    }

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

    /**
     * 从 factorymethod 包读取所有示例代码
     */
    public List<CodeFile> getCodeExample() {
        return codeReaderUtil.readCodeFromPackage(
            "cn.lixx.designpattern_app.service.pattern.creational.factorymethod"
        );
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
